package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.tuganov.dto.order.CafeOrderDiscountDto;
import ru.tuganov.dto.order.CafeOrderDto;
import ru.tuganov.dto.OrderStatusDto;
import ru.tuganov.dto.order.CafeOrderProductDto;
import ru.tuganov.entity.*;
import ru.tuganov.entity.order.CafeOrder;
import ru.tuganov.entity.order.CafeOrderDiscount;
import ru.tuganov.entity.order.CafeOrderProduct;
import ru.tuganov.entity.order.CafeOrderProductTopping;
import ru.tuganov.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final TelegramUserService telegramUserService;
    private final CafeAddressService cafeAddressService;
    private final DiscountService discountService;
    private final ProductService productService;
    private final ToppingService toppingService;
    private final AtomicInteger orderNumber = new AtomicInteger(0);
    private final SimpMessagingTemplate messagingTemplate;

    public void setOrderStatus(OrderStatusDto orderStatus) {
        CafeOrder order = orderRepository.findCafeOrderById(orderStatus.orderId());
        order.setStatus(orderStatus.status());
//        messagingTemplate.convertAndSend("/profile/new-status/" + orderStatus.userId(), orderStatus);
//        messagingTemplate.convertAndSend("/kitchen/new-status/" + order.);
        orderRepository.save(order);
    }

    private CafeOrderProductTopping DtoToCafeOrderProductTopping(long toppingId) {
        Topping topping = toppingService.getTopping(toppingId);
        return CafeOrderProductTopping.builder()
                .topping(topping)
                .build();
    }

    private CafeOrderProduct DtoToCafeOrderProduct(CafeOrderProductDto cafeOrderProductDto) {
        long productId = Long.parseLong(cafeOrderProductDto.id().split("-")[0]);
        Product product = productService.getProduct(productId);
        Size size = product.getSizes().stream()
                .filter(s -> s.getId().equals(cafeOrderProductDto.sizeId()))
                .findAny().get();
        CafeOrderProduct cafeOrderProduct = CafeOrderProduct.builder()
                .quantity(cafeOrderProductDto.quantity())
                .product(product)
                .size(size)
                .build();
//        нужно ли вообще?
//        size.getCafeOrderProduct().add(cafeOrderProduct);
        List<CafeOrderProductTopping> toppings = cafeOrderProductDto.toppingsIds().stream()
                .map(this::DtoToCafeOrderProductTopping)
                .toList();
//        вот тут топпинги я не сохраняю, а значит и не нужно?
        toppings.forEach(topping -> topping.setCafeOrderProduct(cafeOrderProduct));
        cafeOrderProduct.setCafeOrderProductToppings(toppings);
        return cafeOrderProduct;
    }

    private CafeOrderDiscount DtoToCafeOrderDiscount(CafeOrderDiscountDto cafeOrderDiscountDto) {
        List<CafeOrderProduct> cafeOrderProducts = cafeOrderDiscountDto.cafeOrderProductDtoList().stream()
                .map(this::DtoToCafeOrderProduct)
                .toList();
        CafeOrderDiscount discount = CafeOrderDiscount.builder()
                .discount(discountService.getDiscount(cafeOrderDiscountDto.id()))
                .quantity(cafeOrderDiscountDto.quantity())
                .cafeOrderProducts(cafeOrderProducts)
                .build();
        cafeOrderProducts.forEach(product -> product.setCafeOrderDiscount(discount));
        return discount;
    }

    private CafeOrder DtoToCafeOrder(CafeOrderDto cafeOrderDto) {
        List<CafeOrderDiscount> cafeOrderDiscounts = new ArrayList<>();
        if (cafeOrderDto.discounts() != null) {
            cafeOrderDiscounts = cafeOrderDto.discounts().stream()
                    .map(this::DtoToCafeOrderDiscount)
                    .toList();
        }
        List<CafeOrderProduct> cafeOrderProducts = new ArrayList<>();
        if (cafeOrderDto.products() != null) {
            cafeOrderProducts = cafeOrderDto.products().stream()
                    .map(this::DtoToCafeOrderProduct)
                    .toList();
        }
        CafeOrder cafeOrder = CafeOrder.builder()
                .status("Готовится")
                .deliveryMethod(cafeOrderDto.deliveryMethod())
                .cafeAddress(cafeAddressService.findCafe(cafeOrderDto.addressId()))
                .discounts(cafeOrderDiscounts)
                .products(cafeOrderProducts)
                .build();

        cafeOrderProducts.forEach(product -> product.setCafeOrder(cafeOrder));
        cafeOrderDiscounts.forEach(discount -> discount.setCafeOrder(cafeOrder));

        return cafeOrder;
    }

    public List<CafeOrder> getCurrentOrders(long userId) {
        Set<String> statuses = Set.of("Оплачен", "Готовится", "Готов");
        TelegramUser telegramUser = telegramUserService.getTelegramUser(userId);
        return orderRepository.findCafeOrderByTelegramUser(telegramUser, statuses);
    }

    public List<CafeOrder> getOrdersHistory(long userId) {
        Set<String> statuses = Set.of("Выдан", "Отменён");
        TelegramUser telegramUser = telegramUserService.getTelegramUser(userId);
        return orderRepository.findCafeOrderByTelegramUser(telegramUser, statuses);
    }

    public void saveNewOrder(CafeOrderDto cafeOrderDto) {
        TelegramUser telegramUser = telegramUserService.getTelegramUser(cafeOrderDto.userId());
        CafeOrder cafeOrder = DtoToCafeOrder(cafeOrderDto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd");
        cafeOrder.setOrderDateTime(LocalDateTime.parse(cafeOrderDto.orderDateTime(), formatter));
        cafeOrder.setOrderNumber(orderNumber.getAndIncrement());
        cafeOrder.setTotalPrice(cafeOrderDto.totalPrice());
        if (orderNumber.get() > 999) {
            orderNumber.set(0);
        }
        telegramUser.getCafeOrders().add(cafeOrder);
        cafeOrder.setTelegramUser(telegramUser);
        telegramUserService.saveTelegramUser(telegramUser);
    }

    public List<CafeOrder> getCafeOrders(CafeAddress cafeAddress) {
        Set<String> statuses = Set.of("Оплачен", "Готовится", "Готов");
        return orderRepository.findCafeOrdersByCafeAddress(cafeAddress, statuses);
    }
}
