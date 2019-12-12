/*#########################################################################################
 * Autor: Francisco Gamarra
 * Fecha de creacian: 10/09/2019
 * Descripcian: Automatizacian E-commerce
 * #########################################################################################
 * Marca      Fecha Mod        Descripcian Mod           Responsable
 * #########################################################################################
 * 
 * #########################################################################################*/

import org.junit.Test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilitario.UtilExcel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;
import utilitario.InformixDB;
import utilitario.OracleDB;
import utilitario.UtilRecorder;


/**
 *
 * @author USUARIO
 */

@RunWith(DataProviderRunner.class)
public class TestClass1 {
    
    //Variables para recibir datos desde Main Class
    //==============================================
    public static String rutaLog;
    public static String rutaFuente;
    public static String rutaGral;
    public static boolean grabarVideo;
    
    //Variables para inicializacian de Selenium
    //==========================================
    static WebDriver driver;
    static WebElement myElement;
    
    //Mensaje global
    //===============
    String mensaje = "";
    String outputmsj = "";
    
    //Variables que utilizadas para controlar el acceso al login de la aplicaician
    //============================================================================
    boolean adentro = false;
    boolean existe = false;
    
    static Select combo;
    int GlobalRamo = 0;
    
    boolean responsable = false;
    boolean error = false;
    boolean tradicional = true;
    boolean cierre = false;
    String poliza = "";
    
    //Variables enteras del DataTable
    //================================
    int iFlujo = 0;
    int iDocumento = 0;
    int iMarcaTarjeta = 0;
    int iEstadoCivil = 0;
    int iSexo = 0;
    int iClase = 0;
    int iMarca = 0;
    int iUso = 0;
    int iZonaCirculacion = 0;
    
    //Condiciones de entrada y salida
    //================================
    String[][] input_data = new String[13][5];
    String[][] output_data = new String[13][3];
    
    @BeforeClass
    public static void comenzar() throws Exception{
        
        String ruta = "C:\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",ruta);
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        
        driver = new ChromeDriver(options);
        
