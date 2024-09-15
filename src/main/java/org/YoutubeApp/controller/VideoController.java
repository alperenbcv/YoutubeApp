package org.YoutubeApp.controller;

import org.YoutubeApp.dto.VideoDTO;
import org.YoutubeApp.entity.ECategory;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.service.UserService;
import org.YoutubeApp.service.VideoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoController {
	private final VideoService videoService;
	
	public VideoController() {
		this.videoService = new VideoService();
	}
	
	public Optional<Video> save(Video video) {
		try{
			return videoService.save(video);
		} catch (Exception e) {
			System.out.println("Controller Save Hata!");
		}
		return Optional.empty();
	}
	
	public Optional<Video> update(Video video) {
		try{
			return videoService.update(video);
		} catch (Exception e) {
			System.err.println("Controller Update Hata!");
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		try{
			return videoService.delete(id);
		} catch (Exception e) {
			System.err.println("Controller Delete Hata!");
		}
		return false;
	}
	
	public List<Video> findAll() {
		try{
			return videoService.findAll();
		} catch (Exception e) {
			System.err.println("Controller FindAll Hata!");
		}
		return null;
	}
	
	public Optional<Video> findById(Long id) {
		Optional<Video> video = videoService.findById(id);
		video.ifPresentOrElse(
				v -> System.out.println("Controller Video bulundu: " + v.getTitle()),
				() -> System.out.println("Controller Böyle bir video bulunamadı.")
		);
		return video;
	}
	
	public List<Video> findByTitle(String title) {
		try{
			return videoService.findByTitle(title);
		} catch (Exception e) {
			System.err.println("Controller findByTitle Hata!");
		}
		return new ArrayList<>();
	}
	
	public List<Video> findByCategory(ECategory category) {
		try{
			return videoService.findByCategory(category);
		} catch (Exception e) {
			System.err.println("Controller findByTitle Hata!");
		}
		return new ArrayList<>();
	}
	
//	public List<Video> findByDate(String startDate, String endDate) {
//	}
}