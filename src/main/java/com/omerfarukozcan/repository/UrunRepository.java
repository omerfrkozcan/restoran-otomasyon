package com.omerfarukozcan.repository;

import com.omerfarukozcan.entity.UrunModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrunRepository extends JpaRepository<UrunModel, Long> {

    List<UrunModel> findAllByOrderByUpdatedAtDesc();

    List<UrunModel> findAllByCategoryOrderByUpdatedAtDesc(String category);

}
