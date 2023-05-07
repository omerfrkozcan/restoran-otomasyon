package com.omerfarukozcan.controller;

import com.omerfarukozcan.entity.CartModel;
import com.omerfarukozcan.service.CartService;
import com.omerfarukozcan.service.UrunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.omerfarukozcan.util.CommonUtil.izEmpty;

@Slf4j
@Controller
public class HomeController {

    private final UrunService urunService;
    private final CartService cartService;

    public HomeController(UrunService urunService, CartService cartService) {
        this.urunService = urunService;
        this.cartService = cartService;
    }


    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("kahvaltilar", urunService.findAllByCategory("Kahvaltı"));
        model.addAttribute("suluYemekler", urunService.findAllByCategory("Sulu Yemek"));
        model.addAttribute("kebaplar", urunService.findAllByCategory("Kebap"));

        cartService.prepareModelForCart(model);

        return "index";
    }

    @GetMapping("/index.xhtml")
    public String index(Model model) {
        return home(model);
    }

}
