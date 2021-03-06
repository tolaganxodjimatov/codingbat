package uz.teasy.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApiResponse add(UserDTO userDTO) {
        boolean exists = userRepository.existsByEmail(userDTO.getEmail());
        if (exists) return new ApiResponse("User - already exists!", false);
        User user = new User(null, userDTO.getEmail(), userDTO.getPassword());
        userRepository.save(user);
        return new ApiResponse("User - saved OK!", true);
    }

    public List<User> getList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public ApiResponse edit(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("User - does not exists!", false);
        User user = optionalUser.get();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new ApiResponse("User - edited OK!", true);

    }

    public ApiResponse delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("User - not deleted!", false);
        }

    }

}
