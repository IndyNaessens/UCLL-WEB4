package org.ucll.web4.chat_user_friend;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "chat_user_friend",schema = "chat_app")
public class ChatUserFriend {

    @EmbeddedId
    private ChatUserFriendId compositeKey;

    public ChatUserFriend(){

    }

    public ChatUserFriendId getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(ChatUserFriendId compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof ChatUserFriend)) return false;

        ChatUserFriend temp = (ChatUserFriend)object;
        return temp.getCompositeKey().equals(getCompositeKey());
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(getCompositeKey());
    }
}
