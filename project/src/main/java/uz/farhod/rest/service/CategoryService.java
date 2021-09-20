package uz.farhod.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.farhod.rest.entity.Category;
import uz.farhod.rest.entity.Product;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.repository.CategoryRepository;
import uz.farhod.rest.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getAllProductsCategoryLists(){
        return categoryRepository.findAll();
    }

    public ApiResponse getProductCategoryById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product with this id Not Found!", false);
        Product product = optionalProduct.get();
        Category category = product.getCategory();
        return new ApiResponse("Category Details: ", true, category);
    }

}
