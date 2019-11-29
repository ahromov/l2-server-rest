package hromov.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hromov.forum.model.Comment;
import hromov.forum.model.ResourceNotFoundException;
import hromov.forum.repo.CommentRepository;
import hromov.forum.repo.ForumRepository;
import hromov.forum.repo.LoginRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CommentController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/{loginId}/forums/{forumId}/comment")
    public Comment createComment(@PathVariable(value = "loginId") Long loginId,
	    @PathVariable(value = "forumId") Long forumId, @Valid @RequestBody Comment comment) {
	loginRepository.findById(loginId).map(login -> {
	    comment.setLogin(login);
	    return comment;
	}).orElseThrow(() -> new ResourceNotFoundException("LoginId " + loginId + " not found"));

	return forumRepository.findById(forumId).map(forum -> {
	    comment.setForum(forum);
	    return commentRepository.save(comment);
	}).orElseThrow(() -> new ResourceNotFoundException("ForumId " + forumId + " not found"));
    }

    @GetMapping("/forums/{forumId}/comments")
    public Page<Comment> getAllComments(@PathVariable("forumId") Long forumId, Pageable page) {
	return commentRepository.getAllByForumId(forumId, page);
    }

}