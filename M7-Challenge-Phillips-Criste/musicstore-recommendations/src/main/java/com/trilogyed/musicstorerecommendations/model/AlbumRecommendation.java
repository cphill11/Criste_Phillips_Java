package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "album_recommendation")
public class AlbumRecommendation {
    public AlbumRecommendation(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_recommendation_id")
    private long id;

    @NotNull
    @Column(name = "album_id")
    private int albumId;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @NotNull
    private Boolean liked;

    public AlbumRecommendation(long id, int albumId, int userId, Boolean liked) {
        this.id = id;
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumRecommendation)) return false;
        AlbumRecommendation that = (AlbumRecommendation) o;
        return getId() == that.getId() && getAlbumId() == that.getAlbumId() && getUserId() == that.getUserId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAlbumId(), getUserId(), getLiked());
    }

    @Override
    public String toString() {
        return "AlbumRecommendation{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
