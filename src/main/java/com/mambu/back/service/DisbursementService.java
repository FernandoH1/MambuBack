package com.mambu.back.service;

import com.mambu.back.model.dto.active.DisbursementDTO;
import com.mambu.back.model.dto.active.DisbursementResponseDTO;
import reactor.core.publisher.Mono;

public interface DisbursementService {
    Mono<DisbursementResponseDTO> doDisbursement(String id);
}
