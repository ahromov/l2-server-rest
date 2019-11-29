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

import hromov.forum.model.ResourceNotFoundException;
import hromov.forum.model.Section;
import hromov.forum.repo.CategoryRepository;
import hromov.forum.repo.SectionRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories/{categoryId}/sections")
    public Page<Section> getAllSectionsByCategoryId(@PathVariable(value = "categoryId") Long categoryId,
	    Pageable pageable) {

	return sectionRepository.findByCategoryId(categoryId, pageable);
    }

    @PostMapping("/categories/{categoryId}/section")
    public Section createSection(@PathVariable(value = "categoryId") Long categoryId,
	    @Valid @RequestBody Section section) {

	return categoryRepository.findById(categoryId).map(category -> {
	    section.setCategory(category);
	    return sectionRepository.save(section);
	}).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId + " not found"));
    }

    @DeleteMapping("/sections/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long sectionId) {

	return categoryRepository.findById(sectionId).map(section -> {
	    categoryRepository.delete(section);
	    return ResponseEntity.ok().build();
	}).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + sectionId + " not found"));
    }

}
