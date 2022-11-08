package com.trilogyed.musicstorerecommendations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "label_recommendation")
public class LabelRecommendation {
    public LabelRecommendation(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_recommendation_id")
    private long id;

    @NotNull
    @Column(name = "label_id")
    private int labelId;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @NotNull
    private Boolean liked;

    public LabelRecommendation(long id, int labelId, int userId, Boolean liked) {
        this.id = id;
        this.labelId = labelId;
        this.userId = userId;
        this.liked = liked;
    }

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
        if (!(o instanceof LabelRecommendation)) return false;
        LabelRecommendation that = (LabelRecommendation) o;
        return getId() == that.getId() && getLabelId() == that.getLabelId() && getUserId() == that.getUserId() && Objects.equals(getLiked(), that.getLiked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLabelId(), getUserId(), getLiked());
    }

    @Override
    public String toString() {
        return "LabelRecommendation{" +
                "id=" + id +
                ", labelId=" + labelId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
