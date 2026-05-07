package com.javanauta.bffagendadordetarefas.controller;

import com.javanauta.bffagendadordetarefas.business.UsuarioService;
import com.javanauta.bffagendadordetarefas.business.ViaCepService;
import com.javanauta.bffagendadordetarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadordetarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendadordetarefas.business.dto.out.ViaCepDTOResponse;
import com.javanauta.bffagendadordetarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ViaCepService cepService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Criar e cadastrar usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Login realizado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginDTORequest usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar endereço", description = "Criar e cadastrar endereço")
    @ApiResponse(responseCode = "200", description = "Endereço criado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> salvarEndereco(@RequestHeader(name = "Authorization", required = false) String token,
                                                              @RequestBody EnderecoDTORequest enderecoDTO) {
        return ResponseEntity.ok(usuarioService.cadastroEnderecoDTO(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salvar telefone", description = "Criar e cadastrar telefone")
    @ApiResponse(responseCode = "200", description = "Telefone criado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> salvarTelefone(@RequestHeader(name = "Authorization", required = false) String token,
                                                              @RequestBody TelefoneDTORequest telefoneDTO){
        return ResponseEntity.ok(usuarioService.cadastroTelefoneDTO(token, telefoneDTO));
    }

    @GetMapping
    @Operation(summary = "Buscar Usuário", description = "Buscar um usuário por email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarEmail(@RequestParam("email") String email,
                                                          @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.retornarEmail(email, token));
    }

    @DeleteMapping("/{email}") // var na URI , nome deve ser igual ao parâmetro
    @Operation(summary = "Deletar Usuário", description = "Deletar usuário por email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletarPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Usuário", description = "Atualizar dados de um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço", description = "Atualizar dados de um endereço")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizarDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                      @RequestParam("id") Long id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone", description = "Atualizar dados de um telefone")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizarDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                      @RequestParam("id") Long id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));
    }
    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Buscar cep", description = "Buscar dados de um cep")
    @ApiResponse(responseCode = "200", description = "Dados do cep enviado!")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarCep (@PathVariable("cep") String cep) {
        return ResponseEntity.ok(cepService.buscaCep(cep));
    }
}
