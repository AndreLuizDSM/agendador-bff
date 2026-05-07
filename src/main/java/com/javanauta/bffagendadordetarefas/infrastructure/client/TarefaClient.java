package com.javanauta.bffagendadordetarefas.infrastructure.client;

import com.javanauta.bffagendadordetarefas.business.dto.in.TarefaDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import com.javanauta.bffagendadordetarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDTOResponse salvarTarefa (@RequestBody TarefaDTORequest tarefaDTO,
                                    @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscaListaDeTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    List<TarefaDTOResponse> buscaListaDeTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam String id,
                           @RequestHeader(name = "Authorization", required = false) String token);

    @PatchMapping
    TarefaDTOResponse alteraStatus (@RequestParam("id") String id,
                                    @RequestParam("status") StatusNotificacaoEnum status,
                                    @RequestHeader("Authorization")String token);

    @PutMapping
    TarefaDTOResponse alteraTarefa (@RequestBody TarefaDTORequest tarefaDTO,
                                    @RequestParam("id") String id,
                                    @RequestHeader(name = "Authorization", required = false) String token);
}




