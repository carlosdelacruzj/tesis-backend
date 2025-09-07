package com.tesis2025.tesis2025.personal.application;

import com.tesis2025.tesis2025.personal.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PersonalService {
    Page<PersonalResponse> list(String q, Pageable pageable);
    PersonalResponse get(UUID id);
    PersonalResponse create(CreatePersonalRequest r);
    PersonalResponse update(UUID id, UpdatePersonalRequest req);
}