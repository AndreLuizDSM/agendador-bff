package com.javanauta.bffagendadordetarefas.infrastructure.client.config;

import com.javanauta.bffagendadordetarefas.infrastructure.exceptions.BusinessException;
import com.javanauta.bffagendadordetarefas.infrastructure.exceptions.ConflictException;
import com.javanauta.bffagendadordetarefas.infrastructure.exceptions.IllegalArgumentException;
import com.javanauta.bffagendadordetarefas.infrastructure.exceptions.ResourceNotFound;
import com.javanauta.bffagendadordetarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = "Erro: " + mensagemErro(response);


        switch (response.status()){
            case 409 : return new ConflictException(mensagemErro);
            case 403 : return new ResourceNotFound(mensagemErro);
            case 401 : return new UnauthorizedException(mensagemErro);
            case 400 : return new IllegalArgumentException(mensagemErro);
            default : return new BusinessException(mensagemErro);
        }
    }

    private String mensagemErro(Response response){
        try {
            if (Objects.isNull(response)) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ResourceNotFound("Erro" , e.getCause());
        }

    }
}
