package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.VerificationDto;
import com.subwaymonitor.sharedmodel.Verification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {LineStatusMapper.class})
public interface VerificationMapper {

  VerificationMapper INSTANCE = Mappers.getMapper(VerificationMapper.class);

  VerificationDto toDto(Verification verification);
}
