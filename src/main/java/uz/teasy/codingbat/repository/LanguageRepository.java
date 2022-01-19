package uz.teasy.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.teasy.codingbat.entity.Language;

@Repository
public interface  LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);

}
