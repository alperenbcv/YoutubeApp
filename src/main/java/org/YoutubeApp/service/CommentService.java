package org.YoutubeApp.service;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.repository.CommentRepository;
import org.YoutubeApp.repository.VideoRepository;
import org.YoutubeApp.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class CommentService implements ICRUDService<Comment> {
	private final CommentRepository commentRepository;
	
	public CommentService() {
		this.commentRepository = new CommentRepository();
	}
	
	@Override
	public Optional<Comment> save(Comment comment) {
		try {
			commentRepository.save(comment);
			System.out.println("Comment saved successfully!");
		}
		catch (Exception e) {
			System.err.println("Service save Hata!");
		}
		return Optional.ofNullable(comment);
	}
	
	@Override
	public Optional<Comment> update(Comment comment) {
		if (findById(comment.getId()).isPresent()) {
			try {
				commentRepository.update(comment);
				System.out.println("comment updated successfully!");
			}
			catch (Exception e) {
				System.err.println("Service update Hata!");
				return Optional.empty();
			}
		}
		else {
			System.out.println("Like not found! Update failed!");
			return Optional.empty();
		}
		return Optional.of(comment);
	}
	
	@Override
	public boolean delete(Long id) {
		if (findById(id).isPresent()) {
			try {
				commentRepository.delete(id);
				System.out.println("comment deleted successfully!");
				return true;
			}
			catch (Exception e) {
				System.err.println("Service Delete Error!");
			}
		}
		else {
			System.out.println("Comment not found! Deletion failed!");
		}
		return false;
	}
	
	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	@Override
	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}
	
	public List<Comment> findCommentsByVideoId(Long videoId) {
        return commentRepository.findCommentsByVideoId(videoId);
    }
	
	public List<Comment> getCommentsByUserId(Long userId) {
		return commentRepository.getCommentsByUserId(userId);
	}
}