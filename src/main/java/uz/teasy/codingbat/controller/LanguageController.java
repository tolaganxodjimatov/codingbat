package uz.teasy.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.Language;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.AbstractMsging;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.LanguageDTO;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class LanguageController extends AbstractMsging {
    @Autowired
    LanguageService languageService;

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody LanguageDTO languageDTO) {
        ApiResponse apiResponse = languageService.add(languageDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Language> languageList = languageService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(languageList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody LanguageDTO languageDTO) {
        ApiResponse apiResponse = languageService.edit(id, languageDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
