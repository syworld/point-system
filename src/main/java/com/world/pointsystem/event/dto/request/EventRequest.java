package com.world.pointsystem.event.dto.request;

import com.world.pointsystem.event.enums.EventType;
import com.world.pointsystem.event.enums.ReviewAction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class EventRequest {

  private static final String uuidFormat = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
  private static final String uuidMessage = "Input should be a UUID format string";

  @Getter
  @Builder
  @RequiredArgsConstructor
  public static class Creation {

    @NotNull
    private final EventType type;

    @NotNull
    private final ReviewAction action;

    @NotNull
    @Pattern(regexp = uuidFormat, message = uuidMessage)
    private final String reviewId;

    @NotNull
    private final String content;

    @NotNull
    private final String[] attachedPhotoIds;

    @NotNull
    @Pattern(regexp = uuidFormat, message = uuidMessage)
    private final String userId;

    @NotNull
    @Pattern(regexp = uuidFormat, message = uuidMessage)
    private final String placeId;

  }

}
