package uz.farhod.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.farhod.rest.entity.Detail;
import uz.farhod.rest.entity.Order;
import uz.farhod.rest.entity.Product;
import uz.farhod.rest.payload.ApiResponse;
import uz.farhod.rest.repository.DetailRepository;
import uz.farhod.rest.repository.OrderRepository;
import uz.farhod.rest.repository.ProductRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ApiResponse numberOfProductsInOneYear() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = format.parse("01/01/2016");
        Date endDate = format.parse("31/12/2016");

        List<Product> productsInYearList = new ArrayList<>();

        List<Detail> detailList = detailRepository.findAll();
        for (Detail detail : detailList) {
            Order order = detail.getOrder();
            Date date = order.getDate();
            if (endDate.after(date) && startDate.before(date)) {
                Product product = detail.getProduct();
                productsInYearList.add(product);
            }
        }
        return new ApiResponse("Products ordered in 2016 year", true, productsInYearList);
    }
}

