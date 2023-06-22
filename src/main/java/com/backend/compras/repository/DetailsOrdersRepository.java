package com.backend.compras.repository;

import com.backend.compras.entity.DetailsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsOrdersRepository extends JpaRepository<DetailsOrders, Long>{
   
}
