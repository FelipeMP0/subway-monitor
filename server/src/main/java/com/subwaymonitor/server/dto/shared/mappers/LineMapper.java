package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.LineDto;
import com.subwaymonitor.sharedmodel.Line;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LineMapper {

  LineMapper INSTANCE = Mappers.getMapper(LineMapper.class);

  LineDto lineToLineDto(Line line);
}
