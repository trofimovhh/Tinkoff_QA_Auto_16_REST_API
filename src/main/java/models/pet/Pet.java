package models.pet;

import java.util.List;
import java.util.Objects;

public final class Pet {
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(photoUrls, pet
                .photoUrls) && Objects.equals(name, pet
                .name) && Objects.equals(category, pet
                .category) && Objects.equals(tags, pet
                .tags) && Objects.equals(status, pet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoUrls, name, id, category, tags, status);
    }

    private List<String> photoUrls;
    private String name;
    private int id;
    private Category category;
    private List<TagsItem> tags;
    private String status;

    public void setPhotoUrls(final List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setTags(final List<TagsItem> tags) {
        this.tags = tags;
    }

    public List<TagsItem> getTags() {
        return tags;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
