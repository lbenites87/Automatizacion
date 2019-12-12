/*#########################################################################################
 * Autor: Francisco Gamarra
 * Fecha de creación: 10/09/2019
 * Descripción: Automatización E-commerce
 * #########################################################################################
 * Marca      Fecha Mod        Descripción Mod           Responsable
 * #########################################################################################
 * 
 * #########################################################################################*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FirstWindowClassController{
    
    @FXML
    TextField txtArchivo;
    
    @FXML
    TextField txtLog;
    
    @FXML
    TextField txtUser;
    
    @FXML
    CheckBox chbVideo;
    
    @FXML
    PasswordField txtPassword;
    
    MainClass padre;
    String rutaGral;
    
    /**
     * Initializes the controller class.
     */
    public void initialize(MainClass padre) {
        this.padre = padre;
    }
    
    public void setPadre(MainClass padre){
        this.padre = padre;
    }
    
    public boolean validaExcel(String rutaArchivo) throws FileNotFoundException, IOException{
        
        try{
            FileInputStream archivo = new FileInputStream(new File(rutaArchivo));
            XSSFWorkbook archivoExcel = new XSSFWorkbook(archivo);
            XSSFSheet hojaExcel = archivoExcel.getSheet("Input");
        
            Row row = hojaExcel.getRow(0);
            
            Cell cell;
            String cabecera;

            int columnas = row.getLastCellNum();
            
            int colprev = 0;
            String previsto = "";
            
            previsto = "nroFlujo,placa,nombres,apPaterno,apMaterno,estadoCivil,nacionalidad,sexo,fechaNacimiento,docIdentidad,tipoDocumento,marcaTarjeta,nroTarjeta,vencimientoTarjeta,CVVTarjeta,departamento,provincia,distrito,direccion,celular,clase,marca,modelo,version,nroSerie,anhoFabricacion,uso,zonaCirculacion,";
            colprev = 28;
            
            if(columnas!=colprev){
                return false;
            }else{
                int real = 0;

                for(int i=0;i<columnas;i++){    
                    cell = row.getCell(i);
                    cabecera = cell.getStringCellValue();
                    String cPrev = previsto.substring(0,previsto.indexOf(","));

                    previsto = previsto.replaceAll(cPrev+",", "");
                    
                    if(cabecera.equals(cPrev)){
                        real++;
                    }
                    else{
                        break;
                    }
                }

                if(real==columnas){
                    return true;
                }else{
                    return false;
                }
            }
            
        }catch(Exception ex){
            return false;
        }
        
    }
    
    @FXML
    private void handleBuscarArchivo() throws IOException {
        
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            ExtensionFilter filter = new ExtensionFilter("EXCEL files (*.xlsx)", "*.xlsx");
            fileChooser.setTitle("Buscar archivo fuente");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(stage);

            String path = file.getAbsolutePath();
            
            txtArchivo.setText(file.getAbsolutePath());
    }
    
    @FXML
    private void handleBuscarLog() {
        
        Stage stage = new Stage();
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Seleccionar carpeta de destino");
        File selectedDirectory = dirChooser.showDialog(stage);
        
        Date fecha = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YY_MM_dd_hh_mm_ss");
        String fechas = df.format(fecha);
        rutaGral = selectedDirectory.getAbsolutePath();
        txtLog.setText(selectedDirectory.getAbsolutePath()+"\\logEcommerce_"+fechas+".xlsx");
    }
    
    @FXML
    private void handleAceptar() throws Throwable {
        try
        {
            boolean grabarVideo = chbVideo.isSelected();
            
            String ruta = txtLog.getText();
            
            FileOutputStream fileOut = new FileOutputStream(ruta);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet worksheet = workbook.createSheet("Output");
            
            XSSFRow row1 = worksheet.createRow((short) 0);
            XSSFCell cellA1 = row1.createCell((short) 0);
            cellA1.setCellValue("nroFlujo");
            
            cellA1 = row1.createCell((short) 1);
            cellA1.setCellValue("placa");
            
            cellA1 = row1.createCell((short) 2);
            cellA1.setCellValue("nombres");
            
            cellA1 = row1.createCell((short) 3);
            cellA1.setCellValue("apPaterno");
            
            cellA1 = row1.createCell((short) 4);
            cellA1.setCellValue("apMaterno");
            
            //=============================================
            cellA1 = row1.createCell((short) 5);
            cellA1.setCellValue("estadoCivil");
            cellA1 = row1.createCell((short) 6);
            cellA1.setCellValue("nacionalidad");
            cellA1 = row1.createCell((short) 7);
            cellA1.setCellValue("sexo");
            cellA1 = row1.createCell((short) 8);
            cellA1.setCellValue("fechaNacimiento");
            //==============================================
            
            cellA1 = row1.createCell((short) 9);
            cellA1.setCellValue("docIdentidad");
            
            cellA1 = row1.createCell((short) 10);
            cellA1.setCellValue("tipoDocumento");
            
            cellA1 = row1.createCell((short) 11);
            cellA1.setCellValue("tarjeta");
            
            cellA1 = row1.createCell((short) 12);
            cellA1.setCellValue("resultado");
            
            cellA1 = row1.createCell((short) 13);
            cellA1.setCellValue("mensaje");
            
            cellA1 = row1.createCell((short) 14);
            cellA1.setCellValue("tiempoEjecucion (ms)");
            
            cellA1 = row1.createCell((short) 15);
            cellA1.setCellValue("poliza");
            
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
                     
            
            if(validaExcel(txtArchivo.getText())){
                
                padre.rutaFuente = txtArchivo.getText();
                padre.rutaLog = ruta;
                padre.rutaGral = rutaGral;
                padre.firstStage.close();
                padre.grabarVideo = grabarVideo;
                padre.MainLoop(3);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en el formato");
                alert.setHeaderText("El archivo especificado no posee el formato correcto");
                alert.setContentText("Escoja un otro archivo de entrada");
                alert.showAndWait();
            }
            
        }catch(FileNotFoundException ex){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de data insuficiente");
            alert.setHeaderText("Falta introducir uno o más campos");
            alert.setContentText("Introduzca información en todos los campos");
            alert.showAndWait();
            
        }  
    }
}
