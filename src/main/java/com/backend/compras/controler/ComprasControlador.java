package com.backend.compras.controler;

import com.backend.compras.dto.ClientTokenDto;
import com.backend.compras.dto.PurchaseDto;
import com.backend.compras.entity.Categories;
import com.backend.compras.entity.DetailsOrders;
import com.backend.compras.entity.Orders;
import com.backend.compras.entity.Products;
import com.backend.compras.entity.Stores;
import com.backend.compras.exceptions.ResourceNotFoundException;
import com.backend.compras.repository.CategoriesRepository;
import com.backend.compras.repository.DetailsOrdersRepository;
import com.backend.compras.repository.OrdersRepository;
import com.backend.compras.repository.ProductsRepository;
import com.backend.compras.repository.StoreRepository;
import com.backend.compras.service.PaymentService;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static javassist.CtMethod.ConstParameter.string;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "http://localhost:4200")

public class ComprasControlador {
    
    @Autowired
    private ProductsRepository repoProducts;
    @Autowired
    private CategoriesRepository repoCategories;
    @Autowired
    private DetailsOrdersRepository repoDetailsOrders;
    @Autowired
    private OrdersRepository repoOrders;
    @Autowired
    private StoreRepository repoStores;
    @Autowired
    PaymentService paymentService;;

    @GetMapping({"/products", "/"})
    public List<Products> listarProductos() {
        return repoProducts.findAll();}
    
    @PostMapping("/products")
    public Products GuardarProducto(@RequestBody Products producto) {
        return repoProducts.save(producto);
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> obtenerProductoPorId(@PathVariable Long id){
      Products producto = repoProducts.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                  "No existe el producto con el Id : "+id));
      return ResponseEntity.ok(producto);
    }

    @PutMapping("/products/{id}")
    public Products actualizarProductosPorId(
         @PathVariable (value="id") Long id,
         @Valid @RequestBody Products detallesProducto){
     if (!repoProducts.existsById(id)) {
            throw new ResourceNotFoundException("Publicacion con el Id: " + id + " no encontrada");}
     return repoProducts.findById(id).map(producto -> {
            producto.setName(detallesProducto.getName());
            producto.setPrice(detallesProducto.getPrice());
            producto.setDescription(detallesProducto.getDescription());
            producto.setCategoryId(detallesProducto.getCategoryId());
            producto.setStock(detallesProducto.getStock());
            return repoProducts.save(producto);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "No existe el producto con el Id : "+id));  
    }
        
    @DeleteMapping("/products/{id}")
     public ResponseEntity<Map<String,Boolean>> eliminarProducto(@PathVariable Long id){
     Products producto = repoProducts.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                  "No existe el producto con el Id : "+id));

      repoProducts.delete(producto);
      Map<String, Boolean> respuesta = new HashMap<>();
      respuesta.put("eliminar",Boolean.TRUE);
      return ResponseEntity.ok(respuesta);
    }

    @GetMapping({"/categories"})
    public List<Categories> listarCategorias() {
        return repoCategories.findAll();}
    
    @PostMapping("/categories")
    public Categories GuardarCategoria(@RequestBody Categories categorias) {
        return repoCategories.save(categorias);
    }
    
    @GetMapping({"/details"})
    public List<DetailsOrders> listarDetalleOrdenes() {
        return repoDetailsOrders.findAll();}
    
    @PostMapping("/savedetails")
    public DetailsOrders GuardarDetailsOrders(@RequestBody DetailsOrders details) {
        return repoDetailsOrders.save(details);
    }
    
    @GetMapping({"/orders"})
    public List<Orders> listarOrdenes() {
        return repoOrders.findAll();}
    
    @PostMapping("/saveorder")
    public Orders GuardarOrdenes(@RequestBody Orders orders) {
        return repoOrders.save(orders);
    }
    
    @GetMapping({"/stores"})
    public List<Stores> listarTiendas() {
        return repoStores.findAll();}
    
    @PostMapping("/stores")
    public Stores GuardarTiendas(@RequestBody Stores stores) {
        return repoStores.save(stores);
    }
    
    @GetMapping("/token")
    public ResponseEntity<ClientTokenDto> getToken() {
        return ResponseEntity.ok(paymentService.getToken());
    }
    
    @PostMapping("/checkout")
    public ResponseEntity<Result<Transaction>> checkout(
        @RequestBody PurchaseDto dto) {
        return ResponseEntity.ok(paymentService.checkout(dto));
    }  
}

