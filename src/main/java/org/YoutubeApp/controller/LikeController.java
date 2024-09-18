package org.YoutubeApp.controller;

import org.YoutubeApp.entity.Like;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.service.LikeService;
import org.YoutubeApp.service.UserService;

import java.util.List;
import java.util.Optional;

public class LikeController {
	private final LikeService likeService;
	
	public LikeController() {
		this.likeService = new LikeService();
	}
	
	public Optional<Like> save(Like like) {
		try{
			return likeService.save(like);
		} catch (Exception e) {
			System.out.println("Controller Save Hata!");
		}
		return Optional.empty();
	}
	
	public Optional<Like> update(Like like) {
		try{
			return likeService.update(like);
		} catch (Exception e) {
			System.err.println("Controller Update Hata!");
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		try{
			return likeService.delete(id);
		} catch (Exception e) {
			System.err.println("Controller Delete Hata!");
		}
		return false;
	}
	
	public List<Like> findAll() {
		try{
			return likeService.findAll();
		} catch (Exception e) {
			System.err.println("Controller FindAll Hata!");
		}
		return null;
	}
	
	public Optional<Like> findById(Long id) {
		Optional<Like> like = likeService.findById(id);
		like.ifPresentOrElse(
				l -> System.out.println("Controller Like bulundu: " + l.getId()),
				() -> System.out.println("Controller Böyle bir like bulunamadı.")
		);
		return like;
	}
	
	public Integer getLikeCountByVideoId(Long videoId) {
		return likeService.getLikeCountByVideoId(videoId);
	}
	
    public Integer getDislikeCountByVideoId(Long videoId) {
        return likeService.getDislikeCountByVideoId(videoId);
    }
	
	public List<Like> getLikesByUserId(Long userId) {
		return likeService.getLikesByUserId(userId);
	}
}