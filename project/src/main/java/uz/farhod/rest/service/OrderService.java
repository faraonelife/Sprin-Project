package uz.farhod.rest.service;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import uz.farhod.rest.entity.*;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.payload.OrderDto;
import uz.farhod.rest.repository.CustomerRepository;
import uz.farhod.rest.repository.DetailRepository;
import uz.farhod.rest.repository.InvoiceRepository;
import uz.farhod.rest.repository.OrderRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DetailRepository detailRepository;
    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private CustomerRepository CustomersRepository;

    public ApiResponse getOrderDetailsById(Integer orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent())
            return new ApiResponse("Order with this id Not Found!", false);
        Order order = optionalOrder.get();

        Optional<Detail> optionalDetail = detailRepository.findByOrderId(orderId);
        if (!optionalDetail.isPresent())
            return new ApiResponse("Order with this id doesnt have Details in system!", false);
        Detail detail = optionalDetail.get();
        Product product = detail.getProduct();
        return new ApiResponse("Order and Product details \n Product name: "+product.getName(), true, order);
    }

    public ApiResponse makeOrder(OrderDto orderDto){
        Order order = new Order();
        order.setDate(orderDto.getDate());
        Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustomerId());
        if (!optionalCustomer.isPresent())
            return new ApiResponse("Customer with this id Not Found!", false);
        order.setCustomer(optionalCustomer.get());
        Order savedOrder = orderRepository.save(order);
        return new ApiResponse("Order saved successfully", true, savedOrder);
    }

    public ApiResponse ordersWithoutDetails() throws ParseException {
        List<Order> ordersWithoutDetailsList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date checkingDate = format.parse("06/09/2016");
        for (Order order : orderList) {
            Optional<Detail> optionalDetail = detailRepository.findByOrderId(order.getId());
            if (!optionalDetail.isPresent()){
                if (order.getDate().before(checkingDate)){
                    ordersWithoutDetailsList.add(order);
                }
            }
        }
        return new ApiResponse("Orders without Details and Before 6 September 2016",true, ordersWithoutDetailsList);
    }

    public ApiResponse ordersWithoutInvoices(){
        List<Order> orderWithoutInvoiceList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            Optional<Invoice> optionalInvoice = invoiceRepository.findByOrderId(order.getId());
            if (!optionalInvoice.isPresent())
                orderWithoutInvoiceList.add(order);
        }
        return new ApiResponse("Orders without Invoices",true,orderWithoutInvoiceList);
    }

}
