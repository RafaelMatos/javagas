package com.rafaelmatos.gestao_vagas.modules.candidate.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
       super("Usuário já existe");
    }
    
}
