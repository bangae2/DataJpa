package com.springapp.videos.entity;

import javax.persistence.*;

/**
 * Created by bangae11 on 2016-06-20.
 */
@Entity
@Table(name = "video_favourites", schema = "bangae1",uniqueConstraints = @UniqueConstraint(columnNames = {"video_seq", "id"}))
public class VideoFavouritesEntity {
    private Integer video_favourites_seq;
    private Integer video_seq;
    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_favourites_seq")
    public Integer getVideo_favourites_seq() {
        return video_favourites_seq;
    }

    public void setVideo_favourites_seq(Integer video_favourites_seq) {
        this.video_favourites_seq = video_favourites_seq;
    }

    @Basic
    @Column(name = "video_seq", unique = true)
    public Integer getVideo_seq() {
        return video_seq;
    }

    public void setVideo_seq(Integer video_seq) {
        this.video_seq = video_seq;
    }
    
    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoFavouritesEntity that = (VideoFavouritesEntity) o;

        if (video_favourites_seq != null ? !video_favourites_seq.equals(that.video_favourites_seq) : that.video_favourites_seq != null)
            return false;
        if (video_seq != null ? !video_seq.equals(that.video_seq) : that.video_seq != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = video_favourites_seq != null ? video_favourites_seq.hashCode() : 0;
        result = 31 * result + (video_seq != null ? video_seq.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoFavouritesEntity{" +
                "video_favourites_seq=" + video_favourites_seq +
                ", video_seq=" + video_seq +
                ", id='" + id + '\'' +
                '}';
    }
}
