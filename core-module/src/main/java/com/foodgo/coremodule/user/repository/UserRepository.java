package com.foodgo.coremodule.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodgo.coremodule.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	Optional<User> findByUsername(String username);

	User save(User user);
}
