package models.pet;

import java.util.Objects;

public final class Category {
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
        if (o instanceof Category) {
            Category category = (Category) o;
            return id == category.id && Objects.equals(name, category.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
