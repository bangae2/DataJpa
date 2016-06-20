package com.springapp.videos.repository;

import com.springapp.videos.entity.VideoFavouritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bangae11 on 2016-06-20.
 */
public interface VideoFavouritesRepository extends JpaRepository<VideoFavouritesEntity, Integer> {
    @Query(value = "select * from video_favourites where id = :id", nativeQuery = true)
    List<VideoFavouritesEntity> findFavouriteId(@Param("id")String id);

    @Query(value = "select * from video_favourites where video_seq = :video_seq", nativeQuery = true)
    VideoFavouritesEntity findFavouriteVideoSeq(@Param("video_seq") Integer video_seq);

    @Query(value = "select * from video_favourites where video_seq = :video_seq and id = :id", nativeQuery = true)
    VideoFavouritesEntity findFavouritesIdAndSeq(@Param("id")String id, @Param("video_seq")String video_seq);
}
