package uz.teasy.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.codingbat.entity.Example;
import uz.teasy.codingbat.entity.Task;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.ExampleDTO;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.repository.ExampleRepository;
import uz.teasy.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;

    public ApiResponse add(ExampleDTO exampleDTO) {
        boolean exists = exampleRepository.existsByText(exampleDTO.getText());
        if (exists) return new ApiResponse("Example - already exists!", false);

        Optional<Task> optionalTask = taskRepository.findById(exampleDTO.getTask_id());
        if (!optionalTask.isPresent()) return new ApiResponse("Task - does not exists!", false);

        Example example = new Example(null, exampleDTO.getText(), optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example - saved OK!", true);
    }

    public List<Example> getList() {
        List<Example> exampleList = exampleRepository.findAll();
        return exampleList;
    }

    public ApiResponse edit(Integer id, ExampleDTO exampleDTO) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()) return new ApiResponse("Example - does not exists!", false);

        Optional<Task> optionalTask = taskRepository.findById(exampleDTO.getTask_id());
        if (!optionalTask.isPresent()) return new ApiResponse("Task - does not exists!", false);

        Example example = optionalExample.get();
        example.setText(exampleDTO.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);

        return new ApiResponse("Example - edited OK!", true);

    }

    public ApiResponse delete(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("Example - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Example - not deleted!", false);
        }

    }


}
