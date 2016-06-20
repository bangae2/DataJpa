package com.springapp.videos.dao;

import com.springapp.videos.entity.VideoFavouritesEntity;
import com.springapp.videos.entity.VideosEntity;
import com.springapp.videos.repository.VideoFavouritesRepository;
import com.springapp.videos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bangae11 on 2016-06-20.
 */
@Repository
public class VideosDaoImpl implements VideosDao {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private VideoFavouritesRepository videoFavouritesRepository;

    @Override
    public List<VideosEntity> findAll() {
        return this.videosRepository.findAll(sortByTitle3());
    }

    private Sort sortByTitle3() {
        return new Sort(Sort.Direction.ASC, "title3");
    }

    @Override
    public VideosEntity findOne(Integer seq) {
        return this.videosRepository.findOne(seq);
    }

    @Override
    public VideoFavouritesEntity findFavouritesIdAndSeq(String id, String video_seq) {
        return this.videoFavouritesRepository.findFavouritesIdAndSeq(id, video_seq);
    }

    @Override
    public List<VideoFavouritesEntity> findFavouriteId(String id) {
        return this.videoFavouritesRepository.findFavouriteId(id);
    }

    @Override
    public VideoFavouritesEntity findFavouriteVideoSeq(Integer video_seq) {
        return this.videoFavouritesRepository.findFavouriteVideoSeq(video_seq);
    }

    @Override
    public void favouriteSave(VideoFavouritesEntity videoFavouritesEntity) {
        this.videoFavouritesRepository.save(videoFavouritesEntity);
    }

    @Override
    public void favouriteDelete(Integer video_favourites_seq) {
        this.videoFavouritesRepository.delete(video_favourites_seq);
    }
}
