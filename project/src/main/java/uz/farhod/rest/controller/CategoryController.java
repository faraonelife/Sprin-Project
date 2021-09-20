package uz.farhod.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.farhod.rest.entity.Category;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public HttpEntity<?> getAllProductsCategoryLists(){
        List<Category> allProductsCategoryLists = categoryService.getAllProductsCategoryLists();
        return ResponseEntity.ok(allProductsCategoryLists);
    }

    @GetMapping
    public HttpEntity<?> getProductCategoryById(@RequestParam Integer productId){
        ApiResponse apiResponse = categoryService.getProductCategoryById(productId);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse.getObject());
    }
}
