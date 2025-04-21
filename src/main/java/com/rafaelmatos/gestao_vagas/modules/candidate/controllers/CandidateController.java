package com.rafaelmatos.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelmatos.gestao_vagas.modules.candidate.CandidateEntity;
import com.rafaelmatos.gestao_vagas.modules.candidate.CandidateRepository;
import com.rafaelmatos.gestao_vagas.modules.candidate.exceptions.UserFoundException;
import com.rafaelmatos.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.rafaelmatos.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create( @Valid @RequestBody CandidateEntity candidateEntity){
        try {
            var result =  this.createCandidateUseCase.execute(candidateEntity);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
        
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get( HttpServletRequest request){
        var idCandidade = request.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidade.toString()));
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
