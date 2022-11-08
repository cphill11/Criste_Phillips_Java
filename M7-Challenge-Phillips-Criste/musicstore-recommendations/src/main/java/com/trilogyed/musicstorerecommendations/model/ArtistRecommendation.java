package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "album_recommendation")

public class ArtistRecommendation {
    public ArtistRecommendation() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_recommendation_id")
    private long id;

    @NotNull
    @Column(name = "artist_id")
    private int artistId;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @NotNull
    private Boolean liked;

    public ArtistRecommendation(long id, int artistId, int userId, Boolean liked) {
        this.id = id;
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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
        if (!(o instanceof ArtistRecommendation)) return false;
        ArtistRecommendation that = (ArtistRecommendation) o;
        return getId() == that.getId() && getArtistId() == that.getArtistId() && getUserId() == that.getUserId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArtistId(), getUserId(), getLiked());
    }

    @Override
    public String toString() {
        return "ArtistRecommendation{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
