package uz.teasy.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.teasy.codingbat.entity.Language;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.LanguageDTO;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;


    public ApiResponse add(LanguageDTO languageDTO) {
        boolean exists = languageRepository.existsByName(languageDTO.getName());
        if (exists) return new ApiResponse("Language - already exists!", false);
        Language language = new Language(null, languageDTO.getName());
        languageRepository.save(language);
        return new ApiResponse("Language - saved OK!", true);
    }

    public List<Language> getList() {
        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }

    public ApiResponse edit(Integer id, LanguageDTO languageDTO) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()) return new ApiResponse("Language - does not exists!", false);
        Language language = optionalLanguage.get();
        language.setName(languageDTO.getName());
        languageRepository.save(language);
        return new ApiResponse("Language - edited OK!", true);
    }

    public ApiResponse delete(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("User - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("User - not deleted!", false);
        }

    }


}
