package uz.teasy.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.codingbat.entity.Category;
import uz.teasy.codingbat.entity.Language;
import uz.teasy.codingbat.entity.User;
import uz.teasy.codingbat.payload.ApiResponse;
import uz.teasy.codingbat.payload.CategoryDTO;
import uz.teasy.codingbat.payload.UserDTO;
import uz.teasy.codingbat.repository.CategoryRepository;
import uz.teasy.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    LanguageRepository languageRepository;

    public ApiResponse add(CategoryDTO categoryDTO) {
        boolean exists = categoryRepository.existsByName(categoryDTO.getName());
        if (exists) return new ApiResponse("Category - already exists!", false);

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDTO.getLanguage_id());
        if (!optionalLanguage.isPresent()) return new ApiResponse("Language - does not exists!",false);

        Category category=new Category(null,categoryDTO.getName(),categoryDTO.getDescription(),optionalLanguage.get());
        categoryRepository.save(category);

        return new ApiResponse("Category - saved OK!", true);
    }

    public List<Category> getList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public ApiResponse edit(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResponse("Category - does not exists!", false);

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDTO.getLanguage_id());
        if (!optionalLanguage.isPresent()) return new ApiResponse("Language - does not exists!",false);

        Category categoryToEdt = optionalCategory.get();
        categoryToEdt.setName(categoryDTO.getName());
        categoryToEdt.setDescription(categoryDTO.getDescription());
        categoryToEdt.setLanguage(optionalLanguage.get());

        categoryRepository.save(categoryToEdt);
        return new ApiResponse("Category - edited OK!", true);

    }

    public ApiResponse delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Category - not deleted!", false);
        }

    }

}
