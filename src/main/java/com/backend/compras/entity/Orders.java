/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.compras.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name", length = 255, nullable = false)
    private String  name;
    
    @Column(name = "date")
    private Date date; //AAAA-mm-dd
 
    @Column(name = "shippingAddress", length = 255, nullable = false)
    private String shippingAddress;
    
    @Column(name = "city", length = 255, nullable = false)
    private String city;
    
    @Column(name = "delivery")
    private boolean delivery;
    
    @Column(name = "store_id")
    private long store_id;
    
    @Column(name = "store_name")
    private String store_name;
    
    public Orders(long id, String name, Date date, String shippingAddress, String city, boolean delivery, long store_id, String store_name) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
        this.shippingAddress = shippingAddress;
        this.city = city;
        this.delivery = delivery;
        this.store_id = store_id;
        this.store_name = store_name;
    }
}
