package com.crio.api.service;

import com.crio.api.domain.usuario.Usuario;
import com.crio.api.domain.usuario.UsuarioRequestDTO;
import com.crio.api.domain.usuario.UsuarioResponseDTO;
import com.crio.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(UsuarioRequestDTO data){
        Usuario newUsuario = new Usuario();
        newUsuario.setNomeCompleto(data.nomeCompleto());
        newUsuario.setEmail(data.email());
        newUsuario.setSenha(data.senha());
        newUsuario.setTipo(data.tipo());
        newUsuario.setCreatedAt(LocalDateTime.now());
        usuarioRepository.save(newUsuario);

        return newUsuario;
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    public Usuario getUserById(UUID id) {
        return  usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public Usuario updateUser(UUID id, UsuarioResponseDTO usuarioResponseDTO) {
        Usuario updatedUsuario = getUserById(id);
        updatedUsuario.setNomeCompleto(usuarioResponseDTO.nomeCompleto());
        updatedUsuario.setEmail(usuarioResponseDTO.nomeCompleto());
        updatedUsuario.setSenha(usuarioResponseDTO.senha());
        updatedUsuario.setTipo(usuarioResponseDTO.tipo());
        updatedUsuario.setUpdatedAt(LocalDateTime.now());

        return usuarioRepository.save(updatedUsuario);
    }

    public void deleteUser(UUID id){
        Usuario usuario = getUserById(id);
        usuarioRepository.delete(usuario);
    }
}
