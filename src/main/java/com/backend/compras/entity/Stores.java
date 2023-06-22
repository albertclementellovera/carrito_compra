/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.compras.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stores")
public class Stores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name", length = 255, nullable = false)
    private String  name;
    
    @Column(name = "address" ,nullable = false)
    private String  address;
 
    @Column(name = "city", length = 255, nullable = false)
    private String city;
    
    @Column(name = "openingHours", length = 255, nullable = false)
    private String openingHours;
}