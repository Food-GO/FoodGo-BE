package com.foodgo.coremodule.community.repository;

import com.foodgo.coremodule.community.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    Challenge findTopByFriendshipUserIdOrderByCreatedAtDesc(Long userId);

    Boolean existsChallengeByFriendshipId(Long friendshipId);
}
