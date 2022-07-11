package com.world.pointsystem.event.dto.response;

import com.world.pointsystem.event.enums.ReviewAction;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EventResponse {

  private String userId;
  private String reviewId;
  private String placeId;
  private Integer contentPoint;
  private Integer imagePoint;
  private Integer firstPoint;
  private ReviewAction action;
  private LocalDateTime createdAt;

}
