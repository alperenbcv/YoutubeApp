package org.YoutubeApp.controller;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.service.CommentService;
import org.YoutubeApp.service.UserService;

import java.util.List;
import java.util.Optional;

public class CommentController {
	private final CommentService commentService;
	
	public CommentController() {
		this.commentService = new CommentService();
	}
	
	public Optional<Comment> save(Comment comment) {
		try{
			return commentService.save(comment);
		} catch (Exception e) {
			System.out.println("Controller Save Hata!");
		}
		return Optional.empty();
	}
	
	public Optional<Comment> update(Comment comment) {
		try{
			return commentService.update(comment);
		} catch (Exception e) {
			System.err.println("Controller Update Hata!");
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		try{
			return commentService.delete(id);
		} catch (Exception e) {
			System.err.println("Controller Delete Hata!");
		}
		return false;
	}
	
	public List<Comment> findAll() {
		try{
			return commentService.findAll();
		} catch (Exception e) {
			System.err.println("Controller FindAll Hata!");
		}
		return null;
	}
	
	public Optional<Comment> findById(Long id) {
		Optional<Comment> comment = commentService.findById(id);
		comment.ifPresentOrElse(
				c -> System.out.println("Controller Comment bulundu: " + c.getId()),
				() -> System.out.println("Controller Böyle bir comment bulunamadı.")
		);
		return comment;
	}
	
	public List<Comment> findCommentsByVideoId(Long id) {
		List<Comment> commentsByVideoId = commentService.findCommentsByVideoId(id);
		return commentsByVideoId;
    }
	
	public List<Comment> getCommentsByUserId(Long userId) {
		return commentService.getCommentsByUserId(userId);
	}
}