package br.com.forumalura.exception.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoCampoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoCampoExceptionResponse> handle(MethodArgumentNotValidException ex) {
		List<ErroValidacaoCampoExceptionResponse> response = new ArrayList<ErroValidacaoCampoExceptionResponse>();
		
		List<FieldError> fielErros = ex.getBindingResult().getFieldErrors();
		
		fielErros.forEach(e -> {
			String msgErro = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroValidacaoCampoExceptionResponse objErro = new ErroValidacaoCampoExceptionResponse(e.getField(), msgErro, HttpStatus.BAD_REQUEST.toString());
			response.add(objErro);
		});
		
		return response;
	}

}
