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

import hromov.forum.model.Quote;
import hromov.forum.model.ResourceNotFoundException;
import hromov.forum.repo.CommentRepository;
import hromov.forum.repo.LoginRepository;
import hromov.forum.repo.QuoteRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class QuoteController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    QuoteRepository quoteRepository;

    @PostMapping("/{loginId}/comments/{commentId}/quote")
    public Quote createQuote(@PathVariable(value = "loginId") Long loginId,
	    @PathVariable(value = "commentId") Long commentId, @Valid @RequestBody Quote quote) {
	loginRepository.findById(loginId).map(login -> {
	    quote.setLogin(login);
	    return quote;
	}).orElseThrow(() -> new ResourceNotFoundException("LoginId " + loginId + " not found"));

	return commentRepository.findById(commentId).map(comment -> {
	    quote.setComment(comment);
	    return quoteRepository.save(quote);
	}).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

    @GetMapping("/comments/{commentId}/quotes")
    public Page<Quote> getAllQuotes(@PathVariable("commentId") Long commentId, Pageable page) {
	return quoteRepository.getAllByCommentId(commentId, page);
    }

}