        if(grabarVideo == true){
                
                String videoruta = rutaGral;
                UtilRecorder.setUp(videoruta);
                UtilRecorder.comienzaGrabar();
        }
    }
    
    @Test
    @UseDataProvider("dataProviderUSER")
    public void testprincipal(String nroFlujo,String placa,String nombres,String apPaterno,String apMaterno,String estadoCivil,String nacionalidad,String sexo,String fechaNacimiento,String docIdentidad,String tipoDocumento,String marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta,String departamento,String provincia,String distrito,String direccion,String celular,String clase,String marca,String modelo,String version,String nroSerie,String anhoFabricacion,String uso,String zonaCirculacion) throws InterruptedException, AWTException, Exception{
        
        if(ValidaInput(nroFlujo,placa,nombres,apPaterno,apMaterno,estadoCivil,nacionalidad,sexo,fechaNacimiento,docIdentidad,tipoDocumento,marcaTarjeta,nroTarjeta,vencimientoTarjeta,CVVTarjeta,departamento,provincia,distrito,direccion,celular,clase,marca,modelo,version,nroSerie,anhoFabricacion,uso,zonaCirculacion)){
            
            mensaje = "";
            long startTime = System.currentTimeMillis();
            
            try{
                switch(nroFlujo) 
                { 
                    case "1": 
                        Caso1(nroFlujo, placa, nombres, apPaterno, apMaterno, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, departamento, provincia, distrito, direccion, celular);
                        break; 
                    case "2": 
                        Caso1(nroFlujo, placa, nombres, apPaterno, apMaterno, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, departamento, provincia, distrito, direccion, celular);
                        break; 
                    case "3": 
                        Caso1(nroFlujo, placa, nombres, apPaterno, apMaterno, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, departamento, provincia, distrito, direccion, celular);
                        break; 
                    case "4": 
                        Caso5(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular);
                        break;
                    case "5": 
                        Caso5(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular);
                        break;
                    case "6": 
                        Caso5(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular);
                        break;
                    case "7": 
                        Caso5(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular);
                        break;
                    case "8": 
                        Caso8(nroFlujo, placa, nombres, apPaterno, apMaterno, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta);
                        break;
                    case "9": 
                        Caso8(nroFlujo, placa, nombres, apPaterno, apMaterno, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta);
                        break;
                    case "10": 
                        Caso8(nroFlujo, placa, nombres, apPaterno, apMaterno, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta);
                        break;
                    case "11": 
                        Caso11(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular,iClase,iMarca,modelo,version,nroSerie,anhoFabricacion,iUso,iZonaCirculacion);
                        break;
                    case "12": 
                        Caso11(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular,iClase,iMarca,modelo,version,nroSerie,anhoFabricacion,iUso,iZonaCirculacion);
                        break;
                    case "13": 
                        Caso11(nroFlujo, placa, nombres, apPaterno, apMaterno,iEstadoCivil,nacionalidad,iSexo,fechaNacimiento, docIdentidad, tipoDocumento, iMarcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta,departamento,provincia,distrito,direccion,celular,iClase,iMarca,modelo,version,nroSerie,anhoFabricacion,iUso,iZonaCirculacion);
                        break;
                    default: 
                        break;
                }
                
                long TimerTime = (System.currentTimeMillis()-startTime);
                String tiempoEjecucion = ""+TimerTime;
                outputmsj = "Flujo numero "+nroFlujo+" validado exitosamente";
                llenarExcel(nroFlujo, placa, nombres, apPaterno, apMaterno,estadoCivil,nacionalidad,sexo,fechaNacimiento, docIdentidad, tipoDocumento, nroTarjeta, "OK", outputmsj,tiempoEjecucion,poliza);
                
            }catch(Exception ex){
                //System.out.println("Dentro de la excepcian");
                long TimerTime = (System.currentTimeMillis()-startTime);
                ex.printStackTrace();
                File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                Date fecha = new Date();
                SimpleDateFormat df = new SimpleDateFormat("YY_MM_dd_hh_mm_ss");
                String fechas = df.format(fecha);
                String nombreCaptura = "fallo_"+fechas+".png";
                try {
                    FileUtils.copyFile(src, new File(rutaGral+"\\"+nombreCaptura));
                }catch(Exception ex2){}
                Thread.sleep(1000);
                String tiempoEjecucion = ""+TimerTime;
                if(outputmsj.length()==0){
                    if(ex.getClass().getCanonicalName().contains("Timeout")){
                        outputmsj = "Un elemento esta demorando en cargar mas tiempo del previsto (ver la captura '"+nombreCaptura+"')";
                    }else{
                        outputmsj = "Fallo no identificado (ver la captura '"+nombreCaptura+"')";
                    }
                }
                if(cierre==false){
                    outputmsj = "Fallo durante la ejecucion del flujo numero"+nroFlujo+": "+outputmsj;
                    llenarExcel(nroFlujo, placa, nombres, apPaterno, apMaterno,estadoCivil,nacionalidad,sexo,fechaNacimiento, docIdentidad, tipoDocumento, nroTarjeta, "NO OK", outputmsj,tiempoEjecucion,"-");
                }else{
                   outputmsj = "No es posible la validacion del flujo numero"+nroFlujo+": "+outputmsj;
                   llenarExcel(nroFlujo, placa, nombres, apPaterno, apMaterno,estadoCivil,nacionalidad,sexo,fechaNacimiento, docIdentidad, tipoDocumento, nroTarjeta, "NO OK", outputmsj,tiempoEjecucion,"-");
                }
            }
        }else{
            mensaje = "";
            outputmsj = "Inconsistencia de data para el flujo numero"+nroFlujo+": "+outputmsj;
            llenarExcel(nroFlujo, placa, nombres, apPaterno, apMaterno,estadoCivil,nacionalidad,sexo,fechaNacimiento, docIdentidad, tipoDocumento, nroTarjeta, "ERROR", outputmsj,"0","-");
        }
    }
    
    public void Caso1(String nroFlujo,String placa,String nombres,String apPaterno,String apMaterno,String docIdentidad,String tipoDocumento,int marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta, String departamento, String provincia, String distrito, String direccion, String celular) throws InterruptedException, Exception{
    		
    		int i = 1;
    		
    		
            Thread.sleep(1000);
            driver.get("https://10.10.32.93:10042/wps/portal/corporativo/home/cotizador");
            
            Thread.sleep(1000);
            JavascriptExecutor jexe = (JavascriptExecutor) driver;
            jexe.executeScript("window.scrollBy(0,-1000)");
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("iplaca-home")));
            myElement = driver.findElement(By.id("iplaca-home"));
            myElement.sendKeys(placa);
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("btngo")));
            myElement = driver.findElement(By.id("btngo"));
            myElement.click();
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lq-module1']/div/div[2]/div/div")));

            WebElement NoReconocePlaca = driver.findElement(By.xpath("//div[@id='lq-module1']/div/div[2]/div/div"));
            
            System.out.println(NoReconocePlaca.getText());
            System.out.println(NoReconocePlaca.getText().startsWith("�Uhmm!"));
            System.out.println(NoReconocePlaca.getText().startsWith("Dime"));
            
            boolean ReconocePlaca = NoReconocePlaca.getText().startsWith("�Uhmm!");
            
            if(ReconocePlaca) {
            	//new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='SGS-105']")));
            	new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lq-module1']/div[2]/div/form/button/i")));
            	Thread.sleep(1000);
            	
            	System.out.println("Llegue aqui");
            	myElement = driver.findElement(By.xpath("//div[@id='lq-module1']/div[2]/div/form/div/input"));
            	
            	myElement.sendKeys(placa);
            	
            	myElement = driver.findElement(By.xpath("//div[@id='lq-module1']/div[2]/div/form/button/i"));
            	myElement.click();
            }
            
            
            
            if(ReconocePlaca) {
            	i = 2;
            	System.out.println(i);
            } else {
            	i = 1;
            }
            
            //Esperar hasta que salga el mensaje 'Si, esta maravilla es ma'
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a")));
/*            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[2]/div/div"));
            System.out.println(myElement.getText());
            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/div"));
            System.out.println(myElement.getText());
            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[4]/div/form/div[2]/div[1]/div[2]/span"));
            System.out.println(myElement.getText());
*/            
            //Click en el botan 'Si, esta maravilla es ma'
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 3;
            	System.out.println(i);
            } else {
            	i = 2;
            }
            
            //Click al botan 'Si soy yo' una vez que aparezca
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 4;
            	System.out.println(i);
            } else {
            	i = 3;
            }
            
            //Aparece una oferta y digo "Perfecto"
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 5;
            	System.out.println(i);
            } else {
            	i = 4;
            }
            
            //Confirmo DNI
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i"));
            myElement.click();
            
            //Thread.sleep(360000);
            
            if(ReconocePlaca) {
            	i = 6;
            	System.out.println(i);
            } else {
            	i = 5;
            }
            
            //Confirmamos datos
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a"));
            myElement.click();
            
            //===================================== direccion
            
            if(ReconocePlaca) {
            	i = 7;
            	System.out.println(i);
            } else {
            	i = 6;
            }
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));
            
            Thread.sleep(1000);
            
            Select sel_dep = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div/div/select")));
            try{
                sel_dep.selectByVisibleText(departamento.toUpperCase());
            }catch(Exception e){
                outputmsj = "No se encontro el departamento indicado en la lista del chatBot";
                throw new Exception();
            }

            Thread.sleep(1000);
            Select sel_pro = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[2]/div/div/select")));
            try{
                sel_pro.selectByVisibleText(provincia.toUpperCase());
            }catch(Exception e){
                outputmsj = "No se encontro la provincia indicada en la lista del chatBot";
                throw new Exception();
            }
            
            Thread.sleep(1000);
            Select sel_dis = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[3]/div/div/select")));
            try{
                sel_dis.selectByVisibleText(distrito.toUpperCase());
            }catch(Exception e){
                outputmsj = "No se encontro el distrito indicado en la lista del chatBot";
                throw new Exception();
            }

            Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[4]/div/div/input"));
            try{
            	if(myElement.getAttribute("value").isEmpty()) {
                    myElement.sendKeys(direccion.toUpperCase());	
            	}
            }catch(Exception e){
                outputmsj = "El chatBot no permite ingresar la direccion especificada";
                throw new Exception();
            }

            Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[5]/div/input"));
            try{
                myElement.sendKeys(celular);
            }catch(Exception e){
                outputmsj = "El chatBot no permite ingresar el numero de celular especificado";
                throw new Exception();
            }
            
            //===================================================
            
            //Confirmamos la direccian
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 8;
            	System.out.println(i);
            } else {
            	i = 7;
            }
            
            //Esperar disponibilidad y luego escribir el correo en el campo de texto
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/input")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/input"));
            myElement.clear();
            myElement.sendKeys("prueba.ecommerce.positiva@gmail.com");
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i")));        
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 9;
            	System.out.println(i);
            } else {
            	i = 8;
            }
            
            //Confirmar la fecha
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/button/i")));        
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/button/i"));
            myElement.click();
            
            System.out.println("========================== CONFIRMACION ===============================");
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("btnComprarOnline")));        
            
            if(ReconocePlaca) {
            	i = 10;
            	System.out.println(i);
            } else {
            	i = 9;
            }
            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[1]/span"));
            System.out.println("Tarifa: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[1]/div[2]/span"));
            System.out.println("Marca: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[2]/div[2]/span"));
            System.out.println("Modelo: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[3]/div[2]/span"));
            System.out.println("Placa: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[4]/div[2]/span"));
            System.out.println("Clase: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[5]/div[2]/span"));
            System.out.println("Uso: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[6]/div[2]/span"));
            System.out.println("Zona: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[7]/div[2]/span"));
            System.out.println("DNI: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
            System.out.println("Nombre: "+myElement.getText());
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('iterms').setAttribute('class', 'form-control required on')");
            js.executeScript("document.getElementById('iterms').setAttribute('checked', 'true')");
            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
            js.executeScript("arguments[0].setAttribute('class', 'lq-cotizador__resumen__chk form-group lq-error')", myElement);
            
            FlujoTarjeta(nroFlujo, marcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, placa, docIdentidad);
    }
    
    public void Caso8(String nroFlujo,String placa,String nombres,String apPaterno,String apMaterno,String docIdentidad,String tipoDocumento,int marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta) throws InterruptedException, Exception{
            
    		int i = 1;
    		
            Thread.sleep(1000);
            driver.get("https://10.10.32.93:10042/wps/portal/corporativo/home/cotizador");
            
            Thread.sleep(1000);
            JavascriptExecutor jexe = (JavascriptExecutor) driver;
            jexe.executeScript("window.scrollBy(0,-1000)");
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("iplaca-home")));
            myElement = driver.findElement(By.id("iplaca-home"));
            myElement.sendKeys(placa);
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("btngo")));
            myElement = driver.findElement(By.id("btngo"));
            myElement.click();
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lq-module1']/div/div[2]/div/div")));

            WebElement NoReconocePlaca = driver.findElement(By.xpath("//div[@id='lq-module1']/div/div[2]/div/div"));
            
            System.out.println(NoReconocePlaca.getText());
            System.out.println(NoReconocePlaca.getText().startsWith("�Uhmm!"));
            System.out.println(NoReconocePlaca.getText().startsWith("Dime"));
            
            boolean ReconocePlaca = NoReconocePlaca.getText().startsWith("�Uhmm!");
            
            if(ReconocePlaca) {
            	//new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='SGS-105']")));
            	new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lq-module1']/div[2]/div/form/button/i")));
            	Thread.sleep(1000);
            	
            	System.out.println("Llegue aqui");
            	myElement = driver.findElement(By.xpath("//div[@id='lq-module1']/div[2]/div/form/div/input"));
            	
            	myElement.sendKeys(placa);
            	
            	myElement = driver.findElement(By.xpath("//div[@id='lq-module1']/div[2]/div/form/button/i"));
            	myElement.click();
            }
            
            if(ReconocePlaca) {
            	i = 2;
            	System.out.println(i);
            } else {
            	i = 1;
            }
            
            //Esperar hasta que salga el mensaje 'Si, esta maravilla es ma'
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module"+ i +"']/div[2]/div[1]/a")));
/*            
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[1]/div[2]/div/div"));
            System.out.println(myElement.getText());
            
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[1]/div[3]/div/div"));
            System.out.println(myElement.getText());
            
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[1]/div[4]/div/form/div[2]/div[1]/div[2]/span"));
            System.out.println(myElement.getText());
*/            
            //Click en el botan 'Si, esta maravilla es ma'
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 3;
            	System.out.println(i);
            } else {
            	i = 2;
            }
            
            //Click al botan 'No soy yo' una vez que aparezca
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[2]/a/span")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[2]/a/span"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 4;
            	System.out.println(i);
            } else {
            	i = 3;
            }
            
            //Introduce DNI
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div[2]/div/input")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div[2]/div/input"));
            myElement.sendKeys(docIdentidad);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus(); arguments[0].blur(); return true", myElement);
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[2]/div/input")));
            
            String nom = "";
            int segundos = 60;
            while(nom.length()==0||segundos<=0){
                nom = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='iname']\").val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }
            
            if(nom.contains(nombres.toUpperCase())){
                System.out.println("Nombre: "+nom);
            }else{
                outputmsj = "El nombre devuelto por el chatBot no es el correcto";
                throw new Exception();
            }
            
            String apa = "";
            segundos = 60;
            while(apa.length()==0||segundos<=0){
                apa = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='ipaterno']\").val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }
            
            if(apa.contains(apPaterno.toUpperCase())){
                System.out.println("Apellido paterno: "+apa);
            }else{
                outputmsj = "El apellido paterno devuelto por el chatBot no es el correcto";
                throw new Exception();
            }
            
            String ama = "";
            segundos = 60;
            while(ama.length()==0||segundos<=0){
                ama = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='imaterno']\").val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }
            
            if(ama.contains(apMaterno.toUpperCase())){
                System.out.println("Apellido materno: "+ama);
            }else{
                outputmsj = "El apellido materno devuelto por el chatBot no es el correcto";
                throw new Exception();
            }
            
            Thread.sleep(1000);
            //Confirmamos los datos
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 5;
            	System.out.println(i);
            } else {
            	i = 4;
            }
            
            //Esperar a que aparezca la direccian
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));
            
            Select select;
            
            select = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div/div/select")));
            myElement = select.getFirstSelectedOption();
            
            String depa = myElement.getText();
            if(depa.length()>0){
                System.out.println("Departamento: "+depa);
            }else{
                outputmsj = "El chatBot no esta devolviendo el departamento";
                throw new Exception();
            }
            
            select = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[2]/div/div/select")));
            myElement = select.getFirstSelectedOption();
            
            String prov = myElement.getText();
            if(prov.length()>0){
                System.out.println("Provincia: "+prov);
            }else{
                outputmsj = "El chatBot no esta devolviendo la provincia";
                throw new Exception();
            }
            
            select = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[3]/div/div/select")));
            myElement = select.getFirstSelectedOption();
            
            String dist = myElement.getText();
            if(dist.length()>0){
                System.out.println("Distrito: "+dist);
            }else{
                outputmsj = "El chatBot no esta devolviendo el distrito";
                throw new Exception();
            }
            
            String dic = "";
            segundos = 60;
            while(dic.length()==0||segundos<=0){
                dic = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='idirection']\").val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }
            
            if(dic.length()>0){
                System.out.println("Direccion: "+dic);
            }else{
                outputmsj = "El chatBot no esta devolviendo la direccion";
                throw new Exception();
            }
            
            String cel = "";
            segundos = 60;
            while(cel.length()==0||segundos<=0){
                cel = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='icelular']\").val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }
            
            if(cel.length()>0){
                System.out.println("Celular: "+cel);
            }else{
                outputmsj = "El chatBot no esta devolviendo un celular";
                throw new Exception();
            }
            
            //Confirmamos la direccian
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 6;
            	System.out.println(i);
            } else {
            	i = 5;
            }
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module5\"]/div[2]/div/a")));
            
            //Leer tarifa
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/div/div[1]/span"));
            String tari = myElement.getText();
            if(tari.length()>0){
                System.out.println("Tarifa: "+tari);
            }else{
                outputmsj = "El chatBot no esta devolviendo una tarifa";
                throw new Exception();
            }
            
            //Click para confirmar la tarifa
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 7;
            	System.out.println(i);
            } else {
            	i = 6;
            }
            
            //Esperar disponibilidad y luego escribir el correo en el campo de texto
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/input")));
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/input"));
            myElement.clear();
            myElement.sendKeys("prueba.ecommerce.positiva@gmail.com");
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module6\"]/div[2]/div/form/button/i")));        
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i"));
            myElement.click();
            
            if(ReconocePlaca) {
            	i = 8;
            	System.out.println(i);
            } else {
            	i = 7;
            }
            
            //Confirmar la fecha
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/button/i")));        
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/button/i"));
            myElement.click();
            
            System.out.println("========================== CONFIRMACIÓN ===============================");
            
            if(ReconocePlaca) {
            	i = 9;
            	System.out.println(i);
            } else {
            	i = 8;
            }
            
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("btnComprarOnline")));        
            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[1]/span"));
            System.out.println("Tarifa: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[1]/div[2]/span"));
            System.out.println("Marca: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[2]/div[2]/span"));
            System.out.println("Modelo: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[3]/div[2]/span"));
            System.out.println("Placa: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[4]/div[2]/span"));
            System.out.println("Clase: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[5]/div[2]/span"));
            System.out.println("Uso: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[6]/div[2]/span"));
            System.out.println("Zona: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[7]/div[2]/span"));
            System.out.println("DNI: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
            System.out.println("Nombre: "+myElement.getText());
            
            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("document.getElementById('iterms').setAttribute('class', 'form-control required on')");
            jss.executeScript("document.getElementById('iterms').setAttribute('checked', 'true')");

            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
            jss.executeScript("arguments[0].setAttribute('class', 'lq-cotizador__resumen__chk form-group lq-error')", myElement);

            FlujoTarjeta(nroFlujo, marcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, placa, docIdentidad);
    }
    
    public void Caso5(String nroFlujo,String placa,String nombres,String apPaterno,String apMaterno,int estadoCivil,String nacionalidad,int sexo,String fechaNacimiento,String docIdentidad,String tipoDocumento,int marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta,String departamento,String provincia,String distrito,String direccion,String celular) throws InterruptedException, Exception{
        
    	int i = 1;
    	
        Thread.sleep(1000);
        driver.get("https://10.10.32.93:10042/wps/portal/corporativo/home/cotizador");
        
        Thread.sleep(1000);
        JavascriptExecutor jexe = (JavascriptExecutor) driver;
        jexe.executeScript("window.scrollBy(0,-1000)");

        new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("iplaca-home")));
        myElement = driver.findElement(By.id("iplaca-home"));
        myElement.sendKeys(placa);

        new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("btngo")));
        myElement = driver.findElement(By.id("btngo"));
        myElement.click();
        
        new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lq-module1']/div/div[2]/div/div")));

        WebElement NoReconocePlaca = driver.findElement(By.xpath("//div[@id='lq-module1']/div/div[2]/div/div"));
        
        System.out.println(NoReconocePlaca.getText());
        System.out.println(NoReconocePlaca.getText().startsWith("�Uhmm!"));
        System.out.println(NoReconocePlaca.getText().startsWith("Dime"));
        
        boolean ReconocePlaca = NoReconocePlaca.getText().startsWith("�Uhmm!");
        
        if(ReconocePlaca) {
        	//new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='SGS-105']")));
        	new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lq-module1']/div[2]/div/form/button/i")));
        	Thread.sleep(1000);
        	
        	System.out.println("Llegue aqui");
        	myElement = driver.findElement(By.xpath("//div[@id='lq-module1']/div[2]/div/form/div/input"));
        	
        	myElement.sendKeys(placa);
        	
        	myElement = driver.findElement(By.xpath("//div[@id='lq-module1']/div[2]/div/form/button/i"));
        	myElement.click();
        }
        
        if(ReconocePlaca) {
        	i = 2;
        	System.out.println(i);
        } else {
        	i = 1;
        }

        //Esperar hasta que salga el mensaje 'Esta, esta maravilla es mia'
        new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a")));
