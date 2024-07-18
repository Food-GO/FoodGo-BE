package com.foodgo.apimodule.user.application;

import com.foodgo.apimodule.security.util.JwtUtil;
import com.foodgo.apimodule.user.dto.LoginRequest;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.dto.LoginResponse;
import com.foodgo.coremodule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LoginResponse ssoLogin(final LoginRequest loginRequest) {

        final User user = userRepository.findByName(loginRequest.name())
                .orElseGet(() -> createNewUser(loginRequest));

        return new LoginResponse(jwtUtil.createJwtAccessToken(loginRequest.name(), loginRequest.email()),
                jwtUtil.createJwtRefreshToken(loginRequest.name(), loginRequest.email()));
    }

    private User createNewUser(final LoginRequest loginRequest) {

        final User newUser = User.createNewUser(loginRequest.name(), loginRequest.picture(), loginRequest.email());
        return userRepository.save(newUser);
    }
}
