package uz.farhod.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.service.DetailService;
import uz.farhod.rest.service.OrderService;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("others")
public class ProjController {

    private final OrderService orderService;
    private final DetailService detailService;


    @GetMapping("/orders_without_details")
    public HttpEntity<?> getOrdersWithoutDetails() throws ParseException {
        ApiResponse apiResponse = orderService.ordersWithoutDetails();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/number_of_products_in_year")
    public HttpEntity<?> getProductsInYear() throws ParseException {
        ApiResponse apiResponse = detailService.numberOfProductsInOneYear();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/orders_without_invoices")
    public HttpEntity<?> getOrdersWithoutInvoices(){
        ApiResponse apiResponse = orderService.ordersWithoutInvoices();
        return ResponseEntity.ok(apiResponse);
    }

}
