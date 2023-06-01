package com.omerfarukozcan.repository;

import com.omerfarukozcan.entity.OrderModel;
import com.omerfarukozcan.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findAllByEmailOrderByUpdatedAtDesc(String email);

    List<OrderModel> findAllBySessionIdOrderByUpdatedAtDesc(String sessionId);

    List<OrderModel> findAllByOrderCodeAndStatusOrderByUpdatedAtDesc(String orderCode, Status status);

    List<OrderModel> findAllByStatusOrderByUpdatedAtDesc(Status status);

}
