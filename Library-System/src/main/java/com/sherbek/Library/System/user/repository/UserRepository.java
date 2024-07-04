package com.sherbek.Library.System.user.repository;

import com.sherbek.Library.System.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}