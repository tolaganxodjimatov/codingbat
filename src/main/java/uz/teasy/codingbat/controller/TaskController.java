package uz.teasy.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.Category;
import uz.teasy.codingbat.entity.Task;
import uz.teasy.codingbat.payload.AbstractMsging;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.CategoryDTO;
import uz.teasy.codingbat.payload.TaskDTO;
import uz.teasy.codingbat.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController extends AbstractMsging {
    @Autowired
    TaskService taskService;

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.add(taskDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Task> categoryList = taskService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.edit(id, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = taskService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
