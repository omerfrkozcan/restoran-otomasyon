package com.omerfarukozcan.controller;

import com.omerfarukozcan.model.CheckoutRequest;
import com.omerfarukozcan.model.CheckoutResponse;
import com.omerfarukozcan.model.OrderItem;
import com.omerfarukozcan.service.CartService;
import com.omerfarukozcan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.omerfarukozcan.util.CommonUtil.*;

@Slf4j
@Controller
public class OrderController {

    private final OrderService service;
    private final CartService cartService;

    public OrderController(OrderService service, CartService cartService) {
        this.service = service;
        this.cartService = cartService;
    }

    @GetMapping("/orders")
    public String orders(@RequestParam(name = "orderCode", required = false) String orderCode, @ModelAttribute("orderItem") OrderItem orderItem, Model model) {

        model.addAttribute("orderCodeParam", orderCode);
        if (izBlank(orderCode)) {
            if (izNotNull(orderItem) && izNotBlank(orderItem.getOrderCode())) orderCode = orderItem.getOrderCode();
        }

        cartService.prepareModelForCart(model);
        model.addAttribute("orders", service.orders(orderCode));

        return "orders";
    }

    @PostMapping("/checkout")
    @ResponseBody
    public CheckoutResponse checkout(@ModelAttribute("checkoutRequest") CheckoutRequest request) {
        return service.checkout(request);
    }

}
