package com.trilogyed.musicstorecatalog.viewModel;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

// as used in Challenge 5
public class LabelViewModel {

    private long id;
    @NotEmpty(message = "Label name is required.")
    private String name;

    private String website;

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LabelViewModel)) return false;
        LabelViewModel that = (LabelViewModel) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getWebsite(), that.getWebsite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWebsite());
    }
}
