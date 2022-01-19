package uz.teasy.codingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private Integer id;
    @NotNull(message = "email - can't be empty!")
    private String email;

    @NotNull(message = "password - can't be empty!")
    private String password;
}
