package hromov.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hromov.forum.model.Category;
import hromov.forum.model.ResourceNotFoundException;
import hromov.forum.repo.CategoryRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/category")
    public Category createCat(@Valid @RequestBody Category category) {

	return categoryRepository.save(category);
    }

    @GetMapping("/categories")
    public Page<Category> getCategories(Pageable pageable) {

	return categoryRepository.findAll(pageable);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {

	return categoryRepository.findById(categoryId).map(category -> {
	    categoryRepository.delete(category);
	    return ResponseEntity.ok().build();
	}).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId + " not found"));
    }

}