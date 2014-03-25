package com.common.report.excel.business.task;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

import com.common.report.excel.domain.annotation.ExcelField;
import com.common.report.excel.domain.model.formatter.ExcelFieldFormatter;

/**
 * La clase que nos permite crear un reporte con un listado de registros dentro de un archivo de excel.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class CreateSingleExcelReportTask extends GenericTask<Double> {

	private static final long serialVersionUID = 1817771488275361035L;

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(CreateSingleExcelReportTask.class);

	/**
	 * El archivo de excel que contiene el libro que vamos a utilizar dentro de este objeto.
	 */
	protected WritableWorkbook book;
	/**
	 * La hoja del libro que vamos a cargar dentro del archivo con los datos de los registros que queremos cargarle.
	 */
	protected WritableSheet sheet;

	/**
	 * El constructor de un proceso de creaci�n de reportes en excel.
	 */
	public CreateSingleExcelReportTask() {
		super("create single excel report task");
	}

	/**
	 * @see com.common.util.model.thread.GenericTask#beforeExecute()
	 */
	@Override
	protected void beforeExecute() {
		CreateSingleExcelReportTask.log.trace("before execute");

		try {
			// Creamos el libro del archivo que vamos a llenar.
			this.book = Workbook.createWorkbook(new File(this.getFileName()));
			// Creamos la primer hoja del libro.
			this.sheet = this.book.createSheet(this.getSheetName(), 0);
		} catch (IOException e) {
			CreateSingleExcelReportTask.log.error("before execute failed", e);
		}
	}

	/**
	 * @see com.common.util.model.thread.GenericTask#execute()
	 */
	@Override
	protected void execute() {
		CreateSingleExcelReportTask.log.trace("execute");

		try {
			this.monitor.setValue(0.0);

			// Formateamos las columnas.
			this.columnsFormat();
			this.monitor.setValue(0.1);

			// Agregamos los titulos de las columnas.
			this.columnsTitles();
			this.monitor.setValue(0.2);

			// Obtenemos los datos.
			this.getData();
			this.monitor.setValue(0.5);

			// Agregamos los datos.
			this.addData();
			this.monitor.setValue(1.0);

			// Escribimos los datos en el archivo y lo cerramos.
			this.book.write();
			this.book.close();

		} catch (Exception e) {
			CreateSingleExcelReportTask.log.error("execute failed", e);
		}
	}

	/**
	 * La funci�n encargada de retornar el nombre del archivo que vamos a crear.
	 * 
	 * @return El nombre del archivo que vamos a crear.
	 */
	protected abstract String getFileName();

	/**
	 * La funci�n encargada de retornar el nombre que le vamos a poner al libro que vamos a llenar con el listado de registros.
	 * 
	 * @return El nombre del libro que le vamos a poner al libro.
	 */
	protected abstract String getSheetName();

	/**
	 * Funci�n encargada de formatear los anchos de las columnas que van a insertarse dentro del reporte.
	 */
	protected abstract void columnsFormat();

	/**
	 * Funci�n encargada de cargar los t�tulos del reporte y formatearlos.
	 * 
	 * @throws WriteException
	 *             En caso de que ocurra un fallo de escritura.
	 */
	protected abstract void columnsTitles() throws WriteException;

	/**
	 * Funci�n encargada de recuperar desde la base de datos los valores de los registros almacendos dentro del rango de fechas dada.
	 * 
	 * @throws Exception
	 *             En caso de alg�n problema en al recuperaci�n de los datos desde la base de datos.
	 */
	protected abstract void getData() throws Exception;

	/**
	 * Funci�n encargada de cargar la fecha y la hora y los datos recuperados desde la base de datos a la hoja de trabajo.
	 * 
	 * @throws WriteException
	 *             La excepci�n que describe una falla en la escritura de la hoja del libro.
	 */
	protected abstract void addData() throws WriteException;
}