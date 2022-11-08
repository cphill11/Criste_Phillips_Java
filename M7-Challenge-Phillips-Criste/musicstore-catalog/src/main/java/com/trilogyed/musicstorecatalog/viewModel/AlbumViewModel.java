package com.trilogyed.musicstorecatalog.viewModel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

// as used in Challenge 5
public class AlbumViewModel {
    private long id;
    @NotEmpty(message = "Album title is required.")
    private String title;

    @NotNull
    private long artistId;

    @NotNull
    private Date releaseDate;

    @NotNull
    private long labelId;

    @NotNull
    @DecimalMin(value = "1.00", inclusive = true, message = "Price for this item is lower than  $1.0")
    @DecimalMax(value = "999.99", inclusive = true, message = "Price for this item exceeds, $999.99")
    private BigDecimal listPrice;

    public long getId() {        return id;
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

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumViewModel)) return false;
        AlbumViewModel that = (AlbumViewModel) o;
        return getId() == that.getId() && getArtistId() == that.getArtistId() && getLabelId() == that.getLabelId() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getReleaseDate(), that.getReleaseDate()) && Objects.equals(getListPrice(), that.getListPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArtistId(), getReleaseDate(), getLabelId(), getListPrice());
    }
}
