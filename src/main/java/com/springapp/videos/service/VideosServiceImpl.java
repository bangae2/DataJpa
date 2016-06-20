package com.springapp.videos.service;

import com.springapp.videos.dao.VideosDao;
import com.springapp.videos.entity.VideoFavouritesEntity;
import com.springapp.videos.entity.VideosEntity;
import com.springapp.videos.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bangae11 on 2016-06-19.
 */
@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    private VideosDao videosDao;

    @Override
    @Transactional(readOnly = true)
    public List<VideosEntity> findAll() {
        return videosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VideosEntity findOne(Integer seq) {
        return videosDao.findOne(seq);
    }

    @Override
    @Transactional(readOnly = true)
    public VideoFavouritesEntity findFavouritesIdAndSeq(String id, String video_seq) {
        return videosDao.findFavouritesIdAndSeq(id, video_seq);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String favouriteSave(VideoFavouritesEntity videoFavouritesEntity) {
        VideoFavouritesEntity entity = this.videosDao.findFavouriteVideoSeq(videoFavouritesEntity.getVideo_seq());
        if(entity  == null) {
            this.videosDao.favouriteSave(videoFavouritesEntity);
            return "1";
        } else {
            this.videosDao.favouriteDelete(entity.getVideo_favourites_seq());
            return "0";
        }

    }
}
