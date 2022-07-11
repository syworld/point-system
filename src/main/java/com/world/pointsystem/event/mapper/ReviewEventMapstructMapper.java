package com.world.pointsystem.event.mapper;

import com.world.pointsystem.event.dto.request.EventRequest;
import com.world.pointsystem.event.dto.response.EventResponse;
import com.world.pointsystem.event.entity.ReviewEvent;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public interface ReviewEventMapstructMapper {

  ReviewEvent toReviewEvent(EventRequest.Creation eventRequest);

  EventResponse toResponse(ReviewEvent reviewEvent);

}
