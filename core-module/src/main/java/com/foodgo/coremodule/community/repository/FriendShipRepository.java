package com.foodgo.coremodule.community.repository;

import com.foodgo.coremodule.community.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendShipRepository extends JpaRepository<Friendship, Long> {

    Boolean existsFriendshipByUserIdAndFriendId(Long userId, Long friendId);

    Friendship findFriendshipByUserIdAndFriendId(Long userId, Long friendId);
}
