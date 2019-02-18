package org.ucll.web4.chat_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser,UUID> {

}
