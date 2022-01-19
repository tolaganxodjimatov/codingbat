package uz.teasy.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class LanguageDTO {
    private Integer id;
    @NotNull(message = "name - can't be empty!")
    private String name;

}
