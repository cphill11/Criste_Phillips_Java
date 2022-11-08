package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "track_recommendation")
public class TrackRecommendation {
    public TrackRecommendation() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_recommendation_id")
    private long id;

    @NotNull
    @Column(name = "track_id")
    private int trackId;

    @NotNull
    @Column(name = "album_id")
    private int albumId;

    @NotNull
    private Boolean liked;

    public TrackRecommendation(long id, int trackId, int albumId, Boolean liked) {
        this.id = id;
        this.trackId = trackId;
        this.albumId = albumId;
        this.liked = liked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
        if (!(o instanceof TrackRecommendation)) return false;
        TrackRecommendation that = (TrackRecommendation) o;
        return getId() == that.getId() && getTrackId() == that.getTrackId() && getAlbumId() == that.getAlbumId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrackId(), getAlbumId(), getLiked());
    }

    @Override
    public String toString() {
        return "TrackRecommendation{" +
                "id=" + id +
                ", trackId=" + trackId +
                ", albumId=" + albumId +
                ", liked=" + liked +
                '}';
    }
}
