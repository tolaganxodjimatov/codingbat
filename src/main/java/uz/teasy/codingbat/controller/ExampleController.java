package uz.teasy.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.Example;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.AbstractMsging;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.ExampleDTO;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.service.ExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController extends AbstractMsging {
    @Autowired
    ExampleService exampleService;

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody ExampleDTO exampleDTO) {
        ApiResponse apiResponse = exampleService.add(exampleDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Example> exampleList = exampleService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(exampleList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody ExampleDTO exampleDTO) {
        ApiResponse apiResponse = exampleService.edit(id, exampleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = exampleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
