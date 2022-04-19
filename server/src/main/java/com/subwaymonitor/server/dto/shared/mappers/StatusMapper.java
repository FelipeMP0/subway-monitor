package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.StatusDto;
import com.subwaymonitor.sharedmodel.Status;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusMapper {

  StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

  StatusDto toDto(Status status);
}
