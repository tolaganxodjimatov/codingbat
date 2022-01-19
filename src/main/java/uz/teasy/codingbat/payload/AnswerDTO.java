package uz.teasy.codingbat.payload;

import lombok.Data;
import uz.teasy.codingbat.entity.Task;
import uz.teasy.codingbat.entity.User;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class AnswerDTO {

    @NotNull(message = "text - can't be empty!")
    private String text;

    @NotNull(message = "task_id - can't be empty!")
    private Integer task_id;

    @NotNull(message = "user_id - can't be empty!")
    private Integer user_id;

    @NotNull(message = "is_correct - can't be empty!")
    private boolean isCorrect;

}
