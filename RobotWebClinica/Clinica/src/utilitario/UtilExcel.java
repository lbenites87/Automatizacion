package utilitario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilExcel {
    
        public static int cuentaLinea = 1;
        public static boolean adentro = false;

	public static String[][] leerExcel(String rutaArchivo, String nombreHoja){
		String lista[][] = null;
		int i = 0;
                int j = 0;
		try{
			FileInputStream archivo = new FileInputStream(new File(rutaArchivo));
			XSSFWorkbook archivoExcel = new XSSFWorkbook(archivo);
			XSSFSheet hojaExcel = archivoExcel.getSheet(nombreHoja);
			Iterator<Row> filas = hojaExcel.iterator();
			Row filaPrimera = filas.next();
                        int columnas = filaPrimera.getLastCellNum();
                        System.out.println("COLUMNAS: "+columnas);
			lista = new String[hojaExcel.getLastRowNum()][];
			while(filas.hasNext()){
				Row filaActual = filas.next();
				Iterator<Cell> celdas = filaActual.cellIterator();
				lista[i] = new String[filaActual.getLastCellNum()];
				j = 0;
				//for(int p=0;p<columnas;p++){
                                while(celdas.hasNext()){
					//Cell celdaActual = filaActual.getCell(p);
                                        Cell celdaActual = celdas.next();
                                        String valor;
                                        if(celdaActual==null){
                                            valor = "-";
                                        }else{
                                            try{
                                                if (celdaActual.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                                    valor = Long.toString((long)celdaActual.getNumericCellValue());
                                                }else if((celdaActual.getCellType() == Cell.CELL_TYPE_BLANK)|(celdaActual.getCellType() == Cell.CELL_TYPE_ERROR)){
                                                    valor = "-";
                                                }else{
                                                    valor = celdaActual.getStringCellValue().trim();
                                                    if(valor.length()==0){
                                                        valor = "-";
                                                    }
                                                }
                                            }
                                            catch(Exception ex){
                                                valor = "-";
                                                ex.printStackTrace();
                                            }
                                        }
					lista[i][j] = valor;			
					j++;
				}
				i++;
			}
                        
			archivoExcel.close();
			archivo.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
        
        public static String rutaArchivo;
        public static FileInputStream archivo;
        public static XSSFWorkbook archivoExcel;
        public static FileOutputStream fileOut;
        public static XSSFSheet hojaExcel;
	
	public static void setCellData(String rutaArchivo, String nombreHoja, String result,  int rowNum, int colNum) throws Exception	{		
		XSSFCell cell;
		XSSFRow row;
		try{
			archivo = new FileInputStream(new File(rutaArchivo));
			archivoExcel = new XSSFWorkbook(archivo);
			hojaExcel = archivoExcel.getSheet(nombreHoja);
			if(hojaExcel.getRow(rowNum)==null) {
				row = hojaExcel.createRow(rowNum);
			}else {
				row = hojaExcel.getRow(rowNum);
			}
			cell = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
				
  			fileOut = new FileOutputStream(rutaArchivo);
  			archivoExcel.write(fileOut);
  			archivoExcel.close();
  			archivo.close();
  			fileOut.flush();
			fileOut.close();

			}catch(Exception e){
				throw (e);
			}
	}
	
	public static String rutaInputExcel(String nombreArchivo) throws IOException {
		String ruta;
		ruta = Utilitario.obtenerValor(nombreArchivo,"InputDatos")+"\\"+Utilitario.obtenerValor(nombreArchivo,"nombreExcelInputDatos");
		return ruta;
	}
	
	public static String rutaOutputExcel(String nombreArchivo) throws IOException {
		String ruta;
		Calendar fecha = Calendar.getInstance();
        int anho = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
		ruta = Utilitario.obtenerValor(nombreArchivo,"LogResultado")+"\\"+Utilitario.obtenerValor(nombreArchivo,"nombreExcelLogResultado")+"_"+anho+mes+dia+hora+minuto+".xlsx";
		return ruta;
	}
	public static String rutaOutputExcelA(String nombreArchivo) throws IOException {
		String ruta;
		Calendar fecha = Calendar.getInstance();
        int anho = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
		ruta = Utilitario.obtenerValor(nombreArchivo,"LogResultadoA")+"\\"+Utilitario.obtenerValor(nombreArchivo,"nombreExcelLogResultadoA")+"_"+anho+mes+dia+hora+minuto+".xlsx";
		return ruta;
	}
	
	public static void limpiarHojaExcel(String rutaArchivo, String nombreHoja){
		try{
			FileInputStream archivo = new FileInputStream(new File(rutaArchivo));
			XSSFWorkbook archivoExcel = new XSSFWorkbook(archivo);
			XSSFSheet hojaExcel = archivoExcel.getSheet(nombreHoja);
			Iterator<Row> filas = hojaExcel.iterator();
			filas.next();
			while(filas.hasNext()){
				Row filaActual = filas.next();
				Iterator<Cell> celdas = filaActual.cellIterator();
				while(celdas.hasNext()){
					Cell celdaActual = celdas.next();
					celdaActual.setCellValue("");
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(rutaArchivo);
  			archivoExcel.write(fileOut);			
			archivoExcel.close();
			archivo.close();
			fileOut.close();
			fileOut.flush();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}

