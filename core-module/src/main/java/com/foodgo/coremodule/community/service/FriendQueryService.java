package com.foodgo.coremodule.community.service;

import com.foodgo.coremodule.community.domain.Friendship;
import com.foodgo.coremodule.community.repository.FriendShipRepository;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendQueryService {

    private final FriendShipRepository friendShipRepository;
    private final UserRepository userRepository;

    public List<User> findFriendWithNickname(String nickname) {
        return userRepository.findAllByNicknameContaining(nickname);
    }

    public Boolean checkFriendShipYn(Long userId, Long friendId) {
        return friendShipRepository.existsFriendshipByUserIdAndFriendId(userId, friendId);
    }

    public Friendship findByUserIdAndFriendId(Long userId, Long friendId) {
        return friendShipRepository.findFriendshipByUserIdAndFriendId(userId, friendId);
    }

    public List<User> findFriendRequestList(Long userId) {
        return friendShipRepository.findFriendshipsByUserId(userId)
                .stream().map(Friendship::getFriend).toList();
    }

    public void save(Friendship friendship) {
        friendShipRepository.save(friendship);
    }

    public void delete(Friendship friendship) {
        friendShipRepository.delete(friendship);
    }
}
