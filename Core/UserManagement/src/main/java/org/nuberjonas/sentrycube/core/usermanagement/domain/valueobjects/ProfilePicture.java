package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

public final class ProfilePicture {

    private final byte[] data;

    public ProfilePicture(byte[] data) {
        this.data = data;
    }

    public ProfilePicture change(byte[] newData){
        return new ProfilePicture(newData);
    }

    public byte[] get(){
        return data;
    }
}
