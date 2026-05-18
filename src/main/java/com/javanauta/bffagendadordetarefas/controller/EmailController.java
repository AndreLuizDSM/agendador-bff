package com.javanauta.bffagendadordetarefas.controller;


import com.javanauta.bffagendadordetarefas.business.EmailService;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Tag(name = "Email", description = "Envio de emails")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefaDTOResponse emailDTO){
        emailService.enviaEmail(emailDTO);
            return ResponseEntity.ok().build();
    }
}
