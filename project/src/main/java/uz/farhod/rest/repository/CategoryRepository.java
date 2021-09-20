package uz.farhod.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.farhod.rest.entity.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