/*
        myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[1]/div[2]/div/div"));
        System.out.println(myElement.getText());

        myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[1]/div[3]/div/div"));
        System.out.println(myElement.getText());

        myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[1]/div[4]/div/form/div[2]/div[1]/div[2]/span"));
        System.out.println(myElement.getText());
*/
        //Click en el botan 'Si, esta maravilla es mia'
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[1]/a"));
        myElement.click();
        
        if(ReconocePlaca) {
        	i = 3;
        	System.out.println(i);
        } else {
        	i = 2;
        }

        //Click al botan 'No soy yo' una vez que aparezca
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[2]/a/span")));
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div[2]/a/span"));
        myElement.click();
        
        if(ReconocePlaca) {
        	i = 4;
        	System.out.println(i);
        } else {
        	i = 3;
        }

        //Introduce DNI
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div[2]/div/input")));
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div[2]/div/input"));
        myElement.sendKeys(docIdentidad);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].focus(); arguments[0].blur(); return true", myElement);
        
        Thread.sleep(1000);
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));

        System.out.println("Llegamos hasta este punto");

        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[2]/div/input")));
/*
        String nom = "";
        int segundos = 60;
        while(nom.length()==0&&segundos>=0){
            nom = ((JavascriptExecutor)driver).executeScript("return $('input[name='iname']').val();").toString();
            Thread.sleep(1000);
            segundos = segundos - 1;
        }
*/
        Thread.sleep(2000);
        
        myElement = driver.findElement(By.xpath("//input[@name='iname']"));
        if(myElement.getAttribute("value").isEmpty()){

            //En el caso que el DNI insertado no haya sido reconocido y no figure el nombre
            //============================================================================================
            Thread.sleep(1000);

            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[2]/div/input"));
            myElement.sendKeys(nombres.toUpperCase());
            Thread.sleep(1000);

            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[3]/div/input"));
            myElement.sendKeys(apPaterno.toUpperCase());
            Thread.sleep(1000);

            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[4]/div/input"));
            myElement.sendKeys(apMaterno.toUpperCase());
            
            Select sel_sex = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[11]/div[1]/div/div/select")));
            sel_sex.selectByIndex(sexo);

            Thread.sleep(1000);
            
            myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[11]/div[2]/div/div/label/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",myElement);
            myElement.clear();
            myElement.sendKeys(fechaNacimiento);

        }else{

            //En el caso que el DNI insertado haya sido reconocido y figuren valores en la tabla de datos
            //============================================================================================
        	Thread.sleep(1000);
        	
        	if(myElement.getAttribute("value").equals(nombres.toUpperCase())) {
        		System.out.println("Nombre: "+ myElement.getAttribute("value"));
        	}else{
                outputmsj = "El nombre devuelto por el chatBot no es el correcto";
                throw new Exception();
            }
        	
        	myElement = driver.findElement(By.xpath("//input[@name='ipaterno']"));
        	
        	if(myElement.getAttribute("value").equals(apPaterno.toUpperCase())) {
        		System.out.println("Apellido Paterno: "+ myElement.getAttribute("value"));
        	}else{
                outputmsj = "El apellido paterno devuelto por el chatBot no es el correcto";
                throw new Exception();
            }
        	
        	myElement = driver.findElement(By.xpath("//input[@name='imaterno']"));
        	
        	if(myElement.getAttribute("value").equals(apMaterno.toUpperCase())) {
        		System.out.println("Apellido Materno: "+ myElement.getAttribute("value"));
        	}else{
                outputmsj = "El apellido materno devuelto por el chatBot no es el correcto";
                throw new Exception();
            }
        		
        }
