package com.javanauta.bffagendadordetarefas.business;

import com.javanauta.bffagendadordetarefas.business.dto.in.TarefaDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import com.javanauta.bffagendadordetarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadordetarefas.infrastructure.client.TarefaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    public final TarefaClient tarefaClient;

    public TarefaDTOResponse gravarTarefa (TarefaDTORequest tarefaDTO, String token) {

        return tarefaClient.salvarTarefa(tarefaDTO, token);
    }

    public List<TarefaDTOResponse> buscarTarefaGravadaPorPerido(LocalDateTime dataInicial, LocalDateTime dataFinal,
                                                                String token){

        return tarefaClient.buscaListaDeTarefaPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaDTOResponse> buscarTarefaGravadaPorEmail(String token) {
        return tarefaClient.buscaListaDeTarefaPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {

        tarefaClient.deletaTarefaPorId(id, token);
    }

    public TarefaDTOResponse alterarStatus (StatusNotificacaoEnum status, String id, String token) {
       return tarefaClient.alteraStatus(id, status ,token);
    }

    public TarefaDTOResponse alterarTarefa (TarefaDTORequest tarefaDTO, String id, String token) {
        return tarefaClient.alteraTarefa(tarefaDTO, id, token);
    }
}
