package uz.teasy.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.teasy.codingbat.entity.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example,Integer> {
    boolean existsByText(String text);
}
