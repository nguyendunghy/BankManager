package com.example.bankmanager.repository;

import com.example.bankmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

	List<User> findUsersByUsername(String username);

	List<User> findUsersByUsernameAndPassword(String username, String password);
}
