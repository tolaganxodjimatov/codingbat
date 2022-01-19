package uz.teasy.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class TaskDTO {


    private Integer id;
    @NotNull(message = "name - can't be empty!")
    private String name;
    @NotNull(message = "text - can't be empty!")
    private String text;
    @NotNull(message = "solution - can't be empty!")
    private String solution;
    @NotNull(message = "hint - can't be empty!")
    private String hint;
    @NotNull(message = "method - can't be empty!")
    private String method;
    @NotNull(message = "has_star - can't be empty!")
    private String has_star;
    @NotNull(message = "name - can't be empty!")

    private Integer language_id;


}
