package com.javanauta.bffagendadordetarefas.business;

import com.javanauta.bffagendadordetarefas.business.dto.out.ViaCepDTOResponse;
import com.javanauta.bffagendadordetarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    @Autowired
    private UsuarioClient usuarioClient;

    public ViaCepDTOResponse buscaCep(String cep){
        return usuarioClient.buscarCep(cep);
    }
}
