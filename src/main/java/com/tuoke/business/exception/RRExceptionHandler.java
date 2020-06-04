package com.tuoke.business.exception;

import com.tuoke.business.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RRExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public R messageNotReadable(HttpMessageNotReadableException exception){
		logger.error("参数错误"+exception);
		return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"参数错误");
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public R handleMethodVoArgumentNotValidException(MethodArgumentNotValidException ex) {
		FieldError err = ex.getBindingResult().getFieldError();
		String message = "参数{".concat(err.getField()).concat("}").concat(err.getDefaultMessage());
		logger.info("{} -> {}", err.getObjectName(), message);
		return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
	}




	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
}