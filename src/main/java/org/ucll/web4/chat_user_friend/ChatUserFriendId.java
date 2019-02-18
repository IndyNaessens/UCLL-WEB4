package org.ucll.web4.chat_user_friend;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Embeddable
public class ChatUserFriendId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Type(type="pg-uuid")
    @Column(name = "user_id")
    private UUID userId;

    @Type(type="pg-uuid")
    @Column(name = "friend_id")
    private UUID userFriendId;

    public ChatUserFriendId(){

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(UUID userFriendId) {
        this.userFriendId = userFriendId;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof ChatUserFriendId)) return false;

        ChatUserFriendId temp = (ChatUserFriendId)object;
        return temp.getUserId().equals(getUserId()) &&
                temp.getUserFriendId().equals(getUserFriendId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUserId(),getUserFriendId());
    }
}
