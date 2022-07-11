package com.world.pointsystem.event.entity;

import com.world.pointsystem.event.enums.ReviewAction;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReviewEvent {

  private Long id;
  private String userId;
  private String reviewId;
  private String placeId;
  private Integer contentPoint;
  private Integer imagePoint;
  private Integer firstPoint;
  private ReviewAction action;
  private LocalDateTime createdAt;

}
