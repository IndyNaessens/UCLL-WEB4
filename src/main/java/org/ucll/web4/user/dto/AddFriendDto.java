package org.ucll.web4.user.dto;

import java.util.UUID;

public class AddFriendDto {

    private UUID owner;
    private UUID newFriend;

    public AddFriendDto(){

    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public UUID getNewFriend() {
        return newFriend;
    }

    public void seNewFriend(UUID possibleNewFriend) {
        this.newFriend = possibleNewFriend;
    }
}
