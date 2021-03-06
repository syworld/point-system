package com.world.pointsystem.event.dao;

import com.world.pointsystem.event.dto.SumPointDto;
import com.world.pointsystem.event.entity.ReviewEvent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewEventMapper {

  void insert(ReviewEvent reviewEvent);

  SumPointDto findSumPointByReviewId(String reviewId);

  ReviewEvent findAddEventByUserIdAndPlaceId(String placeId, String userId);

  ReviewEvent findByPlaceIdAndFirstPoint(String placeId);

  Integer countPointByUserId(String userId);

}
