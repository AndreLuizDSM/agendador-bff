package com.javanauta.bffagendadordetarefas.business;

import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import com.javanauta.bffagendadordetarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefaDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }
}
