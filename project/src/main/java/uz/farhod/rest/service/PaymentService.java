package uz.farhod.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.farhod.rest.entity.Invoice;
import uz.farhod.rest.entity.Payment;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.payload.PaymentDto;
import uz.farhod.rest.repository.InvoiceRepository;
import uz.farhod.rest.repository.PaymentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public ApiResponse getPaymentDetailsById(Integer paymentId){
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (!optionalPayment.isPresent())
            return new ApiResponse("Payment with this id Not Found!", false);
        Payment payment = optionalPayment.get();
        return new ApiResponse("Payment Details: ", true, payment);
    }

    public ApiResponse makePayment(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (!optionalInvoice.isPresent())
            return new ApiResponse("Invoice with this id Not Found!", false);
        payment.setInvoice(optionalInvoice.get());
        Payment saved = paymentRepository.save(payment);
        return new ApiResponse("Payment saved successfully", true, saved);
    }
}
