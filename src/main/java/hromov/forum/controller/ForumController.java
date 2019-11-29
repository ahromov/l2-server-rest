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

import hromov.forum.model.Forum;
import hromov.forum.model.ResourceNotFoundException;
import hromov.forum.repo.ForumRepository;
import hromov.forum.repo.LoginRepository;
import hromov.forum.repo.SectionRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ForumController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ForumRepository forumRepository;

    @PostMapping("/{loginId}/sections/{sectionId}/forum")
    public Forum createForum(@PathVariable(value = "loginId") Long loginId,
	    @PathVariable(value = "sectionId") Long sectionId, @Valid @RequestBody Forum forum) {
	loginRepository.findById(loginId).map(login -> {
	    forum.setLogin(login);
	    return forum;
	}).orElseThrow(() -> new ResourceNotFoundException("LoginId " + loginId + " not found"));

	return sectionRepository.findById(sectionId).map(section -> {
	    forum.setSection(section);
	    return forumRepository.save(forum);
	}).orElseThrow(() -> new ResourceNotFoundException("SectionId " + sectionId + " not found"));
    }

    @GetMapping("/sections/{sectionId}/forums")
    public Page<Forum> getAllForums(@PathVariable("sectionId") Long sectionId, Pageable page) {
	return forumRepository.getAllBySectionId(sectionId, page);
    }

    @DeleteMapping("/forums/{forumId}")
    public ResponseEntity<?> deleteForum(@PathVariable Long forumId) {

	return forumRepository.findById(forumId).map(forum -> {
	    forumRepository.delete(forum);
	    return ResponseEntity.ok().build();
	}).orElseThrow(() -> new ResourceNotFoundException("ForumId " + forumId + " not found"));
    }

}