package uz.teasy.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.teasy.codingbat.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);

}
