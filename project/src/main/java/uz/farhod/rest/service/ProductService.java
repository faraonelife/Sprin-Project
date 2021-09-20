package uz.farhod.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.farhod.rest.entity.Product;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.repository.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProductsList(){
        return productRepository.findAll();
    }

    public ApiResponse getProductDetailsById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product with this id Not Found!", false);
        Product product = optionalProduct.get();
        return new ApiResponse("Product details: ", true, product);
    }

}
