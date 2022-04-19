package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.LineStatusDto;
import com.subwaymonitor.sharedmodel.LineStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {LineMapper.class, StatusMapper.class})
public interface LineStatusMapper {

  LineStatusMapper INSTANCE = Mappers.getMapper(LineStatusMapper.class);

  LineStatusDto toDto(LineStatus lineStatus);
}
