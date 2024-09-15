package org.YoutubeApp.service;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.ELikeStatus;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.repository.CommentRepository;
import org.YoutubeApp.repository.LikeRepository;
import org.YoutubeApp.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class LikeService implements ICRUDService<Like> {
	
	private final LikeRepository likeRepository;
	
	public LikeService() {
		this.likeRepository = new LikeRepository();
	}
	
	@Override
	public Optional<Like> save(Like like) {
		try {
			if(likeUniqueCheck(like)) {
				likeRepository.save(like);
				System.out.println("Like saved successfully!");
			}
			else {
                System.out.println("Like already exists! Save operation failed!");
                return Optional.empty();
            }
		}
		catch (Exception e) {
			System.err.println("Service save Hata!");
		}
		return Optional.ofNullable(like);
	}
	
	@Override
	public Optional<Like> update(Like like) {
		if (findById(like.getId()).isPresent()) {
			try {
				likeRepository.update(like);
				System.out.println("like updated successfully!");
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
		return Optional.of(like);
		
	}
	
	@Override
	public boolean delete(Long id) {
		if (findById(id).isPresent()) {
			try {
				likeRepository.delete(id);
				System.out.println("Like deleted successfully!");
				return true;
			}
			catch (Exception e) {
				System.err.println("Service delete Error!");
			}
		}
		else {
			System.out.println("Like not found! Deletion failed!");
		}
		return false;
	}
	
	@Override
	public List<Like> findAll() {
		return likeRepository.findAll();
	}
	
	@Override
	public Optional<Like> findById(Long id) {
		return likeRepository.findById(id);
	}
	
	public boolean likeUniqueCheck(Like like){
		String query = "SELECT * FROM tbllike WHERE userid = '" + like.getUserId() + "' AND videoId = '" + like.getVideoId() + "'";
		List<Like> byQuery = likeRepository.findByQuery(query);
		if(byQuery.isEmpty()){
			return true;
		} else {
			Like existingLike = byQuery.get(0);
			if(existingLike.getLikeStatus() != like.getLikeStatus()) {
				existingLike.setLikeStatus(like.getLikeStatus());
				likeRepository.update(existingLike);
			}
			return false;
		}
	}
	
	public Integer getLikeCountByVideoId(Long videoId) {
		String query =
				"SELECT COUNT(*) FROM tbllike WHERE videoId = '" + videoId + "' AND likestatus = 'LIKE'";
		
		return likeRepository.findCountByQuery(query);
	}
	
	public Integer getDislikeCountByVideoId(Long videoId) {
		String query =
				"SELECT COUNT(*) FROM tbllike WHERE videoId = '" + videoId + "' AND likestatus = 'DISLIKE'";
		return likeRepository.findCountByQuery(query);
	}
	
	
}