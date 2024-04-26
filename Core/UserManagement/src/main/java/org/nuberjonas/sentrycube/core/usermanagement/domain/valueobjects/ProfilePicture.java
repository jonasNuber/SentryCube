package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import java.util.Arrays;

public record ProfilePicture(byte[] data) {
    public ProfilePicture change(byte[] newData){
        return new ProfilePicture(newData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfilePicture that = (ProfilePicture) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "ProfilePicture{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
