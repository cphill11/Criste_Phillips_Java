package com.trilogyed.musicstorerecommendations.model.viewModel;

import java.util.Objects;

// as used in Challenge 5
public class LabelRecommendationViewModel {
    private long id;
    private int labelId;
    private int userId;
    private Boolean liked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
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
        if (!(o instanceof LabelRecommendationViewModel)) return false;
        LabelRecommendationViewModel that = (LabelRecommendationViewModel) o;
        return getId() == that.getId() && getLabelId() == that.getLabelId() && getUserId() == that.getUserId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLabelId(), getUserId(), getLiked());
    }
}
