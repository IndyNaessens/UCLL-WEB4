package org.ucll.web4.entity;

import java.util.Objects;
import java.util.UUID;

public class ChatPairEntity {

    private final UUID userOne;
    private final UUID userTwo;

    public ChatPairEntity(UUID userOne,UUID userTwo){
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public UUID getUserOne() {
        return userOne;
    }

    public UUID getUserTwo() {
        return userTwo;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof ChatPairEntity)) return false;

        ChatPairEntity temp = (ChatPairEntity)o;
        return (temp.getUserOne().equals(getUserOne()) && temp.getUserTwo().equals(getUserTwo()))
                                                                                                    ||
                (temp.getUserOne().equals(getUserTwo()) && temp.getUserTwo().equals(getUserOne()));
    }

    @Override
    public int hashCode(){
        return Objects.hash(userOne,userTwo) + Objects.hash(userTwo,userOne);
    }
}
