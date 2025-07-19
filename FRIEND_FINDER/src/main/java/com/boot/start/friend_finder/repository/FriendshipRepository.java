package com.boot.start.friend_finder.repository;

import com.boot.start.friend_finder.model.Friendship;
import com.boot.start.friend_finder.model.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Optional<Friendship> findByRequesterAndReceiver(User requester, User receiver);
    List<Friendship> findByReceiverAndStatus(User receiver, Friendship.FriendshipStatus status);
    List<Friendship> findByRequesterAndStatus(User requester, Friendship.FriendshipStatus status);
    List<Friendship> findByStatusAndRequesterOrReceiver(Friendship.FriendshipStatus status, User requester, User receiver);
    boolean existsByRequesterAndReceiverAndStatus(User a, User b, Friendship.FriendshipStatus status);
    boolean existsByRequesterAndReceiverOrReceiverAndRequesterAndStatus(User a, User b, User b2, User a2, Friendship.FriendshipStatus status);
}
