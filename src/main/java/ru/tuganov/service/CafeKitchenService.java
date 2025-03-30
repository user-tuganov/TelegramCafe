package ru.tuganov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.tuganov.entity.CafeAddress;
import ru.tuganov.entity.CafeEmployee;
import ru.tuganov.entity.order.CafeOrder;
import ru.tuganov.service.security.CustomUserDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeKitchenService {
    private final OrderService orderService;

    private CafeEmployee getCurrentCafeEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return ((CustomUserDetails) authentication.getPrincipal()).getCafeEmployee();
        }
        return null;
    }

    public List<CafeOrder> getCafeOrders() {
        CafeEmployee cafeEmployee = getCurrentCafeEmployee();
        if (cafeEmployee != null) {
            return orderService.getCafeOrders(cafeEmployee.getCafeAddress());
        }
        return List.of();
    }

    public String currenAddress() {
        CafeEmployee cafeEmployee = getCurrentCafeEmployee();
        if (cafeEmployee != null) {
            return cafeEmployee.getCafeAddress().getAddress();
        }
        return "";
    }
}
