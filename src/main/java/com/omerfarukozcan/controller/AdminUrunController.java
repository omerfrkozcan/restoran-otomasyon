package com.omerfarukozcan.controller;

import com.omerfarukozcan.entity.UrunModel;
import com.omerfarukozcan.model.UrunSaveRequest;
import com.omerfarukozcan.model.UrunSaveResponse;
import com.omerfarukozcan.service.UrunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class AdminUrunController {

    private final UrunService service;
    private final RequestUtil requestUtil;

    public AdminUrunController(UrunService service, RequestUtil requestUtil) {
        this.service = service;
        this.requestUtil = requestUtil;
    }


    @GetMapping("/admin/products")
    public String products(Model model) {

        if (requestUtil.izNotLoggedIn()) {
            return "redirect:/admin/login";
        }

        model.addAttribute("urunler", service.findAll());

        return "products";
    }

    @PostMapping("/admin/products/save")
    @ResponseBody
    public UrunSaveResponse urunSave(@ModelAttribute("urunSaveRequest") UrunSaveRequest request) {

        if (requestUtil.izNotLoggedIn()) {
            throw new RuntimeException("Not logged in");
        }

        return service.save(request);
    }

    @PostMapping("/admin/products/{urunId}")
    @ResponseBody
    public UrunSaveResponse urunSave(@PathVariable Long urunId) {

        if (requestUtil.izNotLoggedIn()) {
            throw new RuntimeException("Not logged in");
        }

        return service.get(urunId);
    }

    @PostMapping("/admin/products/remove")
    @ResponseBody
    public UrunSaveResponse removeSave(@ModelAttribute("urunSaveRequest") UrunSaveRequest request) {

        if (requestUtil.izNotLoggedIn()) {
            throw new RuntimeException("Not logged in");
        }

        return service.remove(request);
    }

}
