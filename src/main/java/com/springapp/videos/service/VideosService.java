package com.springapp.videos.service;

import com.springapp.videos.entity.VideoFavouritesEntity;
import com.springapp.videos.entity.VideosEntity;

import java.util.List;

/**
 * Created by bangae11 on 2016-06-19.
 */
public interface VideosService {
    public List<VideosEntity> findAll();
    public VideosEntity findOne(Integer seq);
    public VideoFavouritesEntity findFavouritesIdAndSeq(String id, String video_seq);
    public String favouriteSave(VideoFavouritesEntity videoFavouritesEntity);
}
