package uz.teasy.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.teasy.codingbat.entity.Answer;
import uz.teasy.codingbat.entity.Task;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
