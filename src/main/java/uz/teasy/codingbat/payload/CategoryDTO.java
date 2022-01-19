package uz.teasy.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.teasy.codingbat.entity.Language;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data

public class CategoryDTO {
    private Integer id;
    @NotNull(message = "name - can't be empty!")
    private String name;
    @NotNull(message = "description - can't be empty!")
    private String description;

    @NotNull(message = "language_id - can't be empty!")
    private Integer language_id;

}
