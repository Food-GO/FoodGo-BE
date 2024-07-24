package com.foodgo.coremodule.user.repository;

import java.util.Optional;

import com.foodgo.coremodule.user.domain.User;

public interface UserRepository {

	Optional<User> findByUsername(String username);

	User save(User user);
}
