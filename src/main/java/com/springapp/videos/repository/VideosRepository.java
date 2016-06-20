package com.springapp.videos.repository;

import com.springapp.videos.entity.VideosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bangae11 on 2016-06-19.
 */
public interface VideosRepository extends JpaRepository<VideosEntity, Integer> {
}
