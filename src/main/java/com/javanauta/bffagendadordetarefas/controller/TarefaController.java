package com.javanauta.bffagendadordetarefas.controller;

import com.javanauta.bffagendadordetarefas.business.TarefaService;
import com.javanauta.bffagendadordetarefas.business.dto.in.TarefaDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import com.javanauta.bffagendadordetarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadordetarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> salvarTarefa (@RequestBody TarefaDTORequest tarefaDTO,
                                                           @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas por período encontradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false)String token){

        return ResponseEntity.ok(tarefaService.buscarTarefaGravadaPorPerido(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca tarefas por email", description = "Busca tarefas cadastradas por email")
    @ApiResponse(responseCode = "200", description = "Tarefa por email encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefaPorEmail(@RequestHeader(value = "Authorization",
                                                                            required = false) String token){

        //List<TarefaDTO> listaDto = tarefaService.buscarTarefaGravadaPorEmail(token); Jeito simples
        return ResponseEntity.ok(tarefaService.buscarTarefaGravadaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefa por ID", description = "Deleta tarefa pelo Id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefaService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar status de uma tarefa", description = "Altera os status de uma tarefa")
    @ApiResponse(responseCode = "200", description = "Status de tarefa alterado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> alteraStatus (@RequestParam("id") String id,
                                                           @RequestParam("status") StatusNotificacaoEnum status,
                                                           @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.alterarStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Alterar tarefa", description = "Alterar uma tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> alteraTarefa (@RequestBody TarefaDTORequest tarefaDTO,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alterarTarefa(tarefaDTO, id, token));
    }
}
