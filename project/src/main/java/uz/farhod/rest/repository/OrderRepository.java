package uz.farhod.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.farhod.rest.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
