package models.pet;

import java.util.Objects;

public final class PetNotFound {
    private int code;
    private String type;
    private String message;

    public void setCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PetNotFound)) {
            return false;
        }
        PetNotFound that = (PetNotFound) o;
        return code == that.code && Objects.equals(type, that.type) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type, message);
    }
}
