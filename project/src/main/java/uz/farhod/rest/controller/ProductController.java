package uz.farhod.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.farhod.rest.entity.Product;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public HttpEntity<?> getProductsList(){
        List<Product> productsList = productService.getProductsList();
        return ResponseEntity.ok(productsList);
    }

    @GetMapping
    public HttpEntity<?> getProductDetailsById(@RequestParam Integer productId){
        ApiResponse apiResponse = productService.getProductDetailsById(productId);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse.getObject());
    }


}
