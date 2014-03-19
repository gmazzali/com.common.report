package com.common.report.excel.exception;

import com.common.util.exception.UncheckedException;
import com.common.util.exception.error.ErrorDetail;
import com.common.util.exception.error.Errors;

/**
 * Las excepciones no chequeadas que vamos a utilizar para el manejo de archivos de excel.
 * 
 * @see UncheckedException
 * 
 * @since 19/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class UncheckedExcelException extends UncheckedException {

	private static final long serialVersionUID = 1L;

	/**
	 * El constructor de una instancia de {@link UncheckedExcelException} que no recibe par�metros.
	 */
	public UncheckedExcelException() {
		super();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedExcelException} que recibe como par�metro un elemento {@link Throwable} para mantener el
	 * problema que produjo el lanzamiento de esta {@link UncheckedExcelException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 */
	public UncheckedExcelException(Throwable cause) {
		super(cause);
	}

	/**
	 * El constructor de una instancia de {@link UncheckedExcelException} que recibe como par�metro el conjunto de {@link Errors} que vamos a
	 * contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public UncheckedExcelException(Errors errors) {
		super(errors);
	}

	/**
	 * El constructor de una instancia de {@link UncheckedExcelException} que recibe como par�metro un mensaje de {@link ErrorDetail} que vamos a
	 * crear en el momento.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public UncheckedExcelException(String message, Object... parameter) {
		super(message, parameter);
	}

	/**
	 * El constructor de una instancia de {@link UncheckedExcelException} que recibe como par�metro el {@link Throwable} para mantener el problema que
	 * produjo el lanzamiento de esta {@link UncheckedExcelException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public UncheckedExcelException(Throwable cause, String message, Object... parameter) {
		super(cause, message, parameter);
	}
}