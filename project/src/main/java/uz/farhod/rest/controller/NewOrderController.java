package uz.farhod.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.payload.OrderDto;
import uz.farhod.rest.service.OrderService;

@RestController
@RequestMapping("/order")
public class NewOrderController {

    @Autowired
    private OrderService OrderService;

    @GetMapping("/detail")
    public HttpEntity<?> getOrderDetailsById(@RequestParam Integer orderId){
        ApiResponse apiResponse = OrderService.getOrderDetailsById(orderId);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> makeOrder(@RequestBody OrderDto orderDto){
        ApiResponse apiResponse = OrderService.makeOrder(orderDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:401).body(apiResponse);
    }
}
