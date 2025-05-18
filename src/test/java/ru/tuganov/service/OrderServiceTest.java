package ru.tuganov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tuganov.dto.OrderStatusDto;
import ru.tuganov.dto.order.CafeOrderDto;
import ru.tuganov.dto.order.CafeOrderProductDto;
import ru.tuganov.entity.*;
import ru.tuganov.entity.order.CafeOrder;
import ru.tuganov.repository.OrderRepository;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private TelegramUserService telegramUserService;

    @Mock
    private CafeAddressService cafeAddressService;

    @Mock
    private ProductService productService;

    @Mock
    private ToppingService toppingService;

    @InjectMocks
    private OrderService orderService;

    private final AtomicInteger testOrderNumber = new AtomicInteger(0);

    @BeforeEach
    void setUp() {
        testOrderNumber.set(0);
    }

    @Test
    void setOrderStatus_ShouldUpdateOrderStatus() {
        long orderId = 1L;
        long userId = 123L;
        String newStatus = "Готов";
        OrderStatusDto orderStatusDto = new OrderStatusDto(orderId, userId, newStatus);

        CafeOrder mockOrder = new CafeOrder();
        mockOrder.setId(orderId);
        mockOrder.setStatus("Готовится");

        when(orderRepository.findCafeOrderById(orderId)).thenReturn(mockOrder);
        when(orderRepository.save(any(CafeOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        orderService.setOrderStatus(orderStatusDto);

        verify(orderRepository).findCafeOrderById(orderId);
        verify(orderRepository).save(mockOrder);
        assertEquals(newStatus, mockOrder.getStatus());
    }

    @Test
    void getCurrentOrders_ShouldReturnOrdersWithActiveStatuses() {
        long userId = 123L;
        TelegramUser mockUser = new TelegramUser();
        mockUser.setId(userId);

        List<CafeOrder> expectedOrders = List.of(
                createTestOrder("Оплачен"),
                createTestOrder("Готовится"),
                createTestOrder("Готов")
        );

        when(telegramUserService.getTelegramUser(userId)).thenReturn(mockUser);
        when(orderRepository.findCafeOrderByTelegramUser(mockUser, Set.of("Оплачен", "Готовится", "Готов")))
                .thenReturn(expectedOrders);

        List<CafeOrder> result = orderService.getCurrentOrders(userId);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(order ->
                Set.of("Оплачен", "Готовится", "Готов").contains(order.getStatus())));
    }

    @Test
    void getOrdersHistory_ShouldReturnOrdersWithCompletedStatuses() {

        long userId = 123L;
        TelegramUser mockUser = new TelegramUser();
        mockUser.setId(userId);

        List<CafeOrder> expectedOrders = List.of(
                createTestOrder("Выдан"),
                createTestOrder("Отменён")
        );

        when(telegramUserService.getTelegramUser(userId)).thenReturn(mockUser);
        when(orderRepository.findCafeOrderByTelegramUser(mockUser, Set.of("Выдан", "Отменён")))
                .thenReturn(expectedOrders);

        List<CafeOrder> result = orderService.getOrdersHistory(userId);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(order ->
                Set.of("Выдан", "Отменён").contains(order.getStatus())));
    }

    @Test
    void saveNewOrder_ShouldConvertDtoAndSaveOrder() {

        long userId = 123L;
        long addressId = 1L;
        String orderDateTime = "14:30 2023-05-15";
        int totalPrice = 1500;

        CafeOrderDto orderDto = new CafeOrderDto(
                userId,
                "Доставка",
                totalPrice,
                addressId,
                orderDateTime,
                List.of(createTestProductDto()),
                null
        );

        TelegramUser mockUser = new TelegramUser();
        mockUser.setId(userId);
        CafeAddress mockAddress = new CafeAddress();
        mockAddress.setId(addressId);
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        Size mockSize = new Size();
        mockSize.setId(1L);

        when(telegramUserService.getTelegramUser(userId)).thenReturn(mockUser);
        when(cafeAddressService.findCafe(addressId)).thenReturn(mockAddress);
        when(productService.getProduct(1L)).thenReturn(mockProduct);
        when(toppingService.getTopping(anyLong())).thenReturn(new Topping());

        orderService.saveNewOrder(orderDto);

        verify(telegramUserService).saveTelegramUser(mockUser);
        assertFalse(mockUser.getCafeOrders().isEmpty());
        CafeOrder savedOrder = mockUser.getCafeOrders().get(0);
        assertEquals("Готовится", savedOrder.getStatus());
        assertEquals(totalPrice, savedOrder.getTotalPrice());
        assertEquals(mockAddress, savedOrder.getCafeAddress());
    }

    @Test
    void getCafeOrders_ShouldReturnActiveOrdersForCafe() {
        CafeAddress cafeAddress = new CafeAddress();
        cafeAddress.setId(1L);

        List<CafeOrder> expectedOrders = List.of(
                createTestOrder("Оплачен"),
                createTestOrder("Готовится")
        );

        when(orderRepository.findCafeOrdersByCafeAddress(cafeAddress, Set.of("Оплачен", "Готовится", "Готов")))
                .thenReturn(expectedOrders);

        List<CafeOrder> result = orderService.getCafeOrders(cafeAddress);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(order ->
                Set.of("Оплачен", "Готовится").contains(order.getStatus())));
    }

    private CafeOrder createTestOrder(String status) {
        CafeOrder order = new CafeOrder();
        order.setStatus(status);
        return order;
    }

    private CafeOrderProductDto createTestProductDto() {
        return new CafeOrderProductDto(
                "1-suffix",
                1,
                1L,
                List.of(1L, 2L)
        );
    }
}
