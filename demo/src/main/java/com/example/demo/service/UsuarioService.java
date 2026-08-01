package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.dto.post.UsuarioCreateDTO;
import com.example.demo.dto.get.UsuarioGetDTO;
import com.example.demo.model.entity.UsuarioEntity;
import com.example.demo.utils.CpfUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import com.example.demo.model.Projection.UsuarioNameCpfAvFProjection;

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
                usuario.getCriadoEm().toLocalDate());
    }

    @Transactional(readOnly = true)
    public List<UsuarioNameCpfAvFProjection> listarTodosPorNome(String nome) {

        List<UsuarioNameCpfAvFProjection> resultadoConsulta = usuRep.findAllNativeProjectionByNome(nome);
        
        if(resultadoConsulta.isEmpty()){
            throw new RuntimeException("Nenhum usuario encontrado");
        }
        return resultadoConsulta;

    }

    @Transactional(readOnly = true)
    public List<UsuarioGetDTO> listarTodosNative(Pageable pageable) {
        List<UsuarioGetDTO> resultadoConsulta = usuRep.findAllNative(pageable);

        if(resultadoConsulta.isEmpty()){
            throw new RuntimeException("Nenhum usuario encontrado");
        }
        
        return resultadoConsulta;
    }
}

