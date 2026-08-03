package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.demo.dto.post.AvaliacaoFisicaCreateDTO;
import com.example.demo.dto.get.AvaliacaoFisicaGetDTO;
import com.example.demo.dto.put.AvaliacaoFisicaPutDTO;
import com.example.demo.model.entity.AvaliacaoFisicaEntity;
import com.example.demo.model.repository.AvaliacaoFisicaRepository;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.model.entity.UsuarioEntity;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

    private final UsuarioRepository usuRep;
    private final AvaliacaoFisicaRepository avaFisicaRep;

    @Transactional
    public void criarAvaliacaoFisica(AvaliacaoFisicaCreateDTO x) {

        UsuarioEntity usu = usuRep.findById(x.getUsuarioId())
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if(usu.getAvaliacaoFisica() != null) {
            throw new RuntimeException("Avaliacao fisica ja existe");
        }

        AvaliacaoFisicaEntity avaFisica = new AvaliacaoFisicaEntity();
        avaFisica.setPeso(x.getPeso());
        avaFisica.setAltura(x.getAltura());
        avaFisica.setDataAvaliacao(LocalDate.now());

        avaFisica = avaFisicaRep.save(avaFisica);

        usu.setAvaliacaoFisica(avaFisica);
        usuRep.save(usu);
    }

    @Transactional(readOnly = true)
    public AvaliacaoFisicaGetDTO buscarAvaliacaoFisica(Long usuarioId) {

        UsuarioEntity usu = usuRep.findById(usuarioId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        AvaliacaoFisicaEntity avaFisica = usu.getAvaliacaoFisica();

        if(avaFisica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliacao fisica nao encontrada");
        }

        return new AvaliacaoFisicaGetDTO(
            avaFisica.getId(),
            avaFisica.getPeso(),
            avaFisica.getAltura(),
            avaFisica.getDataAvaliacao());
    }

    @Transactional
    public void updateAvaliacaoFisica(Long usuarioId, AvaliacaoFisicaPutDTO x) {

        UsuarioEntity usu = usuRep.findById(usuarioId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        AvaliacaoFisicaEntity avaFisica = usu.getAvaliacaoFisica();

        if(avaFisica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliacao fisica nao encontrada");
        }

        avaFisicaRep.updateAvaliacaoFisicaById(
            avaFisica.getId(),
            x.peso(),
            x.altura(),
            x.dataAvaliacao());
    }

    @Transactional
    public void deletarAvaliacaoFisica(Long usuarioId) {

        UsuarioEntity usu = usuRep.findById(usuarioId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        AvaliacaoFisicaEntity avaFisica = usu.getAvaliacaoFisica();

        if(avaFisica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliacao fisica nao encontrada");
        }

        usu.setAvaliacaoFisica(null);  //remove o vinculo com a coluna.
        usuRep.saveAndFlush(usu);

        avaFisicaRep.deleteById(avaFisica.getId());
    }

}
