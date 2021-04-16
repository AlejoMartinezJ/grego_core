package com.grego.support;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.grego.support.annotations.DataRootName;
import com.grego.support.constants.SupportConstants;
import com.grego.support.exception.ExceptionDetailImpl;
import com.grego.support.exception.GenericException;
import com.grego.support.exception.ResourceNotFoundException;
import com.grego.support.exception.domain.ErrorInfo;
import com.grego.support.exception.domain.GenericResponse;
import com.grego.support.exception.enums.ExceptionDetail;
import com.grego.support.exception.enums.GenericExceptionDetail;

@ControllerAdvice
public class GenericResource {

	public GenericResource() {
		System.err.println("entroooooo");
	}
	// private static final Logger LOGGER = Logger.getLogger(GenericResource.class);

	@ExceptionHandler({ NoHandlerFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public GenericResponse resourceNotFoundhander(NoHandlerFoundException exception) {
		// LOGGER.error("Not fount: ", exception);
		System.err.println("entroooooo1ac");
		return buildUnsuccessfulResponseWithExceptionDetail(SupportConstants.MSG_GENERICO,
				GenericExceptionDetail.GENERIC_APPLICATION_ERROR);
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public GenericResponse resourceNotFound(ResourceNotFoundException exception) {
		// LOGGER.error("Not fount: ", exception);
		System.err.println("entroooooo1");
		return buildUnsuccessfulResponseWithExceptionDetail(exception.getExceptionDetail());
	}
	
	@ExceptionHandler({ ConnectException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public GenericResponse resourceNotConnet(ConnectException exception) {
		// LOGGER.error("Not fount: ", exception);
		//agregado para excepcion de conexion
		return buildUnsuccessfulResponseWithExceptionDetail(SupportConstants.MSG_GENERICO,
		GenericExceptionDetail.GENERIC_TECHNICAL_ERROR_CONNET_API);
	}
	
	
	
	
	
//	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ResponseBody
//	public GenericResponse resourceNotSupport(HttpRequestMethodNotSupportedException exception) {
//		//LOGGER.error("Not fount: ", exception);		
//		System.err.println("resourceNotSupport");
//		return buildUnsuccessfulResponseWithExceptionDetail(exception.GenericExceptionDetail.GENERIC_APPLICATION_ERROR);
//	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public GenericResponse defaultError(Exception exception) {
		// LOGGER.error("Uncontrolled Server Error: " + exception.toString(),
		// exception);
		System.err.println("entroooooo2");

		return buildUnsuccessfulResponseWithExceptionDetail(SupportConstants.MSG_GENERICO,
				GenericExceptionDetail.GENERIC_APPLICATION_ERROR);
	}
//	si es jpa
//	@ExceptionHandler({ DataAccessException.class })
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ResponseBody
//	public GenericResponse dataAccessError(DataAccessException exception) {
//		System.err.println("Database access error : " + exception.toString());
//		return buildUnsuccessfulResponseWithExceptionDetail(SupportConstants.MSG_GENERICO,
//				GenericExceptionDetail.GENERIC_TECHNICAL_ERROR_OF_ACCESS_TO_DATABASE);
//
//		// return
//		// buildUnsuccessfulResponseWithExceptionDetail(CoreConstants.MSG_GENERICO,
//		// GenericExceptionDetail.GENERIC_TECHNICAL_ERROR_GENERIC_TO_DATABASE,exception.getMessage());
//	}

	@ExceptionHandler(value = GenericException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public GenericResponse genericCustomizeError(final GenericException exception) {
		System.err.println("bag");
		String message = "";
		if ("AnkaAS400Exception".equals(exception.getClass().getSimpleName())) {
			message = SupportConstants.MSG_GENERICO;
			// LOGGER.error("AS400 access error :", exception);
		} else {
			message = exception.getMessage();
			// LOGGER.error("Generic Bad Request.", exception);
		}
		return buildUnsuccessfulResponseWithExceptionDetail(message, exception.getExceptionDetail());
	}

	protected final GenericResponse buildUnsuccessfulResponseWithExceptionDetail(String message,
			ExceptionDetail exceptionDetail) {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.addError(exceptionDetail.getDescription(), exceptionDetail.getKey(), message);
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(false);
		return genericResponse;
	}

	// **********************************
	protected final GenericResponse buildUnsuccessfulResponseWithExceptionDetail(String message,
			ExceptionDetail exceptionDetail, String... values) {
		GenericResponse genericResponse = new GenericResponse();
		exceptionDetail = getCustomDetail(exceptionDetail, values);
		genericResponse.addError(exceptionDetail.getDescription(), exceptionDetail.getKey(), message);
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(false);
		return genericResponse;
	}

	private ExceptionDetail getCustomDetail(ExceptionDetail exDetail, String... values) {
		return new ExceptionDetailImpl(exDetail.getKey(), generateCustomMessage(exDetail.getDescription(), values));
	}

	private String generateCustomMessage(String description, String[] values) {
		int sizeValues = values.length;
		String message = new String(description);
		for (int i = 0; i < sizeValues; i++) {
			message = StringUtils.replace(message, "{" + i + "}", values[i]);
		}
		return message;
	}

	// **********************************
	protected final GenericResponse buildUnsuccessfulResponseWithExceptionDetail(ExceptionDetail exceptionDetail) {
		System.err.println("buildUnsuccessfulResponseWithExceptionDetail");
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.addError(exceptionDetail.getKey(), exceptionDetail.getDescription());
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(false);
		return genericResponse;
	}

	protected final GenericResponse buildUnsuccessfulResponse(GenericException exception) {
		System.err.println("buildUnsuccessfulResponse");

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.addError(exception.getExceptionDetail().getDescription(),
				exception.getExceptionDetail().getKey(), exception.getMessage());
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(false);
		return genericResponse;
	}

	protected final GenericResponse buildUnsuccessfulResponse(Exception exception) {
		System.err.println("buildUnsuccessfulResponse");

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.addError(GenericExceptionDetail.GENERIC_APPLICATION_ERROR.getKey(),
				GenericExceptionDetail.GENERIC_APPLICATION_ERROR.getDescription());
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(false);
		return genericResponse;
	}

	protected final GenericResponse buildUnsuscessfulResponse(List<ErrorInfo> errors) {
		System.err.println("buildUnsuscessfulResponse");

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setErrors(errors);
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(false);
		return genericResponse;
	}

	protected final GenericResponse buildSuccessfulResponse(Object data) {
		System.err.println("buildSuccessfulResponse");

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setData(data);
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(true);
		return genericResponse;
	}

	protected final GenericResponse buildSuccessfulResponseNode(Map<String, Object> data) {
		GenericResponse genericResponse = new GenericResponse();
		Map<String, Object> nodeRoot = new HashMap<>();
		nodeRoot.put(((DataRootName) getClass().getAnnotation(DataRootName.class)).name(), data);
		genericResponse.setData(data);
		genericResponse.setLastUpdate(new Date());
		genericResponse.setSuccess(true);
		return genericResponse;
	}


	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public GenericResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ErrorInfo> errors = new ArrayList<>();
		System.err.println("entrooooooholaa");
		ex.getFieldErrors().forEach(e->{
			ErrorInfo errorInfo = new ErrorInfo(SupportConstants.MSG_VALID_REQUEST,
					GenericExceptionDetail.GENERIC_TECHNICAL_ERROR_VALID_REQUEST.getKey(),e.getField()+" "+ e.getDefaultMessage());
			errors.add(errorInfo);
		});
		return buildUnsuscessfulResponse(errors);
	}
}
