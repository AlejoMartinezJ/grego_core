package com.grego.support.exception.enums;

public enum GenericExceptionDetail implements ExceptionDetail {

	GENERIC_APPLICATION_ERROR("SIS001", "Generic Application Error"),
	GENERIC_NOT_FOUND_EXCEPTION("SIS002", "Generic Not Found"), 
	GENERIC_UNAUTHORIZED("SIS003", "Generic Unauthorized"),
	GENERIC_FORBIDDEN("SIS004", "Generic Forbidden"),
	GENERIC_TECHNICAL_ERROR_OF_ACCESS_TO_DATABASE("SIS005", "Error técnico en consulta a Oracle."),
	GENERIC_TECHNICAL_ERROR_GENERIC_TO_DATABASE("SIS006", "{0}"),
	GENERIC_TECHNICAL_ERROR_VALID_REQUEST("SIS007", "Error validando  en consulta a Base de datos"),
	GENERIC_TECHNICAL_ERROR_CONNET_API("SIS008", "Error técnico en conexión externa."),
	FILE_MIMETYPE_ERROR("FIL001", "El archivo no tiene el formato esperado."),
	FILE_SIZE_ERROR("FIL002", "El archivo sobrepasa el tamaño permitido"),
	FILE_CONTENT_ERROR("FIL003", "El contenido del archivo es incorrecto"),
	FILE_CONTENT_FORMAT_ERROR("FIL004", "La estructura del archivo es incorrecto");

	private String key;
	private String description;

	private GenericExceptionDetail(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

}