/*            if(nom.contains(nombres.toUpperCase())){
                System.out.println("Nombre: "+nom);
            }else{
                outputmsj = "El nombre devuelto por el chatBot no es el correcto";
                throw new Exception();
            }

            String apa = "";
            segundos = 60;
            while(apa.length()==0||segundos<=0){
                apa = ((JavascriptExecutor) driver).executeScript("return $('input[name='ipaterno']').val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }

            if(apa.contains(apPaterno.toUpperCase())){
                System.out.println("Apellido paterno: "+apa);
            }else{
                outputmsj = "El apellido paterno devuelto por el chatBot no es el correcto";
                throw new Exception();
            }

            String ama = "";
            segundos = 60;
            while(ama.length()==0||segundos<=0){
                ama = ((JavascriptExecutor) driver).executeScript("return $('input[name='imaterno']').val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }

            if(ama.contains(apMaterno.toUpperCase())){
                System.out.println("Apellido materno: "+ama);
            }else{
                outputmsj = "El apellido materno devuelto por el chatBot no es el correcto";
                throw new Exception();
            }     
        }
*/
        Thread.sleep(1000);

        /* SE COMENTA EL REGISTRO DE SEXO, ESTADO CIVIL Y FECHA DE NACIMIENTO PORQUE LA WEB NO ESTA VALIDANDO ESTOS PUNTOS
        //Selecciona el sexo
        Select sel_sex = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[11]/div[1]/div/div/select")));
        sel_sex.selectByIndex(sexo);

        Thread.sleep(1000);

        //selecciona el estado civil
        Select sel_est = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[5]/div/div/select")));
        sel_est.selectByIndex(estadoCivil);

        Thread.sleep(1000);

        //Se rellena la fecha
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[11]/div[2]/div/div/label/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",myElement);
        myElement.clear();
        myElement.sendKeys(fechaNacimiento);
        */
        
        Thread.sleep(1000);
        //Confirmamos los datos
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
        myElement.click();
        
        if(ReconocePlaca) {
        	i = 5;
        	System.out.println(i);
        } else {
        	i = 4;
        }

        new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));

      
        Select sel_dep = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[1]/div/div/select")));
        try{
            sel_dep.selectByVisibleText(departamento.toUpperCase());
        }catch(Exception e){
            outputmsj = "No se encontro el departamento indicado en la lista del chatBot";
            throw new Exception();
        }

        Thread.sleep(2000);
        Select sel_pro = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[2]/div/div/select")));
        try{
            sel_pro.selectByVisibleText(provincia.toUpperCase());
        }catch(Exception e){
            outputmsj = "No se encontro la provincia indicada en la lista del chatBot";
            throw new Exception();
        }

        //System.out.println(distrito.toUpperCase());
        //Thread.sleep(360000);

        Thread.sleep(1000);
        Select sel_dis = new Select(driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[3]/div/div/select")));
        try{
            sel_dis.selectByVisibleText(distrito.toUpperCase());
        }catch(Exception e){
            outputmsj = "No se encontro el distrito indicado en la lista del chatBot";
            throw new Exception();
        }

        Thread.sleep(1000);
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[4]/div/div/input"));
        try{
        	if(myElement.getAttribute("value").isEmpty()) {
                myElement.sendKeys(direccion.toUpperCase());	
        	}
        }catch(Exception e){
            outputmsj = "El chatBot no permite ingresar la direccian especificada";
            throw new Exception();
        }

        Thread.sleep(1000);
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/form/div/div[5]/div/input"));
        try{
            myElement.sendKeys(celular);
        }catch(Exception e){
            outputmsj = "El chatBot no permite ingresar el número de celular especificado";
            throw new Exception();
        }

        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
        myElement.click();
        
        if(ReconocePlaca) {
        	i = 6;
        	System.out.println(i);
        } else {
        	i = 5;
        }

        new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a")));

        //Leer tarifa
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[1]/div[3]/div/div/div[1]/span"));
        String tari = myElement.getText();
        if(tari.length()>0){
            System.out.println("Tarifa: "+tari);
        }else{
            outputmsj = "El chatBot no esta devolviendo una tarifa";
            throw new Exception();
        }

        //Click para confirmar la tarifa
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/a"));
        myElement.click();
        
        if(ReconocePlaca) {
        	i = 7;
        	System.out.println(i);
        } else {
        	i = 6;
        }

        //Esperar disponibilidad y luego escribir el correo en el campo de texto
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/input")));
        myElement = driver.findElement(By.xpath("//input[@name='iemail']"));
        if(myElement.getAttribute("value").isEmpty()) {
            myElement.sendKeys("prueba.positiva@gmail.com");
        }
        
        
        //myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/input"));
        //myElement.sendKeys("prueba.positiva@gmail.com");

        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i")));        
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/button/i"));
        myElement.click();
        
        if(ReconocePlaca) {
        	i = 8;
        	System.out.println(i);
        } else {
        	i = 7;
        }

        //Confirmar la fecha
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/button/i")));        
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div/button/i"));
        myElement.click();

        System.out.println("========================== CONFIRMACION ===============================");
        
        if(ReconocePlaca) {
        	i = 9;
        	System.out.println(i);
        } else {
        	i = 8;
        }
        
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("btnComprarOnline")));        

        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[1]/span"));
        System.out.println("Tarifa: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[1]/div[2]/span"));
        System.out.println("Marca: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[2]/div[2]/span"));
        System.out.println("Modelo: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[3]/div[2]/span"));
        System.out.println("Placa: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[4]/div[2]/span"));
        System.out.println("Clase: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[5]/div[2]/span"));
        System.out.println("Uso: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[6]/div[2]/span"));
        System.out.println("Zona: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[7]/div[2]/span"));
        System.out.println("DNI: "+myElement.getText());
        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
        System.out.println("Nombre: "+myElement.getText());

        JavascriptExecutor jss = (JavascriptExecutor) driver;
        jss.executeScript("document.getElementById('iterms').setAttribute('class', 'form-control required on')");
        jss.executeScript("document.getElementById('iterms').setAttribute('checked', 'true')");

        myElement = driver.findElement(By.xpath("//*[@id='lq-module"+ i +"']/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
        jss.executeScript("arguments[0].setAttribute('class', 'lq-cotizador__resumen__chk form-group lq-error')", myElement);

        FlujoTarjeta(nroFlujo, marcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, placa, docIdentidad);
    
    }
    
    public void Caso11(String nroFlujo,String placa,String nombres,String apPaterno,String apMaterno,int estadoCivil,String nacionalidad,int sexo,String fechaNacimiento,String docIdentidad,String tipoDocumento,int marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta,String departamento,String provincia,String distrito,String direccion,String celular, int clase,int marca,String modelo,String version,String nroSerie,String anhoFabricacion,int uso,int zonaCirculacion) throws InterruptedException, Exception{
            
    		int i = 1;
    		
            Thread.sleep(1000);
            driver.get("https://10.10.32.93:10042/wps/portal/corporativo/home/cotizador");
            
            Thread.sleep(1000);
            JavascriptExecutor jexe = (JavascriptExecutor) driver;
            jexe.executeScript("window.scrollBy(0,-1000)");
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("iplaca-home")));
            myElement = driver.findElement(By.id("iplaca-home"));
            myElement.sendKeys(placa);
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.id("btngo")));
            myElement = driver.findElement(By.id("btngo"));
            myElement.click();
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module"+ i +"']/div[2]/div/form/div/input")));
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[2]/div/form/div/input"));
            myElement.sendKeys(placa);
            
            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module"+ i +"']/div[2]/div/form/button")));
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module"+ i +"']/div[2]/div/form/button"));
            myElement.click();
            
            //Thread.sleep(5000);
            
            new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"lq-module2\"]/div[2]/div/a")));
            
            Select sel_clase = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[1]/div[3]/div/form/div/div[2]/div/div/select")));
            sel_clase.selectByIndex(clase);
            
            Thread.sleep(3000);
            Select sel_marca = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[1]/div[3]/div/form/div/div[3]/div/div/select")));
            sel_marca.selectByIndex(marca);
            
            Thread.sleep(1000);
            Select sel_modelo = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[1]/div[3]/div/form/div/div[4]/div/div/select")));
            sel_modelo.selectByIndex(1);
            
            Thread.sleep(1000);
            Select sel_version = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[1]/div[3]/div/form/div/div[5]/div/div/select")));
            sel_version.selectByIndex(1);
            
            Thread.sleep(1000);
            Select sel_uso = new Select(driver.findElement(By.xpath("//*[@id=\"iuso\"]")));
            sel_uso.selectByIndex(uso);
            
            Thread.sleep(1000);
            Select sel_zona = new Select(driver.findElement(By.id("zonacirculacionf")));
            sel_zona.selectByIndex(zonaCirculacion);
            
            Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[1]/div[3]/div/form/div/div[6]/div/input"));
            myElement.sendKeys(nroSerie);
            
            Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[1]/div[3]/div/form/div/div[7]/div/input"));
            myElement.sendKeys(anhoFabricacion);
            
             Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module2\"]/div[2]/div/a"));
            myElement.click();
            
            //===================================================================================================================
            
            //Introduce DNI
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[1]/div[2]/div/input")));
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[1]/div[2]/div/input"));
            myElement.sendKeys(docIdentidad);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus(); arguments[0].blur(); return true", myElement);

            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module3\"]/div[2]/div/a")));

            System.out.println("Llegamos hasta este punto");

            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[2]/div/input")));

            String nom = "";
            int segundos = 60;
            while(nom.length()==0&&segundos>=0){
                nom = ((JavascriptExecutor)driver).executeScript("return $(\"input[name='iname']\").val();").toString();
                Thread.sleep(1000);
                segundos = segundos - 1;
            }

            if(nom.length()==0){

                //En el caso que el DNI insertado no haya sido reconocido y no figure el nombre
                //============================================================================================
                Thread.sleep(1000);

                myElement = driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[2]/div/input"));
                myElement.sendKeys(nombres.toUpperCase());
                Thread.sleep(1000);

                myElement = driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[3]/div/input"));
                myElement.sendKeys(apPaterno.toUpperCase());
                Thread.sleep(1000);

                myElement = driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[4]/div/input"));
                myElement.sendKeys(apMaterno.toUpperCase());

            }else{

                //En el caso que el DNI insertado haya sido reconocido y figuren valores en la tabla de datos
                //============================================================================================

                if(nom.contains(nombres.toUpperCase())){
                    System.out.println("Nombre: "+nom);
                }else{
                    outputmsj = "El nombre devuelto por el chatBot no es el correcto";
                    throw new Exception();
                }

                String apa = "";
                segundos = 60;
                while(apa.length()==0||segundos<=0){
                    apa = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='ipaterno']\").val();").toString();
                    Thread.sleep(1000);
                    segundos = segundos - 1;
                }

                if(apa.contains(apPaterno.toUpperCase())){
                    System.out.println("Apellido paterno: "+apa);
                }else{
                    outputmsj = "El apellido paterno devuelto por el chatBot no es el correcto";
                    throw new Exception();
                }

                String ama = "";
                segundos = 60;
                while(ama.length()==0||segundos<=0){
                    ama = ((JavascriptExecutor) driver).executeScript("return $(\"input[name='imaterno']\").val();").toString();
                    Thread.sleep(1000);
                    segundos = segundos - 1;
                }

                if(ama.contains(apMaterno.toUpperCase())){
                    System.out.println("Apellido materno: "+ama);
                }else{
                    outputmsj = "El apellido materno devuelto por el chatBot no es el correcto";
                    throw new Exception();
                }     
            }

            Thread.sleep(1000);
            
            /* SE COMENTA EL REGISTRO DE SEXO, ESTADO CIVIL Y FECHA DE NACIMIENTO PORQUE LA WEB NO ESTA VALIDANDO ESTOS PUNTOS - LBENITES
            //Selecciona el sexo
            Select sel_sex = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[11]/div[1]/div/div/select")));
            sel_sex.selectByIndex(sexo);

            Thread.sleep(1000);

            //selecciona el estado civil
            Select sel_est = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[5]/div/div/select")));
            sel_est.selectByIndex(estadoCivil);

            Thread.sleep(1000);

            //Se rellena la fecha
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[1]/div[3]/div/form/div/div[11]/div[2]/div/div/label/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",myElement);
            myElement.clear();
            myElement.sendKeys(fechaNacimiento);
 			*/
            
            Thread.sleep(1000);
            //Confirmamos los datos
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module3\"]/div[2]/div/a"));
            myElement.click();

            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module4\"]/div[2]/div/a")));

            Thread.sleep(1000);
            Select sel_dep = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module4\"]/div[1]/div[3]/div/form/div/div[1]/div/div/select")));
            try{
                sel_dep.selectByVisibleText(departamento.toUpperCase());
            }catch(Exception e){
                outputmsj = "No se encontro el departamento indicado en la lista del chatBot";
                throw new Exception();
            }

            Thread.sleep(1000);
            Select sel_pro = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module4\"]/div[1]/div[3]/div/form/div/div[2]/div/div/select")));
            try{
                sel_pro.selectByVisibleText(provincia.toUpperCase());
            }catch(Exception e){
                outputmsj = "No se encontro la provincia indicada en la lista del chatBot";
                throw new Exception();
            }

            //System.out.println(distrito.toUpperCase());
            //Thread.sleep(360000);

            Thread.sleep(1000);
            Select sel_dis = new Select(driver.findElement(By.xpath("//*[@id=\"lq-module4\"]/div[1]/div[3]/div/form/div/div[3]/div/div/select")));
            try{
                sel_dis.selectByVisibleText(distrito.toUpperCase());
            }catch(Exception e){
                outputmsj = "No se encontro el distrito indicado en la lista del chatBot";
                throw new Exception();
            }

            Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module4\"]/div[1]/div[3]/div/form/div/div[4]/div/div/input"));
            try{
            	if(myElement.getAttribute("value").isEmpty()) {
                    myElement.sendKeys(direccion.toUpperCase());	
            	}
            }catch(Exception e){
                outputmsj = "El chatBot no permite ingresar la direccion especificada";
                throw new Exception();
            }

            Thread.sleep(1000);
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module4\"]/div[1]/div[3]/div/form/div/div[5]/div/input"));
            try{
                myElement.sendKeys(celular);
            }catch(Exception e){
                outputmsj = "El chatBot no permite ingresar el numero de celular especificado";
                throw new Exception();
            }

            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module4\"]/div[2]/div/a"));
            myElement.click();

            new WebDriverWait(driver,90).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module5\"]/div[2]/div/a")));

            //Leer tarifa
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module5\"]/div[1]/div[3]/div/div/div[1]/span"));
            String tari = myElement.getText();
            if(tari.length()>0){
                System.out.println("Tarifa: "+tari);
            }else{
                outputmsj = "El chatBot no esta devolviendo una tarifa";
                throw new Exception();
            }

            //Click para confirmar la tarifa
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module5\"]/div[2]/div/a"));
            myElement.click();

            //Esperar disponibilidad y luego escribir el correo en el campo de texto
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module6\"]/div[2]/div/form/div/input")));
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module6\"]/div[2]/div/form/div/input"));
            //myElement.sendKeys("prueba.ecommerce.positiva@gmail.com");

            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module6\"]/div[2]/div/form/button/i")));        
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module6\"]/div[2]/div/form/button/i"));
            myElement.click();

            //Confirmar la fecha
            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lq-module7\"]/div[2]/div/form/div/button/i")));        
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module7\"]/div[2]/div/form/div/button/i"));
            myElement.click();

            System.out.println("========================== CONFIRMACIÓN ===============================");

            new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("btnComprarOnline")));        

            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[1]/span"));
            System.out.println("Tarifa: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[1]/div[2]/span"));
            System.out.println("Marca: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[2]/div[2]/span"));
            System.out.println("Modelo: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[3]/div[2]/span"));
            System.out.println("Placa: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[4]/div[2]/span"));
            System.out.println("Clase: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[5]/div[2]/span"));
            System.out.println("Uso: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[6]/div[2]/span"));
            System.out.println("Zona: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[7]/div[2]/span"));
            System.out.println("DNI: "+myElement.getText());
            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
            System.out.println("Nombre: "+myElement.getText());

            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("document.getElementById('iterms').setAttribute('class', 'form-control required on')");
            jss.executeScript("document.getElementById('iterms').setAttribute('checked', 'true')");

            myElement = driver.findElement(By.xpath("//*[@id=\"lq-module8\"]/div[2]/div/form/div[1]/div[2]/div[8]/div[2]/span"));
            jss.executeScript("arguments[0].setAttribute('class', 'lq-cotizador__resumen__chk form-group lq-error')", myElement);

            FlujoTarjeta(nroFlujo, marcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta, placa, docIdentidad);
    }
    
    public void FlujoTarjeta(String nroFlujo,int marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta, String placa, String docIdentidad) throws InterruptedException, Exception{
        
        Thread.sleep(1000);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('btnComprarOnline').click();");
        
        int[] intTarjeta = new int[3];
        
        //Apunta el foco hacia el modal
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("culqi_checkout_frame"))); 
        myElement = driver.findElement(By.id("culqi_checkout_frame"));
        driver.switchTo().frame(myElement);
        
        Thread.sleep(500);
        
        //Introduce el número de la tarjeta
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("cardNumber"))); 
        myElement = driver.findElement(By.name("cardNumber"));
        myElement.sendKeys(nroTarjeta);
        
        //Introduce la fecha de expiracian
        Thread.sleep(500);
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("cardExp")));
        myElement = driver.findElement(By.name("cardExp"));
        myElement.sendKeys(vencimientoTarjeta);
        
        //Valida el número de la tarjeta
        myElement = driver.findElement(By.xpath("//*[@id=\"clq\"]/section/section/section[1]/div/div/div[1]/form/div[1]/div/div"));
        String val1 = myElement.getAttribute("class");
        System.out.println("nroTarjeta: "+val1);
        if(val1.equals("form-group invalid"))
        {
            intTarjeta[0] = 2;
        }
        else if(val1.equals("form-group valid"))
        {
            intTarjeta[0] = 1;
        }
        else
        {
            intTarjeta[0] = 0;
        }
        
        //Introduce el CVV
        Thread.sleep(500);
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("cardCVV")));
        myElement = driver.findElement(By.name("cardCVV"));
        myElement.sendKeys(CVVTarjeta);
        
        //Valida la fecha de expiracian
        myElement = driver.findElement(By.xpath("//*[@id=\"clq\"]/section/section/section[1]/div/div/div[1]/form/div[2]/div[1]/div"));
        String val2 = myElement.getAttribute("class");
        System.out.println("carExp: "+val2);
        if(val2.equals("form-group invalid"))
        {
            intTarjeta[1] = 2;
        }
        else if(val2.equals("form-group valid"))
        {
            intTarjeta[1] = 1;
        }
        else
        {
            intTarjeta[1] = 0;
        }
        
        //Introduce el email
        Thread.sleep(500);
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("cardEmail")));
        myElement = driver.findElement(By.name("cardEmail"));
        myElement.sendKeys("prueba.ecommerce.positiva@gmail.com");
        
        //Valida el CVV
        myElement = driver.findElement(By.xpath("//*[@id=\"clq\"]/section/section/section[1]/div/div/div[1]/form/div[2]/div[2]/div"));
        String val3 = myElement.getAttribute("class");
        System.out.println("cvv: "+val3);
        if(val3.equals("form-group invalid"))
        {
            intTarjeta[2] = 2;
        }
        else if(val3.equals("form-group valid"))
        {
            intTarjeta[2] = 1;
        }
        else
        {
            intTarjeta[2] = 0;
        }
        
        int validaTarjeta = intTarjeta[0]*intTarjeta[1]*intTarjeta[2];
        boolean casoExcepcion = false;
        boolean casoInvalida = false;
        
        String condicion = input_data[iFlujo][4];
        System.out.println("iFlujo: "+iFlujo);
        //System.out.println("Antes de la validacion");
        //Thread.sleep(360000);
        if(condicion.contains("1"))
        {
            System.out.println("La tarjeta debe ser valida");
            if(validaTarjeta==1)
            {
                System.out.println("La tarjeta es valida - Es correcto");
            }
            else if(validaTarjeta>1)
            {
                System.out.println("La tarjeta es invalida - Es incorrecto");
                for(int i=0;i<3;i++){
                    if((i==0)&(intTarjeta[i]>1))
                    {
                        System.out.println("Es incorrecto el numero de la tarjeta");
                        outputmsj = "Resultado inesperado: El numero de la tarjeta es evaluado por el chatBot como incorrecto";
                        throw new Exception();
                    }
                    else if((i==1)&(intTarjeta[i]>1))
                    {
                        System.out.println("Es incorrecta la fecha de expiracion");
                        outputmsj = "Resultado inesperado: La fecha de expiracion de la tarjeta es evaluada por el chatBot como incorrecta";
                        throw new Exception();
                    }
                    else if((i==2)&(intTarjeta[i]>1))
                    {
                        System.out.println("Es incorrecto el CVV");
                        outputmsj = "Resultado inesperado: El CVV de la tarjeta es evaluado por el chatBot como incorrecto";
                        throw new Exception();
                    }
                }
            }
            else if(validaTarjeta==0)
            {
                System.out.println("La tarjeta es indeterminada - Es incorrecto");
                for(int i=0;i<3;i++){
                    if((i==0)&(intTarjeta[i]==0))
                    {
                        System.out.println("Es indeterminado el numero de la tarjeta");
                        outputmsj = "Resultado inesperado: El numero de la tarjeta no es evaluado por el chatBot";
                        throw new Exception();
                    }
                    else if((i==1)&(intTarjeta[i]==0))
                    {
                        System.out.println("Es indeterminado la fecha de expiracion");
                        outputmsj = "Resultado inesperado: La fecha de expiracion de la tarjeta no es evaluada por el chatBot";
                        throw new Exception();
                    }
                    else if((i==2)&(intTarjeta[i]==0))
                    {
                        System.out.println("Es indeterminado el CVV");
                        outputmsj = "Resultado inesperado: El CVV de la tarjeta no es evaluado por el chatBot";
                        throw new Exception();
                    }
                }
            }
        }
        else if(condicion.contains("2"))
        {
            System.out.println("La tarjeta debe ser invalida");
            if(validaTarjeta==1)
            {
                System.out.println("La tarjeta es (aparentemente) valida - Es (aparentemente) incorrecto");
                
                System.out.println("La tarjeta es valida - Es incorrecto(aparentemente)");
                casoExcepcion = true;
            }
            else if(validaTarjeta>1)
            {
                System.out.println("La tarjeta es invalida - Es correcto");
                casoInvalida = true;
            }
            else if(validaTarjeta==0)
            {
                System.out.println("La tarjeta es indeterminada - Es incorrecto");
                for(int i=0;i<3;i++){
                    if((i==0)&(intTarjeta[i]==0))
                    {
                        System.out.println("Es indeterminado el numero de la tarjeta");
                        outputmsj = "Resultado inesperado: El numero de la tarjeta no es evaluado por el chatBot";
                        throw new Exception();
                    }
                    else if((i==1)&(intTarjeta[i]==0))
                    {
                        System.out.println("Es indeterminado la fecha de expiracion");
                        outputmsj = "Resultado inesperado: La fecha de expiracion de la tarjeta no es evaluada por el chatBot";
                        throw new Exception();
                    }
                    else if((i==2)&(intTarjeta[i]==0))
                    {
                        System.out.println("Es indeterminado el CVV");
                        outputmsj = "Resultado inesperado: El CVV de la tarjeta no es evaluado por el chatBot";
                        throw new Exception();
                    }
                }
            }
        }
        
        //Botan de compra
        Thread.sleep(1000);
        new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"clq\"]/section/section/section[1]/div/div/div[1]/form/div[5]/button")));
        myElement = driver.findElement(By.xpath("//*[@id=\"clq\"]/section/section/section[1]/div/div/div[1]/form/div[5]/button"));
        myElement.click();
        
        if(casoInvalida==false){
            if(casoExcepcion==false){
                new WebDriverWait(driver,180).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layoutContainers\"]/div/div[2]/div/div/div[4]/div/div/section/div/div/div/div[2]/div/a[1]")));
            }else{
                try{
                    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("lq-dialog_desc")));
                }catch(Exception ex){
                    outputmsj = "Resultado inesperado: La tarjeta no es evaluada como incorrecta";
                    throw new Exception();
                }
            }
        }
        System.out.println("Validando la salida...");
        ValidaOutput(nroFlujo,placa,docIdentidad);
    }
    
    public boolean ValidaInput(String nroFlujo,String placa,String nombres,String apPaterno,String apMaterno,String estadoCivil,String nacionalidad,String sexo,String fechaNacimiento,String docIdentidad,String tipoDocumento,String marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta,String departamento,String provincia,String distrito,String direccion,String celular,String clase,String marca,String modelo,String version,String nroSerie,String anhoFabricacion,String uso,String zonaCirculacion) throws ParseException{
        
        boolean resultado = true;
        boolean encontrado = false;
        int indice = -1; 
        
        input_data[0] = new String[] { "1", "1", "1", "234", "1" };
        input_data[1] = new String[] { "1", "1", "1", "234", "2" };
        input_data[2] = new String[] { "1", "1", "1", "234", "1" };
        input_data[3] = new String[] { "1", "12", "2", "234", "1" };

        input_data[4] = new String[] { "1", "2", "2", "234", "1" };
        input_data[5] = new String[] { "1", "2", "2", "234", "2" };
        input_data[6] = new String[] { "1", "2", "2", "234", "1" };
        input_data[7] = new String[] { "1", "2", "1", "234", "1" };

        input_data[8] = new String[] { "1", "2", "1", "234", "2" };
        input_data[9] = new String[] { "1", "2", "1", "234", "1" };
        input_data[10] = new String[] { "2", "2", "2", "234", "1" };
        input_data[11] = new String[] { "2", "2", "2", "234", "2" };
        input_data[12] = new String[] { "2", "2", "2", "234", "1" };
        
        String [] PlacaExisteVal = {"Placa existe","Placa no existe"};
        String [] ContratanteAsociadoVal = {"Contratante asociado","Contratante no asociado"};
        String [] ClienteExisteVal = {"Cliente registrado","Cliente no registrado"};
        String [] PolizaVigenteVal = {"Poliza existe, tiene fin de vigencia y no expira","Poliza existe, tiene fin de vigencia y ya expira","Paliza existe pero no tiene fin de vigencia","Poliza no existe"};
        String [] TarjetaValidaVal = {"Tarjeta valida","Tarjeta invalida"};
        
        InformixDB conexion = new InformixDB();
        
        try{
            iFlujo = Integer.parseInt(nroFlujo);
            iFlujo = iFlujo - 1;
        }catch(Exception ex){
            ex.printStackTrace();
            outputmsj = outputmsj+"El campo 'nroFlujo' debe contener un valor numerico\n";
            mensaje = mensaje + "El campo 'nroFlujo' debe contener un valor numerico - ERROR\n";
            return false;   
        }
        
        String [] flujos = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
        
        String sFlujo = nroFlujo;
        sFlujo = sFlujo.replace(" ", "");
        
        encontrado = false;
        for(int i=0; i<flujos.length;i++){
            if(sFlujo.equals(flujos[i])){
                encontrado = true;
                break;
            }
        }
        if(encontrado==false){
                outputmsj = outputmsj+"El campo 'nroFlujo' no corresponde a un flujo considerado en esta versian.\n";
                return false;
        }
        
        if(tipoDocumento.toUpperCase().contains("DNI")){iDocumento=1;}
        else if (tipoDocumento.toUpperCase().contains("RUC")){iDocumento=2;}
        else if (tipoDocumento.toUpperCase().contains("EXTRAN")){iDocumento=3;}
        else {
            outputmsj = outputmsj+"El campo 'tipoDocumento' no contiene un valor reconocible\n";
            return false;
        }
        
        if(iDocumento==1){
            if(docIdentidad==null||docIdentidad.length()==0||docIdentidad.length()>8){
                outputmsj = outputmsj+"El campo 'docIdentidad' no tiene el formato correcto.\n";
                return false;
            }
        }
        
        if(marcaTarjeta.toUpperCase().contains("VISA")){iMarcaTarjeta=1;}
        else if (marcaTarjeta.toUpperCase().contains("MAST")){iMarcaTarjeta=2;}
        else if (marcaTarjeta.toUpperCase().contains("AME")){iMarcaTarjeta=3;}
        else if (marcaTarjeta.toUpperCase().contains("DIN")){iMarcaTarjeta=4;}
        else {
            outputmsj = outputmsj+"El campo 'marcaTarjeta' no contiene un valor reconocible\n";
            return false;
        }
        
        if(estadoCivil.toUpperCase().startsWith("C")){iEstadoCivil=1;}
        else if (estadoCivil.toUpperCase().startsWith("S")){iEstadoCivil=2;}
        else if (estadoCivil.toUpperCase().startsWith("V")){iEstadoCivil=3;}
        else if (estadoCivil.toUpperCase().startsWith("D")){iEstadoCivil=4;}
        else if (estadoCivil.toUpperCase().startsWith("O")){iEstadoCivil=5;}
        else {
            outputmsj = outputmsj+"El campo 'estadoCivil' no contiene un valor reconocible\n";
            return false;
        }
        
        if(sexo.toUpperCase().startsWith("F")){iSexo=1;}
        else if (sexo.toUpperCase().startsWith("M")){iSexo=2;}
        else {
            outputmsj = outputmsj+"El campo 'sexo' no contiene un valor reconocible\n";
            return false;
        }
        
        try{
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);  
        }catch(Exception excep){
            outputmsj = outputmsj+"El campo 'fechaNacimiento' no posee un formato valido. Introduzca una fecha en el formato dd/MM/yyyy.\n";
            return false;
        }
        
        String sdep = departamento;
        if(sdep== null || !sdep.replace(" ","").matches("^[a-zA-Z]*$")){
            outputmsj = outputmsj+"El campo 'departamento' no posee un formato valido.\n";
            return false;
        }
        
        String spro = provincia;
        if(spro== null || !spro.replace(" ","").matches("^[a-zA-Z]*$")){
            outputmsj = outputmsj+"El campo 'provincia' no posee un formato valido.\n";
            return false;
        }
        
        String sdis = distrito;
        if(sdis== null || sdis.length()==0){
            outputmsj = outputmsj+"El campo 'distrito' no posee un formato valido.\n";
            return false;
        }
        
        String sdir = direccion;
        if(sdir== null || sdis.length()==0){
            outputmsj = outputmsj+"El campo 'direccion' no posee un formato valido.\n";
            return false;
        }
        
        try{
            long lcelular = Long.parseLong(celular);
            if(celular.length()!=9){
                outputmsj = outputmsj+"El campo 'celular' no tiene el tamano adecuado.\n";
                return false;
            }
        }catch(Exception excep){
            outputmsj = outputmsj+"El campo 'celular' no posee un formato valido.\n";
            return false;
        }
        
        if(clase.toUpperCase().contains("AUTO")){iClase=1;}
        else if (clase.toUpperCase().contains("RURAL")){iClase=2;}
        else if (clase.toUpperCase().contains("STATION")){iClase=3;}
        else {
            outputmsj = outputmsj+"El campo 'clase' no contiene un valor reconocible\n";
            return false;
        }
        
        String [] marcas = {"AC","ACURA","ALFA ROMEO","AMC","ASTON MARTIN","AUDI","AUSTIN","AUSTIN HEALEY","AUTOBIANCHI","BAIC","BAJAJ","BENTLEY","BMW","BRILLIANCE","BUICK","BURSTNER","BYD","CADILLAC","CAN-AM","CATERHAM","CHANGAN","CHANGFENG","CHANGHE","CHERY","CHEVROLET","CHRYSLER","CHTC","CITROEN","DACIA","DAEWOO","DAIHATSU","DATSUN","DE SOTO","DETROIT DIESEL","DIAMOND","DKW","DODGE","DONGFENG","DS","EAGLE","EXCALIBUR","FAW","FERRARI","FIAT","FIAT ALLIS","FORD","FOTON","FUJIAN","FULU","GALVEZ","GEELY","GEO","GM","GREAT WALL","GURGEL","HAFEI","HAIGE","HAIMA","HEIBAO","HILLMAN","HINO","HONDA","HONGQI","HUDSON","HYUNDAI","INFINITI","ISUZU","JAC","JAGUAR","JENSEN","JONWAY","KIA","LADA","LAMBORGHINI","LANCIA","LEXUS","LIFAN","LINCOLN","LOTUS","MASERATI","MAZDA","MC LAREN","MEGANE TRIC","MERCEDES BENZ","MERCURY","MG","MGB","MINI","MITSUBISHI","MONDE","MORGAN","MORRIS","MYM","NASH","NISSAN","OLDSMOBILE","OPEL","PACKARD","PERODUA","PEUGEOT","PLYMOUTH","POLARIS","POLSKI","PONTIAC","PORSCHE","PROTON","PUMA","QINGQI","RAMBLER","RENAULT","ROSSION","ROVER","SAAB","SAMSUNG","SATURN","SCION","SEAT","SEGURO DE BREVETES","SEGURO PLACAS DE EXHIBICION","SEM","SEMAFASH","SIMCA","SKODA","SMA","SMART","SOUEAST","STUDEBAKER","SUBARU","SUNBEAM","SUZUKI","TAVRIA","TIANJIN FAW","TORINO","TOYOTA","TRIUMPH","VANDEN PLAS","VAUXHALL","VIRTUS","VOLKSWAGEN","VOLVO","WILLYS OVERLAN","YEMA AUTO","YOGOMO","YUGO","ZHONGHUA","ZOTYE"};
        
        for(int i=0; i<marcas.length;i++){
            if(marca.toUpperCase().contains(marcas[i])){
                encontrado = true;
                indice = i;
                break;
            }
        }
        if(encontrado){iMarca=indice+1;}
        else {
            outputmsj = outputmsj+"El campo 'marca' no contiene un valor reconocible\n";
            return false;
        }
        
        if(uso.toUpperCase().contains("PARTICULAR")){iUso=1;}
        else if (uso.toUpperCase().contains("URBANO")){iUso=2;}
        else {
            outputmsj = outputmsj+"El campo 'uso' no contiene un valor reconocible\n";
            return false;
        }
        
        String [] depaPeru = {"AMAZONAS","ANCASH","APURIMAC","AREQUIPA","AYACUCHO","CAJAMARCA","CALLAO","CUSCO","HUANCAVELICA","HUANUCO","ICA","JUNIN","LA LIBERTAD","LAMBAYEQUE","LIMA","LORETO","MADRE DE DIOS","MOQUEGUA","PASCO","PIURA","PUNO","SAN MARTIN","TACNA","TUMBES","UCAYALI"};
        encontrado = false;
        indice = -1;
        
        for(int i=0; i<depaPeru.length;i++){
            if(zonaCirculacion.toUpperCase().contains(depaPeru[i])){
                encontrado = true;
                indice = i;
                break;
            }
        }
        if(encontrado){iZonaCirculacion=indice+1;}
        else {
            outputmsj = outputmsj+"El campo 'zonaCirculacion' no contiene un valor reconocible\n";
            return false;
        }
        
        /*
            0 -> Placa existente
            1 -> Contratante asociado
            2 -> Cliente existente
            3 -> Paliza vigente
            4 -> Tarjeta correcta
        */
        String esperado = input_data[iFlujo][0];
        int valor = conexion.PlacaExistente(placa);
        mensaje = mensaje+"La condician 'PlacaExistente' dio como resultado: '"+PlacaExisteVal[valor-1]+"'";
        if(!esperado.contains(""+valor)){
            mensaje = mensaje + " - ERROR\n";
            outputmsj = outputmsj+ PlacaExisteVal[valor-1] + " - Este resultado no es permitido\n";
            return false;
        }else{
            mensaje = mensaje + " - OK\n";
        }
        
        esperado = input_data[iFlujo][1];
        valor = conexion.ContratanteAsociado(placa, docIdentidad, iDocumento);
        mensaje = mensaje+"La condician 'ContratanteAsociado' dio como resultado: '"+ContratanteAsociadoVal[valor-1]+"'";
        if(!esperado.contains(""+valor)){
            mensaje = mensaje + " - ERROR\n";
            outputmsj = outputmsj +ContratanteAsociadoVal[valor-1]+ " - Este resultado no es permitido\n";
            return false;
        }else{
            mensaje = mensaje + " - OK\n";
        }
        
        esperado = input_data[iFlujo][2];
        valor = conexion.ClienteExistente(nombres, apPaterno, apMaterno, docIdentidad, iDocumento);
        mensaje = mensaje+"La condician 'ClienteExistente' dio como resultado: '"+ClienteExisteVal[valor-1]+"'";
        if(!esperado.contains(""+valor)){
            mensaje = mensaje + " - ERROR\n";
            outputmsj = outputmsj + ClienteExisteVal[valor-1]+" - Este resultado no es permitido\n";
            return false;
        }else{
            mensaje = mensaje + " - OK\n";
        }
        
        esperado = input_data[iFlujo][3];
        valor = conexion.PolizaVigente(placa);
        mensaje = mensaje+"La condician 'PolizaVigente' dio como resultado: '"+PolizaVigenteVal[valor-1]+"'";
        if(!esperado.contains(""+valor)){
            mensaje = mensaje + " - ERROR\n";
            outputmsj = outputmsj +PolizaVigenteVal[valor-1]+ " - Este resultado no es permitido\n";
            return false;
        }else{
            mensaje = mensaje + " - OK\n";
        }
        
        esperado = input_data[iFlujo][4];
        valor = conexion.TarjetaCorrecta(marcaTarjeta, nroTarjeta, vencimientoTarjeta, CVVTarjeta);
        mensaje = mensaje+"La condician 'TarjetaValida' dio como resultado: '"+TarjetaValidaVal[valor-1]+"'";
        if(!esperado.contains(""+valor)){
            mensaje = mensaje + " - ERROR\n";
            outputmsj = outputmsj + TarjetaValidaVal[valor-1]+" - Este resultado no es permitido\n";
            return false;
        }else{
            mensaje = mensaje + " - OK\n";
        }
        
        return resultado;
    }
    
    public void ValidaOutput(String nroFlujo,String placa, String documento) throws InterruptedException, SQLException, Exception{
        
        output_data[0] = new String[] { "1", "1", "1"};
        output_data[1] = new String[] { "2", "2", "1"};
        output_data[2] = new String[] { "2", "2", "2"};
        output_data[3] = new String[] { "2", "2", "2"};

        output_data[4] = new String[] { "1", "1", "1"};
        output_data[5] = new String[] { "2", "2", "1"};
        output_data[6] = new String[] { "2", "2", "2"};
        output_data[7] = new String[] { "1", "1", "1"};

        output_data[8] = new String[] { "2", "2", "1"};
        output_data[9] = new String[] { "2", "2", "2"};
        output_data[10] = new String[] { "1", "1", "1"};
        output_data[11] = new String[] { "2", "2", "1"};
        output_data[12] = new String[] { "2", "2", "2"};
        
        String [] PagoConfirmadoVal = {"Pago confirmado","Pago no confirmado"};
        String [] PolizaEmitidaVal = {"Poliza emitida","Poliza no emitida"};
        String [] ExtornoVal = {"Extorno validado","Extorno no validado"};
        
        OracleDB conexion = new OracleDB();
        
        int limite = 10;
        
        try{
            while(((conexion.pago.length()==0)&(conexion.poliza.length()==0))&&(limite>0)){
                Thread.sleep(500);
                conexion = new OracleDB();
                conexion.VerificaPagoPoliza(placa,documento);
                conexion.CierraConexion();
                System.out.println("Cuenta regresiva: "+limite);
                System.out.println("Pago: "+conexion.pago);
                System.out.println("Poliza: "+conexion.poliza);
                limite = limite - 1;
            }
        }catch(Exception ex){
            if(output_data[iFlujo][0].contains("1")|output_data[iFlujo][1].contains("1")){
                outputmsj = "No es posible verificar la paliza emitida";
                cierre = true;
                throw new Exception();
            }
        }

        if(conexion.pago.length()<=0){
            if(output_data[iFlujo][0].contains("1")){
                outputmsj = "No es posible verificar el pago";
                cierre = true;
                throw new Exception();
            }
        }

        if(conexion.poliza.length()<=0){
            if(output_data[iFlujo][1].contains("1")){
                outputmsj = "No es posible verificar la poliza emitida";
                cierre = true;
                throw new Exception();
            }
        }else{
            System.out.println("Caso contrario");
            System.out.println("iFlujo: "+iFlujo);
            System.out.println("ergo: "+output_data[iFlujo][1]);
            if(output_data[iFlujo][1].contains("2")){
                outputmsj = "Es posible verificar la poliza cuando no debera haberse emitido";
                cierre = true;
                throw new Exception();
            }
            else{
                this.poliza = conexion.poliza;
            }
        }
    }
    
    @AfterClass
    public static void finalizar() throws InterruptedException, IOException{ 
        driver.quit();
        if(grabarVideo == true){
            UtilRecorder.finalizaGrabar();
        }
    }
    
    @DataProvider
    public static Object[][] dataProviderUSER(){
        Object[][] datos = null;
        try{
            String rutaArchivo = rutaFuente;
            String hojaexcel = "Input";
            datos = UtilExcel.leerExcel(rutaArchivo, hojaexcel);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return datos;
    }

    public void llenarExcel(String nroFlujo, String placa, String nombres, String apPaterno, String apMaterno, String estadoCivil,String nacionalidad,String sexo,String fechaNacimiento, String docIdentidad, String tipoDocumento, String tarjeta, String resultado, String mensaje, String tiempoEjecucion, String salida) throws Exception{
        UtilExcel.setCellData(rutaLog, "Output", nroFlujo, UtilExcel.cuentaLinea, 0);
        UtilExcel.setCellData(rutaLog, "Output", placa, UtilExcel.cuentaLinea, 1);
        UtilExcel.setCellData(rutaLog, "Output", nombres, UtilExcel.cuentaLinea, 2);
        UtilExcel.setCellData(rutaLog, "Output", apPaterno, UtilExcel.cuentaLinea, 3);
        UtilExcel.setCellData(rutaLog, "Output", apMaterno, UtilExcel.cuentaLinea, 4);
        
        UtilExcel.setCellData(rutaLog, "Output", estadoCivil, UtilExcel.cuentaLinea, 5);
        UtilExcel.setCellData(rutaLog, "Output", nacionalidad, UtilExcel.cuentaLinea, 6);
        UtilExcel.setCellData(rutaLog, "Output", sexo, UtilExcel.cuentaLinea, 7);
        UtilExcel.setCellData(rutaLog, "Output", fechaNacimiento, UtilExcel.cuentaLinea, 8);
        
        UtilExcel.setCellData(rutaLog, "Output", docIdentidad, UtilExcel.cuentaLinea, 9);
        UtilExcel.setCellData(rutaLog, "Output", tipoDocumento, UtilExcel.cuentaLinea, 10);
        UtilExcel.setCellData(rutaLog, "Output", tarjeta, UtilExcel.cuentaLinea, 11);
        UtilExcel.setCellData(rutaLog, "Output", resultado, UtilExcel.cuentaLinea, 12);
        UtilExcel.setCellData(rutaLog, "Output", mensaje, UtilExcel.cuentaLinea, 13);
        UtilExcel.setCellData(rutaLog, "Output", tiempoEjecucion, UtilExcel.cuentaLinea, 14);
        UtilExcel.setCellData(rutaLog, "Output", salida, UtilExcel.cuentaLinea, 15);
        UtilExcel.cuentaLinea++;
    }
    
}