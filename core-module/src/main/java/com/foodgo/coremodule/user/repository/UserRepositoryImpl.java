package com.foodgo.coremodule.user.repository;


import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.foodgo.coremodule.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<User> findByUsername(String username) {
		return userJpaRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
}
