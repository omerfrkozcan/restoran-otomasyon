package com.omerfarukozcan.service;

import com.omerfarukozcan.controller.RequestUtil;
import com.omerfarukozcan.entity.CartModel;
import com.omerfarukozcan.entity.UrunModel;
import com.omerfarukozcan.model.AddToChartRequest;
import com.omerfarukozcan.model.AddToChartResponse;
import com.omerfarukozcan.model.CartItem;
import com.omerfarukozcan.repository.CartRepository;
import com.omerfarukozcan.repository.OrderRepository;
import com.omerfarukozcan.repository.UrunRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.omerfarukozcan.util.CommonUtil.izNotNull;

@Service
public class CartService {

    private final CartRepository repository;
    private final UrunRepository urunRepository;
    private final OrderRepository orderRepository;

    private final RequestUtil requestUtil;

    public CartService(CartRepository repository, UrunRepository urunRepository, OrderRepository orderRepository, RequestUtil requestUtil) {
        this.repository = repository;
        this.urunRepository = urunRepository;
        this.orderRepository = orderRepository;
        this.requestUtil = requestUtil;
    }

    public AddToChartResponse checkAndAdd(AddToChartRequest request) {

        final String sessionId = requestUtil.getSessionId();
        CartModel oldCart = repository.getFirstByUrunIdAndSessionId(request.getUrunId(), request.getSessionId());
        if (izNotNull(oldCart) && izNotNull(oldCart.getOrderId())) oldCart = null;

        final CartModel cart = izNotNull(oldCart) ? oldCart : new CartModel();

        cart.setUrunId(request.getUrunId());
        cart.setCount(cart.getCount() + 1);
        cart.setSessionId(sessionId);

        repository.save(cart);

        final List<CartModel> carts = findAllBySessionId();
        final List<CartItem> items = cartItems(carts);
        final int cartsSize = cartsSize(items);

        final AddToChartResponse response = new AddToChartResponse(true);
        response.setCartsSize(cartsSize);

        return response;
    }

    public List<CartModel> findAllBySessionId() {
        final String sessionId = requestUtil.getSessionId();

        return findAll(sessionId);
    }

    private List<CartModel> findAll(String sessionId) {
        return repository.findAllBySessionId(sessionId);
    }

    public String prepareModelForCart(Model model) {
        final List<CartModel> carts = findAllBySessionId();
        final List<CartItem> items = cartItems(carts);
        final int cartsSize = cartsSize(items);

        model.addAttribute("carts", items);
        model.addAttribute("cartsTotalPrice", itemsTotalPrice(items));
        model.addAttribute("cartsSize", String.valueOf(cartsSize));

        return "cart";
    }

    private int cartsSize(List<CartItem> carts) {
        return carts.stream().map(CartItem::getCount).reduce(0, Integer::sum);
    }

    private BigDecimal itemsTotalPrice(List<CartItem> items) {
        return items.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<CartItem> cartItems(List<CartModel> carts) {
        List<CartItem> items = new ArrayList<>();
        for (CartModel cart : carts) {

            if (izNotNull(cart.getOrderId())) continue;

            CartItem item = new CartItem();
            item.setId(cart.getId());
            item.setCount(cart.getCount());
            item.setSessionId(cart.getSessionId());

            final Long urunId = cart.getUrunId();
            final UrunModel urun = urunRepository.getReferenceById(urunId);

            item.setUrunId(urunId);
            item.setUrunName(urun.getName());
            item.setUrunCategory(urun.getCategory());
            item.setUrunPrice(urun.getPrice());
            item.setTotalPrice(urun.getPrice().multiply(new BigDecimal(cart.getCount())));
            item.setUrunDescription(urun.getDescription());
            item.setUrunImage(urun.getImage());

            items.add(item);
        }

        return items;
    }

}
