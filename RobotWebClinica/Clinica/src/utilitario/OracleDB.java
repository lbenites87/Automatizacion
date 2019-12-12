/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class OracleDB {
    
    Connection conn = null;
    String cmd=null;
    
    public String poliza = "";
    public String pago = "";

    public OracleDB() throws SQLException{
    
        //LLAMADA AL JDBC
        //=============================================================================================
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e){
            System.out.println("ERROR: failed to load Oracle JDBC driver.");
            e.printStackTrace();
            return;
        }

        //INICIO DE LA CONEXIÓN
        //=============================================================================================     
        try{
            conn = DriverManager.getConnection("jdbc:oracle:thin:@50.0.0.193:1521:oraprod","INSUDB","#vtme.ins04!");
        }catch (SQLException e){
            System.out.println("ERROR: failed to connect!");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        } 
    }
    
    public void Consulta(String query){
        
        //ENVÍO DEL QUERY
        //=============================================================================================
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String rpta1 = rs.getString(1);
                String rpta2 = rs.getString(2);
                String rpta3 = rs.getString(3);
                System.out.println(rpta1+"\t"+rpta2+"\t"+rpta3);
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: execution failed - statement: " + cmd);
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void VerificaPagoPoliza(String placa, String documento){
        
        //ENVÍO DEL QUERY
        //=============================================================================================
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            rs = stmt.executeQuery("SELECT NUMERO_POLIZA, PASAR_DESC_DENEGACION, NUMERO_DOCUMENTO FROM WEBCORPORATIVA.SOAT_ORDEN_PAGO_POLIZA WHERE PLACA = '"+placa+"' AND TRUNC(FECHA_CREA) = '"+sdf.format(today)+"'");
            
            while(rs.next()){
                
                String numeroPoliza = rs.getString(1);
                String pasarDescDenegacion = rs.getString(2);
                String numeroDocumento = rs.getString(3);
                
                if(documento.equals(numeroDocumento)){
                    this.poliza = numeroPoliza;
                    this.pago = pasarDescDenegacion;
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: execution failed - statement: " + cmd);
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void CierraConexion(){
        //CIERRE DE LA CONEXIÓN
        //=============================================================================================
        try {
            conn.close();
        } 
        catch (SQLException e) 
        {
            System.out.println("ERROR: failed to close the connection!");
            e.printStackTrace();
            return;
        }
    }
}
