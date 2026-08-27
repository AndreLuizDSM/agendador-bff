package com.javanauta.bffagendadordetarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EmailDTO(
        String email,
        String nomeTarefa,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataEvento
) {
}
