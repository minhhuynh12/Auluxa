package net.dirox.auluxa.video;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by an on 6/29/2017.
 * by Hoang Giang
 */

public class User implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("isAdmin")
    public boolean isAdmin;
    @SerializedName("email")
    public String email;
    @SerializedName("tvPasswordContent")
    public String tvPasswordContent;
    @SerializedName("llLivingRoom")
    public boolean llLivingRoom;
    @SerializedName("llDiningRoom")
    public boolean llDiningRoom;
    @SerializedName("llGuestBedroom")
    public boolean llGuestBedroom;

    public User(String name, boolean isAdmin, String email, String tvPasswordContent, boolean llLivingRoom, boolean llDiningRoom, boolean llGuestBedroom) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.email = email;
        this.tvPasswordContent = tvPasswordContent;
        this.llLivingRoom = llLivingRoom;
        this.llDiningRoom = llDiningRoom;
        this.llGuestBedroom = llGuestBedroom;
    }
}
