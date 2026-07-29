package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.dto.post.UsuarioCreateDTO;
import com.example.demo.dto.get.UsuarioGetDTO;
import com.example.demo.model.entity.UsuarioEntity;
import com.example.demo.utils.CpfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuRep;

    @Transactional
    public void criarAluno(UsuarioCreateDTO x) {
        if (!CpfUtils.isValido(x.getCpf())) {
            throw new RuntimeException("CPF invalido");
        }

        if (usuRep.existsByCpf(x.getCpf())) {
            throw new RuntimeException("Usuario com este CPF ja existe");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(x.getNome());
        usuario.setCpf(x.getCpf());
        usuario.setDataNascimento(x.getDataNascimento());
        usuario.setCep(x.getCep());

        usuRep.save(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioGetDTO pegarAlunoPeloId(Long id) {
        UsuarioEntity usuario = usuRep.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        return new UsuarioGetDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getCriadoEm());
    }

}
