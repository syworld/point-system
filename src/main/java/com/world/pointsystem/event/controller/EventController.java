package com.world.pointsystem.event.controller;

import com.world.pointsystem.event.dto.request.EventRequest;
import com.world.pointsystem.event.dto.response.PointResponse;
import com.world.pointsystem.event.entity.ReviewEvent;
import com.world.pointsystem.event.mapper.ReviewEventMapstructMapper;
import com.world.pointsystem.event.service.EventService;
import com.world.pointsystem.global.exception.GlobalExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "events")
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

  private final EventService eventService;
  private final ReviewEventMapstructMapper reviewEventMapstructMapper;

  @Operation(summary = "리뷰 작성 이벤트 생성")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "REVIEW EVENT CREATED"),
      @ApiResponse(responseCode = "400", description = "INVALID INPUT", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public void create(@RequestBody @Validated EventRequest.Creation eventRequest) {
    ReviewEvent reviewEvent = reviewEventMapstructMapper.toReviewEvent(eventRequest);
    eventService.issue(reviewEvent, eventRequest.getAction(), eventRequest.getContent(),
        eventRequest.getAttachedPhotoIds());
  }


  @Operation(summary = "유저 아이디로 현재 point 조회", description = "유저 아이디로 해당 유저의 현재 point 조회하기")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PointResponse.class))),
      @ApiResponse(responseCode = "404", description = "Review Event NOT FOUND", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @Parameters({
      @Parameter(name = "userId", description = "유저 아이디", example = "1e2e0ef2-92b7-4817-a5f3-0c575361f745"),
  })
  @GetMapping("/points")
  public ResponseEntity<PointResponse> getUserEventPoints(
      @RequestParam(value = "userId") String userId) {
    Integer point = eventService.getUserCurrentPoint(userId);
    PointResponse pointResponse = new PointResponse(userId, point);
    return ResponseEntity.ok().body(pointResponse);
  }

}
