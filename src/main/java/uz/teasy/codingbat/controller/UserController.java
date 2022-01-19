package uz.teasy.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.AbstractMsging;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractMsging {
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.add(userDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<User> userList = userService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.edit(id, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
