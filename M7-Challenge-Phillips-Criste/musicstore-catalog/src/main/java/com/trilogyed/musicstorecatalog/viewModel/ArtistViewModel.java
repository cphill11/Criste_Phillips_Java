package com.trilogyed.musicstorecatalog.viewModel;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

// as used in Challenge 5
public class ArtistViewModel {
    private long id;
    @NotEmpty(message = "Artist name is required.")
    private String name;

    private String instagram;

    private String twitter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtistViewModel)) return false;
        ArtistViewModel that = (ArtistViewModel) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getInstagram(), that.getInstagram()) && Objects.equals(getTwitter(), that.getTwitter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getInstagram(), getTwitter());
    }
}
