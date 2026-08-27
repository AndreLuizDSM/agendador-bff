package com.javanauta.bffagendadordetarefas.business;

import com.javanauta.bffagendadordetarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendadordetarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvarUsuario (UsuarioDTORequest usuarioDTO) {   // Recebi um UsuarioDTO

        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public UsuarioDTOResponse retornarEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public String loginUsuario(LoginDTORequest loginDTORequestDTO) {
        log.info("Usuario logado com sucesso");
        return usuarioClient.loginUsuario(loginDTORequestDTO);
    }

    public void deletarPorEmail(String email, String token) {

        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarUsuario (String token, UsuarioDTORequest usuarioDTO) {
        log.info("Usuario atualizado com sucesso");
        return usuarioClient.atualizarDadosUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
            return usuarioClient.atualizarDadosEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
            return usuarioClient.atualizarDadosTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastroEnderecoDTO (String token , EnderecoDTORequest enderecoDTO) {
            return usuarioClient.salvarEndereco(token, enderecoDTO);
    }

    public TelefoneDTOResponse cadastroTelefoneDTO (String token , TelefoneDTORequest telefoneDTO) {
            return usuarioClient.salvarTelefone(token, telefoneDTO);
    }

    public void deletarTelefone (Long id, String token) {
        log.info("Telefone deletado com sucesso");
        usuarioClient.deletarTelefone(id, token);
    }

    public void deletarEndereco (Long id, String token) {
        log.info("Endereco deletado com sucesso");
        usuarioClient.deletarEndereco(id, token);
    }
}
