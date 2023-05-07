package com.omerfarukozcan.service;

import com.omerfarukozcan.controller.RequestUtil;
import com.omerfarukozcan.entity.CartModel;
import com.omerfarukozcan.entity.OrderModel;
import com.omerfarukozcan.entity.Status;
import com.omerfarukozcan.model.CheckoutRequest;
import com.omerfarukozcan.model.CheckoutResponse;
import com.omerfarukozcan.model.OrderItem;
import com.omerfarukozcan.repository.CartRepository;
import com.omerfarukozcan.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.omerfarukozcan.entity.Status.*;
import static com.omerfarukozcan.util.CommonUtil.*;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final CartRepository cartRepository;
    private final RequestUtil requestUtil;

    public OrderService(OrderRepository repository, CartRepository cartRepository, RequestUtil requestUtil) {
        this.repository = repository;
        this.cartRepository = cartRepository;
        this.requestUtil = requestUtil;
    }

    public List<OrderItem> orders(String orderCode) {

        if (izBlank(orderCode)) return toItems(repository.findAllBySessionIdOrderByUpdatedAtDesc(requestUtil.getSessionId()));

        return toItems(repository.findAllByOrderCodeOrderByUpdatedAtDesc(orderCode));
    }

    private List<OrderItem> toItems(List<OrderModel> orders) {
        final List<OrderItem> items = new ArrayList<>();

        if (izEmpty(orders)) return items;

        for (OrderModel order : orders) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setOrderCode(order.getOrderCode());
            item.setOrderDate(DATE_FORMATTER.format(order.getCreatedAt()));
            item.setName(order.getName());
            item.setEmail(order.getEmail());
            item.setNote(order.getNote());
            item.setStatus(order.getStatus().name());
            item.setStatusDesc(order.getStatus().getDesc());

            items.add(item);
        }

        return items;
    }

    public CheckoutResponse checkout(CheckoutRequest request) {

        if (izBlank(request.getName()) || izBlank(request.getEmail())) {
            return new CheckoutResponse();
        }

        final String orderCode = getOrderCode();
        final String sessionId = requestUtil.getSessionId();
        final List<CartModel> carts = cartRepository.findAllBySessionId(sessionId);

        final List<CartModel> freshCarts = carts.stream().filter(c -> izNull(c.getOrderId())).collect(toList());
        if (izEmpty(freshCarts)) return new CheckoutResponse();

        OrderModel order = new OrderModel();
        order.setOrderCode(orderCode);
        order.setName(request.getName());
        order.setEmail(request.getEmail());
        order.setNote(request.getNote());
        order.setSessionId(sessionId);

        order = repository.save(order);
        final Long orderId = order.getId();

        for (CartModel cart : freshCarts) {
            cart.setOrderId(orderId);
            cartRepository.save(cart);
        }

        return new CheckoutResponse(orderCode);
    }

    private String getOrderCode() {
        return format("%06x", (long) (Math.random() * (0xfffffffL + 1L))).toUpperCase();
    }

    public List<OrderItem> activeOrders(String orderCode) {
        return getOrderItems(orderCode, ACTIVE);
    }

    public void changeStatus(String orderId, String status) {
        final OrderModel order = repository.getReferenceById(Long.valueOf(orderId));
        order.setStatus(Status.valueOf(status));

        repository.save(order);
    }

    public List<OrderItem> deletedOrders(String orderCode2) {
        return getOrderItems(orderCode2, DELETED);
    }

    public List<OrderItem> completedOrders(String orderCode3) {
        return getOrderItems(orderCode3, COMPLETED);
    }

    private List<OrderItem> getOrderItems(String orderCode, Status status) {
        if (izBlank(orderCode)) return toItems(repository.findAllByStatusOrderByUpdatedAtDesc(status));

        return toItems(repository.findAllByOrderCodeAndStatusOrderByUpdatedAtDesc(orderCode, status));
    }

}
