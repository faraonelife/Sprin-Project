package uz.farhod.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.farhod.rest.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
