package com.javanauta.bffagendadordetarefas.business;

import com.javanauta.bffagendadordetarefas.business.dto.in.EmailDTO;
import com.javanauta.bffagendadordetarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.TarefaDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.TarefaDTOResponse;
import com.javanauta.bffagendadordetarefas.business.enums.StatusNotificacaoEnum;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    // Email e senha fixo para CRON fazer Login
    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    //CRON acessa todas as tasks de 5 em 5 minutos e envia o email
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(converterLoginDTO());
        log.info("login feito");

        OffsetDateTime horaFutura = OffsetDateTime.now().plusHours(1); //Buscar daqui 1 hora
        OffsetDateTime horaFinalUmaHora = OffsetDateTime.now().plusHours(1).plusMinutes(5); // Pra cada 5 minutos,
        // fazer a notificação
        List<TarefaDTOResponse> listaTarefas = tarefaService.buscarTarefaGravadaPorPerido
                                                (horaFutura, horaFinalUmaHora, token);
        log.info("lista pega " + listaTarefas);

        for (TarefaDTOResponse lista: listaTarefas) {
            tarefaService.alterarStatus(StatusNotificacaoEnum.NOTIFICADO, lista.getId(), token);
            log.info("passado pelo email " + lista.getEmailUsuario());
            emailService.enviaEmail(converterTarefaToEmail(lista));
        }
    }

    public EmailDTO converterTarefaToEmail(TarefaDTOResponse response){
        return EmailDTO.builder()
                .nomeTarefa(response.getNomeTarefa())
                .descricao(response.getDescricao())
                .email(response.getEmailUsuario())
                .dataEvento(response.getDataEvento())
                .build();
    }

    public String login(LoginDTORequest dto) {

        return usuarioService.loginUsuario(dto);
    }

    // Login com email e senha fixos para ativar o CRON e permitir o acesso ao banco de dados
    public LoginDTORequest converterLoginDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
