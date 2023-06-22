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
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name", length = 255, nullable = false)
    private String  name;
    
    @Column(name = "date")
    private Date createdDate; //AAAA-mm-dd
 
    @Column(name = "shippingAddress", length = 255, nullable = false)
    private String shippingAddress;
    
    @Column(name = "city", length = 255, nullable = false)
    private String city;
    
    @Column(name = "pickup")
    private boolean pickup;
}
