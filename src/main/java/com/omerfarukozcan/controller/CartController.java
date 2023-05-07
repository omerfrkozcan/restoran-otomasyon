package com.omerfarukozcan.controller;

import com.omerfarukozcan.model.AddToChartRequest;
import com.omerfarukozcan.model.AddToChartResponse;
import com.omerfarukozcan.model.CheckoutRequest;
import com.omerfarukozcan.model.CheckoutResponse;
import com.omerfarukozcan.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return service.prepareModelForCart(model);
    }

    @PostMapping("/add-to-cart")
    @ResponseBody
    public AddToChartResponse addToCart(@ModelAttribute("contactRequest") AddToChartRequest request) {
        return service.checkAndAdd(request);
    }

}
