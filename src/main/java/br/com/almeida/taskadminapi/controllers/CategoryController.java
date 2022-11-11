package br.com.almeida.taskadminapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.almeida.taskadminapi.models.CategoryModel;
import br.com.almeida.taskadminapi.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryModel>> getAllCategories(@RequestParam(required = false) String name) {
        try {
            List<CategoryModel> categories = new ArrayList<CategoryModel>();
            if (name == null) {
                categoryRepository.findAll().forEach(categories::add);
            } else {
                categoryRepository.findByName(name).forEach(categories::add);
            }
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable("id") UUID id) {
        Optional<CategoryModel> categoryData = categoryRepository.findById(id);
        if (categoryData.isPresent()) {
            return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/category")
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel category) {
        try {
            CategoryModel _category = categoryRepository
                    .save(new CategoryModel(
                            category.getName(), category.getColor(), category.getIconCode()));
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable("id") UUID id,
            @RequestBody CategoryModel category) {
        Optional<CategoryModel> categoryData = categoryRepository.findById(id);

        if (categoryData.isPresent()) {
            CategoryModel _category = categoryData.get();
            _category.setName(category.getName());
            _category.setColor(category.getColor());
            _category.setIconCode(category.getIconCode());
            return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") UUID id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/category")
    public ResponseEntity<CategoryModel> deleteAllCategories() {
        try {
            categoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
