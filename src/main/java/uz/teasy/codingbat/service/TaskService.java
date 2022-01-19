package uz.teasy.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.codingbat.entity.Category;
import uz.teasy.codingbat.entity.Language;
import uz.teasy.codingbat.entity.Task;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.CategoryDTO;
import uz.teasy.codingbat.payload.TaskDTO;
import uz.teasy.codingbat.repository.LanguageRepository;
import uz.teasy.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    LanguageRepository languageRepository;


    public ApiResponse add(TaskDTO taskDTO) {

        Optional<Language> optionalLanguage = languageRepository.findById(taskDTO.getLanguage_id());
        if (!optionalLanguage.isPresent()) return new ApiResponse("Language - does not exists!", false);

        Task task = new Task(null, taskDTO.getName(), taskDTO.getText(), taskDTO.getSolution(), taskDTO.getHint(), taskDTO.getMethod(), taskDTO.getHas_star(), optionalLanguage.get());

        taskRepository.save(task);
        return new ApiResponse("Task - saved OK!", true);
    }

    public List<Task> getList() {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    public ApiResponse edit(Integer id, TaskDTO taskDTO) {

        Optional<Task> optional = taskRepository.findById(id);
        if (!optional.isPresent()) return new ApiResponse("Task - does not exists!", false);

        Optional<Language> optionalLanguage = languageRepository.findById(taskDTO.getLanguage_id());
        if (!optionalLanguage.isPresent()) return new ApiResponse("Language - does not exists!", false);

        Task taskToEDit = optional.get();
        taskToEDit.setName(taskDTO.getName());
        taskToEDit.setText(taskDTO.getText());
        taskToEDit.setSolution(taskDTO.getSolution());
        taskToEDit.setHint(taskDTO.getHint());
        taskToEDit.setMethod(taskDTO.getMethod());
        taskToEDit.setHas_star(taskDTO.getHas_star());
        taskToEDit.setLanguage(optionalLanguage.get());

        taskRepository.save(taskToEDit);
        return new ApiResponse("Task - edited OK!", true);

    }

    public ApiResponse delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Category - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Category - not deleted!", false);
        }

    }

}
