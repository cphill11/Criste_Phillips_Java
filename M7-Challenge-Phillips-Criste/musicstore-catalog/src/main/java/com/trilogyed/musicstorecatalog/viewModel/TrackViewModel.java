package com.trilogyed.musicstorecatalog.viewModel;


import javax.validation.constraints.NotEmpty;
import java.util.Objects;

// as used in Challenge 5
public class TrackViewModel {
    private long id;

    @NotEmpty(message = "Track title is required.")
    private String title;

    private int albumId;

    private int runTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackViewModel)) return false;
        TrackViewModel that = (TrackViewModel) o;
        return getId() == that.getId() && getAlbumId() == that.getAlbumId() && getRunTime() == that.getRunTime() && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAlbumId(), getRunTime());
    }
}
