package com.freetests4u.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freetests4u.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User,String>{

	User getUserByEmail(String email);
}
