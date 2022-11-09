package com.trilogyed.musicstorerecommendations.model.viewModel;

import java.util.Objects;

// as used in Challenge 5
public class TrackRecommendationViewModel   {
    private long id;

    private int trackId;
    private int albumId;
    private Boolean liked;

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
        if (!(o instanceof TrackRecommendationViewModel)) return false;
        TrackRecommendationViewModel that = (TrackRecommendationViewModel) o;
        return getId() == that.getId() && getTrackId() == that.getTrackId() && getAlbumId() == that.getAlbumId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrackId(), getAlbumId(), getLiked());
    }
}
