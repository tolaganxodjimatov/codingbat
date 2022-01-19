package uz.teasy.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.codingbat.entity.Answer;
import uz.teasy.codingbat.entity.Language;
import uz.teasy.codingbat.entity.Task;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.AnswerDTO;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.TaskDTO;
import uz.teasy.codingbat.repository.AnswerRepository;
import uz.teasy.codingbat.repository.LanguageRepository;
import uz.teasy.codingbat.repository.TaskRepository;
import uz.teasy.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;



    public ApiResponse add(AnswerDTO answerDTO) {

        Optional<Task> optionalTask = taskRepository.findById(answerDTO.getTask_id());
        if (!optionalTask.isPresent()) return new ApiResponse("Task - does not exists!", false);

        Optional<User> optionalUser = userRepository.findById(answerDTO.getUser_id());
        if (!optionalUser.isPresent()) return new ApiResponse("User - does not exists!", false);

        Answer answer = new Answer(null,answerDTO.getText(),optionalTask.get(),optionalUser.get(),true);

        answerRepository.save(answer);
        return new ApiResponse("Answer - edited OK!", true);

    }

    public List<Answer> getList() {
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }

    public ApiResponse edit(Integer id, AnswerDTO answerDTO) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (!answerOptional.isPresent()) return new ApiResponse("Answer - does not exists!", false);

        Optional<Task> optionalTask = taskRepository.findById(answerDTO.getTask_id());
        if (!optionalTask.isPresent()) return new ApiResponse("Task - does not exists!", false);

        Optional<User> optionalUser = userRepository.findById(answerDTO.getUser_id());
        if (!optionalUser.isPresent()) return new ApiResponse("User - does not exists!", false);

        Answer answerToEdt = answerOptional.get();
        answerToEdt.setText(answerDTO.getText());
        answerToEdt.setTask(optionalTask.get());
        answerToEdt.setUser(optionalUser.get());
        answerToEdt.setCorrect(answerDTO.isCorrect());

        answerRepository.save(answerToEdt);
        return new ApiResponse("Answer - edited OK!", true);

    }

    public ApiResponse delete(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Answer - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Answer - not deleted!", false);
        }

    }

}
