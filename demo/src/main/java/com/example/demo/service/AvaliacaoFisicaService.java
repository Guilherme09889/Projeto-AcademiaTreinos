package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.demo.dto.post.AvaliacaoFisicaCreateDTO;
import com.example.demo.model.entity.AvaliacaoFisicaEntity;
import com.example.demo.model.repository.AvaliacaoFisicaRepository;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.model.entity.UsuarioEntity;
import java.time.LocalDateTime;


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
        avaFisica.setDataAvaliacao(LocalDateTime.now());

        avaFisica = avaFisicaRep.save(avaFisica);

        usu.setAvaliacaoFisica(avaFisica);
        usuRep.save(usu);
    }
    
}
