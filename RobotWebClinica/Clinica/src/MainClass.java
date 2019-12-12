

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import static utilitario.UtilExcel.archivo;
import static utilitario.UtilExcel.archivoExcel;
import static utilitario.UtilExcel.fileOut;
import static utilitario.UtilExcel.hojaExcel;

/**
 *
 * @author USUARIO
 */
public class MainClass extends Application {
    
    public Stage welcomeStage;
    public Stage firstStage;
    
    public String rutaFuente;
    public String rutaLog;
    public String rutaGral;
    public boolean grabarVideo;
    
    public int porcentaje = 0;
    
    public void MainLoop(int secuencia) throws IOException{
        
        switch(secuencia) {
        case 1 :
           initWelcomeWindowClass();
           break;
           
        case 2 :
           FirstWindowClass();
           break;
           
        case 3 :
           
           porcentaje = 0;
           TestConnectivity("50.0.0.5");
           
           if(porcentaje==100){
                porcentaje = 0;
                TestConnectivity("10.10.32.93");
                if(porcentaje==100){
                    MainLoop(4);
                }
                else {
                   if(porcentaje>0) {
                     Alert alert = new Alert(AlertType.WARNING);
                     alert.setTitle("Conexión deficiente");
                     alert.setHeaderText("La conexión con el servidor de E-commerce es deficiente");
                     alert.setContentText("Contacte a un administrador");
                     alert.showAndWait();  
                   }else { 
                     Alert alert = new Alert(AlertType.ERROR);
                     alert.setTitle("Error de conexión");
                     alert.setHeaderText("No es posible conectarse con el servidor de E-commerce");
                     alert.setContentText("Revise su conexión");
                     alert.showAndWait();
                   }
                }
           }
           else {
              if(porcentaje>0) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Conexión deficiente");
                alert.setHeaderText("La conexión con el servidor de INSUNIX es deficiente");
                alert.setContentText("Contacte a un administrador");
                alert.showAndWait();
              }else { 
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de conexión");
                alert.setHeaderText("No es posible conectarse con el servidor de INSUNIX");
                alert.setContentText("Revise su conexión");
                alert.showAndWait();
              }
           }
           
           break;
           
        case 4:
            
            TestClass1.rutaLog = rutaLog;
            TestClass1.rutaFuente = rutaFuente;
            TestClass1.grabarVideo = grabarVideo;
            TestClass1.rutaGral = rutaGral;

            JUnitCore junit = new JUnitCore();
            junit.addListener (new TextListener(System.out));
            Result result = junit.run(TestClass1.class);
        
        default :
           //Statements
           break;
           
        }  
    }
 
    public void FirstWindowClass() throws IOException{
        this.firstStage = new Stage();
        
        //Load splash screen view FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainClass.class.getResource("/FirstWindowClass.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        FirstWindowClassController controller = (FirstWindowClassController) loader.getController();
        controller.initialize(this);
        
        Scene scene = new Scene(pane);
        firstStage.setTitle("Robot Cl�nica");
        
        firstStage.getIcons().add(new Image("icon.png"));
        firstStage.setScene(scene);
        firstStage.initStyle(StageStyle.DECORATED);
        firstStage.show();
        
    }
    
    public void initWelcomeWindowClass() throws IOException{
        
        this.welcomeStage = new Stage();
        
        //Load splash screen view FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainClass.class.getResource("/WelcomeWindowClass.fxml"));
        StackPane pane = (StackPane)loader.load();
        WelcomeWindowClassController controller = (WelcomeWindowClassController) loader.getController();
        String dirLogo = MainClass.class.getResource("/MDPLogo.png").toString();
        controller.initialize(dirLogo);
        
        Scene scene = new Scene(pane);
        
        welcomeStage.getIcons().add(new Image("icon.png"));
        welcomeStage.setScene(scene);
        welcomeStage.initStyle(StageStyle.UNDECORATED);
        welcomeStage.show();
        
        //Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
 
        //Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
 
        fadeIn.play();
 
        //After fade in, start fade out
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();  
        });
        
        fadeOut.setOnFinished((e) -> {
            welcomeStage.close(); 
            try {
                MainLoop(2);
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    public void TestConnectivity(String dirIP) {
            
        String ip = dirIP;
        String command = "ping " + ip;
        try {
                Runtime r = Runtime.getRuntime();
                Process p = r.getRuntime().exec(command);
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String s = "";
                int nLin = 1;

                while (((s = inputStream.readLine()) != null)&(nLin<7)) {
                        if(nLin>2){
                            if((!s.contains("inaccesible"))&(!s.contains("agotado"))){
                                porcentaje = porcentaje + 25;
                            }
                        }
                        nLin++;
                }

                p.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public  int[] GeneraIndice() throws FileNotFoundException, IOException{
        
        //Cuántas ventanas concurrentes hay que abrir?
        //Cuántas filas hay dentro de esa ventana concurrente?
        
        ArrayList<Integer> indice = new ArrayList<Integer>();
        
        FileInputStream archivo = new FileInputStream(new File(rutaFuente));
        XSSFWorkbook archivoExcel = new XSSFWorkbook(archivo);
        XSSFSheet hojaExcel = archivoExcel.getSheet("Input");
        Iterator<Row> filas = hojaExcel.iterator();
        
        filas.next();
        
        int numCon = 0;
        int rowCount = 0;
        int prevCont = 0;
        int cuenta = 1;
        
        while(filas.hasNext()){
            
            Row filaActual = filas.next();
            Cell celdaActual = filaActual.getCell(0);
            
            int i = (int)celdaActual.getNumericCellValue();
            
            if(rowCount==0){
                numCon = 0;
                indice.add(1);
            }else{
                if(prevCont==i){
                    cuenta = indice.get(numCon)+1;
                    indice.add(numCon,cuenta);
                }else{
                    numCon++;
                    indice.add(numCon,1);
                } 
            }
            prevCont = i;
            rowCount++;
        }
        
        int[] intArray = new int[numCon+1];
        for(int i=0;i<numCon+1;i++){
            intArray[i] = indice.get(i);
        }
        
        return intArray;
    }
    
    class RunnableDemo implements Runnable {
        private Thread t;
        private String threadName;

        RunnableDemo( String name) {
           threadName = name;
           System.out.println("Creating " +  threadName );
        }

        public void run() {
            System.out.println("Running " +  threadName );
             
            JUnitCore junit = new JUnitCore();
            junit.addListener (new TextListener(System.out));
            Result result = junit.run(TestClass1.class);

            if (result.getFailureCount ()> 0) {
                System.out.println ("Test failed.");
                System.exit(1);
            } else {
                System.out.println ("Prueba finalizada correctamente");
                System.exit(0);
            }
            
            System.out.println("Thread " +  threadName + " exiting.");
        }

        public void start () {
           System.out.println("Starting " +  threadName );
           if (t == null) {
              t = new Thread (this, threadName);
              t.start ();
           }
        }
     }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainLoop(1);   
    }
    
    public static void main (String args []) {
        launch(args);  
    }
    
    
}
