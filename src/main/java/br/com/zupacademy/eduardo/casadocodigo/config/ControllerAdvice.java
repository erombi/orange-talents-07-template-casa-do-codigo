package br.com.zupacademy.eduardo.casadocodigo.config;

import br.com.zupacademy.eduardo.casadocodigo.config.exception.InvalidStateObjectException;
import br.com.zupacademy.eduardo.casadocodigo.config.exception.ResourceNotFoundException;
import br.com.zupacademy.eduardo.casadocodigo.config.validacao.ErroDeFormularioDTO;
import br.com.zupacademy.eduardo.casadocodigo.config.validacao.ErroPadraoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @Autowired
    private MessageSource source;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException exception) {
        List<ErroDeFormularioDTO> errosDto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        //Validações nível de atributo
        fieldErrors.forEach(e -> {
            String mensagem = source.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioDTO erro = new ErroDeFormularioDTO(e.getField(), mensagem);

            errosDto.add(erro);
        });

        // Validações nivel de Classe
        exception.getBindingResult().getGlobalErrors().forEach(e -> {
            errosDto.add(new ErroDeFormularioDTO(e.getObjectName(), e.getDefaultMessage()));
        });

        return errosDto;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ErroDeFormularioDTO handle(SQLIntegrityConstraintViolationException exception) {
        ErroDeFormularioDTO erroDto = new ErroDeFormularioDTO();
        erroDto.setErro("Erro de integridade do banco");

        return erroDto;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErroPadraoDTO handle(ResourceNotFoundException exception) {
        return new ErroPadraoDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), exception.getPath());
    }

}
