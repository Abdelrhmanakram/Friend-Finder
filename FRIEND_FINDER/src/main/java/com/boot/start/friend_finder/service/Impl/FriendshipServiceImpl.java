package com.boot.start.friend_finder.service.Impl;

import com.boot.start.friend_finder.repository.FriendshipRepository;
import com.boot.start.friend_finder.repository.jwt.UserRepository;
import com.boot.start.friend_finder.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public void sendFriendRequest(FriendRequestDto dto) {
//        User requester = userRepository.findById(dto.getRequesterId()).get();
//        User receiver = userRepository.findById(dto.getReceiverId()).get();
//
//        if (friendshipRepository.findByRequesterAndReceiver(requester,receiver).isPresent()){
//            throw new RuntimeException("Friend request already sent");
//        }
//        Friendship friendship = new Friendship(requester, receiver, Friendship.FriendshipStatus.PENDING);
//        friendshipRepository.save(friendship);
//
//    }

//    @Override
//    public void acceptFriendRequest(Long requestId) {

//    }

//    @Override
//    public void rejectFriendRequest(Long requestId) {

//    }

//    @Override
//    public @org.jetbrains.annotations.NotNull List<FriendDto> getFriends(Long userId) {
//       User user = userRepository.findById(userId).get();
//       List<Friendship> friendships = friendshipRepository.findByStatusAndRequesterOrReceiver(Friendship.FriendshipStatus.ACCEPTED, user, user);
//        return friendships.stream().map(f -> {
//            User friend = f.getRequester().equals(user) ? f.getReceiver() : f.getRequester();
//            return new FriendDto(friend.getId(), friend.getUsername(), friend.getEmail());
//        }).collect(Collectors.toList());
//    }

//    @Override
//    public List<Friendship> getPendingRequests(Long userId) {
//        return List.of();
//    }

//    @Override
//    public boolean areFriends(Long userId1, Long userId2) {
//        return false;
//    }
}
