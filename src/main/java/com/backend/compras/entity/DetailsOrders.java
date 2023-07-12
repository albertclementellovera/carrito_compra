package com.backend.compras.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "details")
public class DetailsOrders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "order_id", length = 11, nullable = false)
    private long orderId;
    
    @Column(name = "product_name", length = 250, nullable = false)
    private String productName;
    
    @Column(name = "quantity", length = 11, nullable = false)
    private long quantity;
    
    @Column(name = "product_id", length = 11, nullable = false)
    private long productId;


    public DetailsOrders(long id, long orderId, String productName, long quantity, long productId) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.productName = productName;
        this.productId = productId;
    }
}