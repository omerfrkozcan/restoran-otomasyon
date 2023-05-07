package com.omerfarukozcan.controller;

import com.omerfarukozcan.model.OrderItem;
import com.omerfarukozcan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import static com.omerfarukozcan.util.CommonUtil.*;

@Slf4j
@Controller
public class AdminController {

    private final OrderService service;
    private final RequestUtil requestUtil;

    public AdminController(OrderService service, RequestUtil requestUtil) {
        this.service = service;
        this.requestUtil = requestUtil;
    }

    @GetMapping("/admin")
    public String orders(@RequestParam(name = "orderCode", required = false) String orderCode,
                         @ModelAttribute("orderItem") OrderItem orderItem, Model model) {
        return adminOrders(orderCode, null, null, null, null, orderItem, model);
    }

    @GetMapping("/admin/orders")
    public String adminOrders(@RequestParam(name = "orderCode", required = false) String orderCode,
                              @RequestParam(name = "orderCode2", required = false) String orderCode2,
                              @RequestParam(name = "orderCode3", required = false) String orderCode3,
                              @RequestParam(name = "orderId", required = false) String orderId,
                              @RequestParam(name = "status", required = false) String status,
                              @ModelAttribute("orderItem") OrderItem orderItem, Model model) {

        if (requestUtil.izNotLoggedIn()) {
            return "redirect:/admin/login";
        }

        if (izNotBlank(orderId) && izNotBlank(status)) {
            service.changeStatus(orderId, status);

            return "redirect:/admin/orders";
        }

        model.addAttribute("orderCodeParam1", orderCode);
        model.addAttribute("orderCodeParam2", orderCode2);
        model.addAttribute("orderCodeParam3", orderCode3);

        if (izBlank(orderCode)) {
            if (izNotNull(orderItem) && izNotBlank(orderItem.getOrderCode())) orderCode = orderItem.getOrderCode();
        }

        model.addAttribute("activeOrders", service.activeOrders(orderCode));
        model.addAttribute("deletedOrders", service.deletedOrders(orderCode2));
        model.addAttribute("completedOrders", service.completedOrders(orderCode3));

        return "admin-orders";
    }

}
