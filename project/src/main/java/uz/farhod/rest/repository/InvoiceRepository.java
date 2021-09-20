package uz.farhod.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.farhod.rest.entity.Invoice;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findByOrderId(Integer orderId);
}
