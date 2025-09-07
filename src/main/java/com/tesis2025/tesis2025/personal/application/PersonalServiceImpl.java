package com.tesis2025.tesis2025.personal.application;

import com.tesis2025.tesis2025.pedido.domain.Pedido;
import com.tesis2025.tesis2025.pedido.dto.CreatePedidoRequest;
import com.tesis2025.tesis2025.pedido.dto.PedidoResponse;
import com.tesis2025.tesis2025.pedido.dto.UpdatePedidoRequest;
import com.tesis2025.tesis2025.personal.domain.Personal;
import com.tesis2025.tesis2025.personal.dto.*;
import com.tesis2025.tesis2025.personal.repository.PersonalRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonalServiceImpl implements PersonalService{

    private final PersonalRepository repo;

    @Transactional(readOnly = true)
    @Override
    public Page<PersonalResponse> list(String q, Pageable pageable){
        Page<Personal> page;
        if (q == null ||  q.isBlank()){
            page = repo.findAll(pageable);
        }else{
            page = repo
            .findByNombreContainingIgnoreCaseOrCargoContainingIgnoreCaseOrDniContainingIgnoreCase(
                q, q, q, pageable
                );
        }
        return page.map(this::toResponse);
    }

    @Override
    @Transactional
    public PersonalResponse update(UUID id, UpdatePersonalRequest r) {
        var p = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personal no encontrado: " + id));

        if (r.nombre() != null) p.setNombre(trim(r.nombre()));
        // aplica más campos del request cuando los agregues

        var saved = repo.save(p);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonalResponse get(UUID id) {
        var c = repo.findById(id).orElseThrow();
        return toResponse(c);
    }

    
    @Override
    public PersonalResponse create(CreatePersonalRequest r) {
        var c = Personal.builder()
                .nombre(trim(r.nombre()))
                .cargo(trim(r.cargo()))
                .dni(trim(r.dni()))
                .build();
        return toResponse(repo.save(c));
    }
    
    private PersonalResponse toResponse(Personal c) {
        return new PersonalResponse( 
                c.getIdPersonal(), c.getNombre(), c.getCargo(),
                c.getDni()
        );
    }

    private static String trim(String s) { return s == null ? null : s.trim(); }
}