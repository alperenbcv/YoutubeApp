package org.YoutubeApp.service;

import org.YoutubeApp.dto.VideoDTO;
import org.YoutubeApp.entity.ECategory;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.repository.UserRepository;
import org.YoutubeApp.repository.VideoRepository;
import org.YoutubeApp.utility.ICRUDService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoService implements ICRUDService<Video> {
	
	private final VideoRepository videoRepository;
	
	public VideoService() {
		this.videoRepository = new VideoRepository();
	}
	
	@Override
	public Optional<Video> save(Video video) {
		try {
			videoRepository.save(video);
			System.out.println("Video saved successfully!");
		}
		catch (Exception e) {
			System.err.println("Service save Hata!");
		}
		return Optional.ofNullable(video);
	}
	
	@Override
	public Optional<Video> update(Video video) {
		if (findById(video.getId()).isPresent()) {
			try {
				videoRepository.update(video);
				System.out.println("video updated successfully!");
			}
			catch (Exception e) {
				System.err.println("Service update Hata!");
				return Optional.empty();
			}
		}
		else {
			System.out.println("Video not found! Update failed!");
			return Optional.empty();
		}
		return Optional.of(video);
	}
	
	@Override
	public boolean delete(Long id) {
		if (findById(id).isPresent()) {
			try {
				videoRepository.delete(id);
				System.out.println("video deleted successfully!");
				return true;
			}
			catch (Exception e) {
				System.err.println("Service delete Error!");
			}
		}
		else {
			System.out.println("Video not found! Deletion failed!");
		}
		return false;
	}
	
	@Override
	public List<Video> findAll() {
		return videoRepository.findAll();
	}
	
	
	@Override
	public Optional<Video> findById(Long id) {
		return videoRepository.findById(id);
	}
	
	public List<Video> findByTitle(String title) {
		return videoRepository.findByTitle(title);
	}
	
	public List<Video> findByCategory(ECategory category) {
		return videoRepository.findByCategory(category);
	}
	
	public List<Video> findByDate(String startDate, String endDate) {
		return videoRepository.findByDate(startDate, endDate);
	}
	
	public List<Video> getVideosByUserId(Long userId) {
		return videoRepository.getVideosByUserId(userId);
	}
	
	public List<Video> findAllByTitle() {
        return videoRepository.findAllByTitle();
    }
	
	public List<Video> findAllByViewCount() {
        return videoRepository.findAllByViewCount();
    }
	
	public List<Video> findAllByLikeCount() {
        return videoRepository.findAllByLikeCount();
    }
	
	public List<Video> findAllByDislikeCount() {
        return videoRepository.findAllByDislikeCount();
    }
}