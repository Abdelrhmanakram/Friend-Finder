package com.boot.start.friend_finder.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendshipResponseDto {

    private Long friendshipId;
    private Long requesterId;
    private String requesterUsername;
    private Long receiverId;
    private String receiverUsername;
    private String status;
}
