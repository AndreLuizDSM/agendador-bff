package com.javanauta.bffagendadordetarefas.infrastructure.client;

import com.javanauta.bffagendadordetarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping()
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,@RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario (@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String loginUsuario (@RequestBody LoginDTORequest loginDTORequestDTO);

    @DeleteMapping("/{email}") // var na URI , nome deve ser igual ao parâmetro
    void deletaUsuarioPorEmail(@PathVariable String email, @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse salvarTelefone(@RequestHeader(name = "Authorization", required = false) String token,
                                       @RequestBody TelefoneDTORequest telefoneDTO);

    @PostMapping("/endereco")
    EnderecoDTOResponse salvarEndereco(@RequestHeader(name = "Authorization", required = false) String token,
                                       @RequestBody EnderecoDTORequest enderecoDTO);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                               @RequestParam("id") Long id,
                                               @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                               @RequestParam("id") Long id,
                                               @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/endereco/{cep}/")
    ViaCepDTOResponse buscarCep(@PathVariable("cep") String cep);
}




