package com.omerfarukozcan.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttr {

    private final RequestUtil requestUtil;

    public GlobalModelAttr(RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
    }


    @ModelAttribute("sessionId")
    public String sessionId() {
        return requestUtil.getSessionId();
    }

    @ModelAttribute("title")
    public String title() {
        return "Restoran Otomasyon | Ömer Faruk Özcan";
    }

}
