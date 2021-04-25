package models.pet;

import java.util.Objects;

public final class TagsItem {
    private String name;
    private int id;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagsItem)) {
            return false;
        }
        TagsItem tagsItem = (TagsItem) o;
        return id == tagsItem.id && Objects.equals(name, tagsItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
