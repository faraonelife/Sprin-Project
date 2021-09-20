package uz.farhod.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.farhod.rest.entity.Detail;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    Optional<Detail> findByOrderId(Integer orderId);


}
