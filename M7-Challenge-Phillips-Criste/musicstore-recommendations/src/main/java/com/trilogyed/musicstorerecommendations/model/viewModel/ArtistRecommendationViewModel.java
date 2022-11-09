package com.trilogyed.musicstorerecommendations.model.viewModel;

import java.util.Objects;

// as used in Challenge 5
public class ArtistRecommendationViewModel {
    private long id;
    private int artistId;
    private int userId;
    private Boolean liked;

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
        if (!(o instanceof ArtistRecommendationViewModel)) return false;
        ArtistRecommendationViewModel that = (ArtistRecommendationViewModel) o;
        return getId() == that.getId() && getArtistId() == that.getArtistId() && getUserId() == that.getUserId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArtistId(), getUserId(), getLiked());
    }
}
