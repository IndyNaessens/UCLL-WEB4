package org.ucll.web4.user.repository;

import org.springframework.stereotype.Repository;
import org.ucll.web4.GenericRepository;
import org.ucll.web4.user.UserEntity;

import java.util.UUID;

@Repository
public interface UserRepository extends GenericRepository<UserEntity, UUID> {

}
