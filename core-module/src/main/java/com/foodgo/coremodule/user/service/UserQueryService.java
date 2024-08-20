package com.foodgo.coremodule.user.service;

import com.foodgo.commonmodule.user.exception.UserErrorCode;
import com.foodgo.commonmodule.user.exception.UserExceptionHandler;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserQueryService {

    private final UserRepository userRepository;

    public Boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean checkNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserExceptionHandler(UserErrorCode.USER_NOT_FOUND));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
