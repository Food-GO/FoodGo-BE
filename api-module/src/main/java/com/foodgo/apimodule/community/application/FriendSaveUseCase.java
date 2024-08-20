package com.foodgo.apimodule.community.application;

import com.foodgo.coremodule.community.domain.Friendship;
import com.foodgo.coremodule.community.service.FriendQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendSaveUseCase {

    private final FriendQueryService friendQueryService;

    public void acceptFriendRequest(Long userId, Long friendId) {

        Friendship friendship = friendQueryService.findByUserIdAndFriendId(userId, friendId);
        friendship.markAsMutual();
        friendQueryService.save(friendship);
    }
}
