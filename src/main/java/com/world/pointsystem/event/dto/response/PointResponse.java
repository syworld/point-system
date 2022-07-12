package com.world.pointsystem.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointResponse {

  private String userId;
  private Integer currentPoint;
}
