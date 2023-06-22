package com.backend.compras.repository;

import com.backend.compras.entity.Products;
import com.backend.compras.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Stores, Long>{
    
}
