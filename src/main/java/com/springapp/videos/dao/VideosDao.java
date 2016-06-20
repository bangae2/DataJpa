package com.springapp.videos.dao;

import com.springapp.videos.entity.VideoFavouritesEntity;
import com.springapp.videos.entity.VideosEntity;

import java.util.List;

/**
 * Created by bangae11 on 2016-06-20.
 */
public interface VideosDao {
    public List<VideosEntity> findAll();
    public VideosEntity findOne(Integer seq);
    public VideoFavouritesEntity findFavouritesIdAndSeq(String id, String video_seq);
    public List<VideoFavouritesEntity> findFavouriteId(String id);
    public VideoFavouritesEntity findFavouriteVideoSeq(Integer video_seq);
    public void favouriteSave(VideoFavouritesEntity videoFavouritesEntity);
    public void favouriteDelete(Integer video_favourites_seq);
}
