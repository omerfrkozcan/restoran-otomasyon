package com.omerfarukozcan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class RequestUtil {

    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private final HttpServletRequest request;

    public RequestUtil(HttpServletRequest request) {
        this.request = request;
    }

    public String getSessionId() {
        return request.getSession(true).getId();
    }

    public boolean izNotLoggedIn() {
        return !izLoggedIn();
    }

    public boolean izLoggedIn() {
        try {
            final HttpSession session = request.getSession();
            return (boolean) session.getAttribute(IS_LOGGED_IN);
        } catch (Exception e) {
            return false;
        }
    }

    public void setLoggedIn(boolean success) {
        try {
            final HttpSession session = request.getSession();
            session.setAttribute(IS_LOGGED_IN, success);
        } catch (Exception e) {
            log.error("setLoggedIn errorz", e);
        }
    }

    public void logout() {
        setLoggedIn(false);
    }

}
