package utilitario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilitario {
	
	private Utilitario() {}
	
	//Dar formato a fecha
	public static final String convertirDate(Date fecha, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(fecha);
	}
	
	public static final String obtenerValor(String nombreArchivo, String clave) throws IOException {
		String cadena = "";
		File f = new File(nombreArchivo); // Creamos un objeto file
		FileReader fr = new FileReader(f.getAbsolutePath()); 
	    BufferedReader br = new BufferedReader(fr);
	    
	    while((cadena = br.readLine())!=null) {
	    	if (cadena.startsWith(clave)) {
	    		break;
	    	}
	    	cadena = "";
	    }
	    br.close();
	    fr.close();
	    return cadena.substring(clave.length()+1);	//adicionamos 1 para no retornar =
	}
	
	//Captura la pantalla y lo guarda en la ruta designada
	public static void capturarPantalla(WebDriver driver,String nombre,String ruta) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ruta+"\\"+nombre+".png"));
	}
	
	
	public static final Date obtenerFechaModificacionDirectorio(File dirOrigen) {
		Date dateModification = new Date(dirOrigen.lastModified());
		return dateModification;
	}
		
	/*public static void moverLog(String nombreArchivo) throws Exception {
		String rutaOrigen = obtenerValor(nombreArchivo,"LogResultado");
		File dirOrigen = new File(rutaOrigen);
		String rutaDestino = obtenerValor(nombreArchivo,"Hitorico");
		File LogResultExcel = new File(UtilExcel.rutaOutputExcel(nombreArchivo));
		rutaDestino = rutaDestino+"\\"+convertirDate(obtenerFechaModificacionDirectorio(LogResultExcel),obtenerValor(nombreArchivo,"formtatoFH"));
		File dirDestino = new File(rutaDestino);
		copiarDirectorio(dirOrigen,dirDestino);
	}*/
		
	public static void copiarDirectorio(File dirOrigen, File dirDestino) throws Exception{
		try {
			if(dirOrigen.isDirectory()) {
				String[] contenido = dirOrigen.list();
				if(contenido.length>0) {
					if(!dirDestino.exists()) {
						dirDestino.mkdir();
					}
					for(int i = 0; i < contenido.length; i++) {
						copiarDirectorio(new File(dirOrigen, contenido[i]),new File(dirDestino, contenido[i]));		
					}
				}
			} else {
				copiar(dirOrigen, dirDestino);
			}
		}catch (Exception e) {
			throw e;
		}
	}
		
	public static void copiar(File dirOrigen, File dirDestino) throws Exception{
		InputStream in = new FileInputStream(dirOrigen);
		OutputStream out = new FileOutputStream(dirDestino);
			
		byte[] buffer = new byte[1024];
		int len;
		try {
			while((len = in.read(buffer)) >0) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			in.close();
			out.close();
		}
	}
	
	public static void crearCarpeta(String ubicacion)
	{
		boolean success = (new File(ubicacion)).mkdirs();
		if (!success) {
		    System.out.println("Directory creation failed");
		}
	}
	
	public static void crearEstructura(String ubicacion)
	{
		crearCarpeta(ubicacion);
		crearCarpeta(ubicacion+"\\"+"Historico");
		crearCarpeta(ubicacion+"\\"+"InputDatos");
		crearCarpeta(ubicacion+"\\"+"LogResult");
		crearCarpeta(ubicacion+"\\"+"Manual");
		crearCarpeta(ubicacion+"\\"+"Script");
	}
	
	public static String getWebDriver()
	{
		return "webdriver.chrome.driver";
	}
	
	public static String getDriverDir()
	{
		//return "C:\\Users\\gabriel.salvador\\Desktop\\drivers\\chromedriver.exe";
		return "C:\\Users\\gabriel.salvador\\Desktop\\drivers\\chromedriver.exe";
	}
	
	public static String DriverName()
	{
		return "chrome";
	}
	
	public static final Integer ESPERAPRUEBA = 2000;
	public static void tespera()
	{
		
		Thread.currentThread();
		try {
			Thread.sleep(ESPERAPRUEBA);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	public static void TakeScreenshot(WebDriver driver,String nombre,String ruta) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ruta+"\\"+nombre+".png"));
	}
	
	public static String excel()
	{
		return "C:\\Users\\gabriel.salvador\\Desktop\\archivos\\prueba.xlsx";
	}
}
