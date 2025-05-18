package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tuganov.dto.order.CafeOrderDto;
import ru.tuganov.dto.OrderStatusDto;
import ru.tuganov.entity.order.CafeOrder;
import ru.tuganov.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/set-status")
    public HttpStatus setStatus(@RequestBody OrderStatusDto orderStatusDto) {
        orderService.setOrderStatus(orderStatusDto);
        return HttpStatus.OK;
    }

    @GetMapping("/get-current-orders/{userId}")
    public ResponseEntity<List<CafeOrder>> getCurrentOrders(@PathVariable long userId) {
        return ResponseEntity.ok(orderService.getCurrentOrders(userId));
    }

    @GetMapping("/get-order-history/{userId}")
    public ResponseEntity<List<CafeOrder>> getOrderHistory(@PathVariable long userId) {
        return ResponseEntity.ok(orderService.getOrdersHistory(userId));
    }

    @PostMapping("/new-order")
    public HttpStatus saveNewOrder(@RequestBody CafeOrderDto newOrder) {
        orderService.saveNewOrder(newOrder);
        return HttpStatus.OK;
    }
}
