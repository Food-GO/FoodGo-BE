package com.foodgo.apimodule.community.application;

import com.foodgo.coremodule.community.domain.Friendship;
import com.foodgo.coremodule.community.service.FriendQueryService;
import com.foodgo.coremodule.user.domain.User;
import com.foodgo.coremodule.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendSaveUseCase {

    private final FriendQueryService friendQueryService;
    private final UserQueryService userQueryService;

    public void acceptFriendRequest(Long userId, Long friendId) {

        Friendship friendship = friendQueryService.findByUserIdAndFriendId(userId, friendId);
        friendship.markAsMutual();
        friendQueryService.save(friendship);
    }

    public Boolean requestFriend(User user, Long friendId) {

        if (friendQueryService.checkFriendShipYn(user.getId(), friendId)) {
            return false;
        } else {

            User friend = userQueryService.findByUserId(friendId);
            final Friendship friendship = Friendship.builder().user(user).friend(friend).isMutual(false).build();
            friendQueryService.save(friendship);

            return true;
        }
    }
}
