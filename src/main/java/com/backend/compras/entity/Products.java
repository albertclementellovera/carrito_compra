package com.backend.compras.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name", length = 255, nullable = false)
    private String  name;
    
    @Column(name = "price" ,nullable = false)
    private long price;
 
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    
    @Column(name = "categoryId", length = 255, nullable = false)
    private long categoryId;
    
    @Column(name = "stock")
    private long stock;

    public void addAttribute(String productos, List<Products> ListarTodosLosProductos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Products(long id, String name, long price, String description, long categoryId, long stock) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.stock = stock;
    }
}
