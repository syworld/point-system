package com.world.pointsystem.event.service;

import com.world.pointsystem.event.dao.ReviewEventMapper;
import com.world.pointsystem.event.entity.ReviewEvent;
import com.world.pointsystem.event.enums.Point;
import com.world.pointsystem.event.enums.ReviewAction;
import com.world.pointsystem.event.exception.InvalidReviewActionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

  private final ReviewEventMapper reviewEventMapper;

  public void issue(ReviewEvent reviewEvent, ReviewAction action, String content,
      String[] attachedPhotos) {

    ReviewEvent preparedReviewEvent;

    if (action.equals(ReviewAction.ADD)) {
      preparedReviewEvent = issueAddEvent(reviewEvent, content, attachedPhotos);
    } else if (action.equals(ReviewAction.MOD)) {
      preparedReviewEvent = issueModEvent(reviewEvent, content, attachedPhotos);
    } else if (action.equals(ReviewAction.DELETE)) {
      preparedReviewEvent = issueDeleteEvent(reviewEvent, content, attachedPhotos);
    } else {
      throw new InvalidReviewActionException("No review action matches");
    }

    reviewEventMapper.insert(preparedReviewEvent);
  }

  public ReviewEvent issueAddEvent(ReviewEvent reviewEvent, String content,
      String[] attachedPhotos) {
    // Set default value
    reviewEvent.setAction(ReviewAction.ADD);

    // first point 여부 확인
    Integer firstPointValue;
    if (!isFirstPlaceReviewExists(reviewEvent.getPlaceId())) {
      firstPointValue = Point.PLUS.getValue();
    } else {
      firstPointValue = Point.ZERO.getValue();
    }
    reviewEvent.setFirstPoint(firstPointValue);
    reviewEvent.setContentPoint(getPointByContent(content, false));
    reviewEvent.setImagePoint(getPointByAttachedPhotos(attachedPhotos, false));

    return reviewEvent;
  }

  public ReviewEvent issueModEvent(ReviewEvent reviewEvent, String content,
      String[] attachedPhotos) {
    // Set default value
    reviewEvent.setAction(ReviewAction.MOD);
    reviewEvent.setFirstPoint(Point.ZERO.getValue());

    // 가장 최근 review event 호출
    ReviewEvent recentReviewEvent = getUserRecentReviewEvent(reviewEvent.getPlaceId(),
        reviewEvent.getUserId());

    boolean isAddedRecentContentPoint = recentReviewEvent.getContentPoint()
        .equals(Point.PLUS.getValue());
    boolean isAddedRecentImagePoint = recentReviewEvent.getImagePoint()
        .equals(Point.PLUS.getValue());

    reviewEvent.setContentPoint(getPointByContent(content, isAddedRecentContentPoint));
    reviewEvent.setImagePoint(
        getPointByAttachedPhotos(attachedPhotos, isAddedRecentImagePoint));
    return reviewEvent;
  }

  public ReviewEvent issueDeleteEvent(ReviewEvent reviewEvent, String content,
      String[] attachedPhotos) {
    // Set default value
    reviewEvent.setAction(ReviewAction.DELETE);
    reviewEvent.setContentPoint(Point.ZERO.getValue());
    reviewEvent.setImagePoint(Point.ZERO.getValue());

    // Place의 첫 번째 리뷰이면 firstPoint = -1
    Integer firstPointValue;
    if (reviewEvent.getFirstPoint().equals(Point.PLUS.getValue())) {
      firstPointValue = Point.MINUS.getValue();
    } else {
      firstPointValue = Point.ZERO.getValue();
    }
    reviewEvent.setFirstPoint(firstPointValue);

    return reviewEvent;
  }


  public boolean isFirstPlaceReviewExists(String placeId) {
    ReviewEvent reviewEvent = reviewEventMapper.findByPlaceIdAndFirstPoint(placeId);
    // 가장 최근의 first_point = 1 이면 존재하므로 true 반환
    return reviewEvent != null && reviewEvent.getFirstPoint() == 1;
  }


  public ReviewEvent getUserRecentReviewEvent(String placeId, String userId) {
    return reviewEventMapper.findRecentByUserIdAndPlaceId(placeId, userId);
  }


  public Integer getUserCurrentPoint(String userId) {
    return reviewEventMapper.countPointByUserId(userId);
  }


  public Integer getPointByContent(String content, boolean isAddedBefore) {
    if (!isAddedBefore && content.length() >= 1) {
      return Point.PLUS.getValue();
    } else if (isAddedBefore && content.length() < 1) {
      return Point.MINUS.getValue();
    } else {
      return Point.ZERO.getValue();
    }
  }

  public Integer getPointByAttachedPhotos(String[] attachedPhotos, boolean isAddedBefore) {
    if (!isAddedBefore && attachedPhotos.length >= 1) {
      return Point.PLUS.getValue();
    } else if (isAddedBefore && attachedPhotos.length < 1) {
      return Point.MINUS.getValue();
    } else {
      return Point.ZERO.getValue();
    }
  }
}
