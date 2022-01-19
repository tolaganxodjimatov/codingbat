package uz.teasy.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.Answer;
import uz.teasy.codingbat.entity.Task;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.AbstractMsging;
import uz.teasy.codingbat.payload.AnswerDTO;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.TaskDTO;
import uz.teasy.codingbat.service.AnswerService;
import uz.teasy.codingbat.service.TaskService;
import uz.teasy.codingbat.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController extends AbstractMsging {
    @Autowired
    AnswerService answerService;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody AnswerDTO answerDTO) {
        ApiResponse apiResponse = answerService.add(answerDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Answer> answerList = answerService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(answerList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody AnswerDTO answerDTO) {
        ApiResponse apiResponse = answerService.edit(id, answerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = answerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
