package org.ucll.web4;

import org.springframework.context.annotation.Bean;
import org.ucll.web4.user.repository.UserFriendListRepository;
import org.ucll.web4.user.repository.UserFriendListRepositoryStub;
import org.ucll.web4.user.repository.UserRepository;
import org.ucll.web4.user.repository.UserRepositoryStub;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryStub();
    }

    @Bean
    public UserFriendListRepository userFriendListRepository(){
        return new UserFriendListRepositoryStub();
    }
}
