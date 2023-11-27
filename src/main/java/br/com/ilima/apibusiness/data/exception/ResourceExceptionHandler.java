package br.com.ilima.apibusiness.data.exception;

import br.com.ilima.apibusiness.domain.exception.BusinessException;
import br.com.ilima.apibusiness.domain.exception.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ResourceExceptionHandler {

    private MessageSource messageSource;

    public ResourceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public List<CampoInvalido> handlerArgumentNotValid(ConstraintViolationException exception) {

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<CampoInvalido> camposInvalido = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            camposInvalido.add(new CampoInvalido(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return camposInvalido;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public CampoInvalido handlerNotFoundException(NotFoundException exception) {
        CampoInvalido campoInvalido = new CampoInvalido(exception.getCampo(), exception.getMensagem());
        return campoInvalido;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BusinessException.class})
    public CampoInvalido handlerBusinessException(BusinessException exception) {
        CampoInvalido campoInvalido = new CampoInvalido(exception.getCampo(), exception.getMensagem());
        return campoInvalido;
    }


}
