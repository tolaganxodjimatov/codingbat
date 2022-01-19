package uz.teasy.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class ExampleDTO {

    @NotNull(message = "name - can't be empty!")
    private String text;

    @NotNull(message = "name - can't be empty!")
    private Integer task_id;
}
