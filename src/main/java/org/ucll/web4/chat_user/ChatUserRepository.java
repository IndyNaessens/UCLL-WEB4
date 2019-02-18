package org.ucll.web4.chat_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser,UUID> {

    //update the status of the chat user
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ChatUser user SET user.status = :status WHERE user.id = :id")
    void updateStatus(@Param("id") UUID chatUserId, @Param("status") String newStatus);

}
