package com.coderspark.springsecurityclient.repository;

import com.coderspark.springsecurityclient.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
