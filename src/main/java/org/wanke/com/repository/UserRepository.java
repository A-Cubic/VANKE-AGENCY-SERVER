package org.wanke.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wanke.com.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);

}
