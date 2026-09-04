package br.edu.ifsp.biblioteca.exception;

public class ErroCadastroException extends RuntimeException{
    public ErroCadastroException(String mensagem){
        super(mensagem);
    }

}
