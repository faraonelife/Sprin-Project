package uz.farhod.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.farhod.rest.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
