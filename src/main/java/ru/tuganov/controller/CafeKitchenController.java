package ru.tuganov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tuganov.dto.OrderStatusDto;
import ru.tuganov.entity.order.CafeOrder;
import ru.tuganov.service.CafeKitchenService;
import ru.tuganov.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
public class CafeKitchenController {
    private final CafeKitchenService cafeKitchenService;
    private final OrderService orderService;

    @GetMapping("/get-orders")
    public ResponseEntity<List<CafeOrder>> getOrders() {
        return ResponseEntity.ok(cafeKitchenService.getCafeOrders());
    }

    @PostMapping("/set-status")
    public HttpStatus setStatus(@RequestBody OrderStatusDto orderStatusDto) {
        orderService.setOrderStatus(orderStatusDto);
        return HttpStatus.OK;
    }

    @GetMapping("/get-address")
    public ResponseEntity<String> getCurrentAddress() {
        return ResponseEntity.ok(cafeKitchenService.currenAddress());
    }
}
