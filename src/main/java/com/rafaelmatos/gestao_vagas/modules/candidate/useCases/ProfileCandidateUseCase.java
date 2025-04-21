package com.rafaelmatos.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafaelmatos.gestao_vagas.modules.candidate.CandidateRepository;
import com.rafaelmatos.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidade){
       var candidate =  this.candidateRepository.findById(idCandidade)
       .orElseThrow(()->{
        throw new UsernameNotFoundException("User not found");
       });

       var candidateDTO = ProfileCandidateResponseDTO.builder()
       .description(candidate.getDescription())
       .username(candidate.getUsername())
       .email(candidate.getEmail())
       .name(candidate.getName())
       .id(candidate.getId())
       .build();

       return candidateDTO;
    }
    
}
