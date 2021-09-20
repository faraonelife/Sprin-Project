package uz.farhod.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.payload.PaymentDto;
import uz.farhod.rest.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/detail")
    public HttpEntity<?> getPaymentDetailsById(@RequestParam Integer paymentId){
        ApiResponse apiResponse = paymentService.getPaymentDetailsById(paymentId);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> makePayment(@RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = paymentService.makePayment(paymentDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:401).body(apiResponse);
    }
}
