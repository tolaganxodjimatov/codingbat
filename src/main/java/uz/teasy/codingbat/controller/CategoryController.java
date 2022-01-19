package uz.teasy.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.Category;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.AbstractMsging;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.CategoryDTO;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends AbstractMsging {
    @Autowired
    CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody CategoryDTO categoryDTO) {
        ApiResponse apiResponse = categoryService.add(categoryDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Category> categoryList = categoryService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        ApiResponse apiResponse = categoryService.edit(id, categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
