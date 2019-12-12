/*#########################################################################################
 * Autor: Francisco Gamarra
 * Fecha de creación: 10/09/2019
 * Descripción: Automatización E-commerce
 * #########################################################################################
 * Marca      Fecha Mod        Descripción Mod           Responsable
 * #########################################################################################
 * 
 * #########################################################################################*/

package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class InformixDB {
    
    Connection conn = null;
    String cmd=null;

    public InformixDB(){
    
        //LLAMADA AL JDBC
        //=============================================================================================
        try{
            Class.forName("com.informix.jdbc.IfxDriver");
        }catch (Exception e){
            System.out.println("ERROR: failed to load Informix JDBC driver.");
            e.printStackTrace();
            return;
        }
        
        //INICIO DE LA CONEXIÓN
        //=============================================================================================     
        String url = "jdbc:informix-sqli://50.0.0.5:1526/insunixdb:INFORMIXSERVER=beta;user=lotnote0;password=notes99";

        try{
            conn = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println("ERROR: failed to connect!");
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        } 
    }
    
    public int PlacaExistente(String placa){
        
        int resultado = 0;
        
        //ENVÍO DEL QUERY
        //=============================================================================================
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;

            String query = "select * from auto_peru where regist = '"+placa+"'";

            rs = stmt.executeQuery(query);
            
            if(rs.next()){
                resultado = 1;
            }else{
                resultado = 2;
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: execution failed - statement: " + cmd);
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public int ClienteExistente(String nombres, String apPaterno, String apMaterno, String docIdentidad, int tipoDocumento){
        
        int resultado = 0;
        
        //ENVÍO DEL QUERY
        //=============================================================================================
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String query;
            
            if(tipoDocumento==2){
                query = "select cliename from client where ruc = '"+docIdentidad+"'";
            }else{
                query = "select cliename from client where elec_book = '"+docIdentidad+"'";
            }
            
            rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                
                String cliename = rs.getString("cliename");
                if(cliename.contains(nombres.toUpperCase())){
                    if(tipoDocumento==2){
                        resultado = 1;
                        break;
                    }else{
                        if(cliename.contains(apPaterno.toUpperCase())&cliename.contains(apMaterno.toUpperCase())){
                            resultado = 1;
                            break; 
                        }
                    }
                }
            }
            
            if(resultado==0){
                resultado = 2;
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: execution failed - statement: " + cmd);
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public int PolizaVigente(String placa) throws ParseException{
        
        //Póliza existe, tiene fin de vigencia y no expiró	1
        //Póliza existe, tiene fin de vigencia y ya expiró	2
        //Póliza existe pero no tiene fin de vigencia	3
        //Poliza no existe	4
        
        int resultado = 0;
        
        //ENVÍO DEL QUERY
        //=============================================================================================
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            String query = "select policy.expirdat from policy inner join (select policy from auto_peru where regist = '"+placa+"') as tabla1  on tabla1.policy = policy.policy";
            
            rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                
                String expirdat = rs.getString("expirdat");
                
                if(expirdat==null){
                    resultado = 3;
                }else{
                    if(expirdat.length()==0){
                        resultado = 3;
                    }else{
                        
                        Date dexpir =new SimpleDateFormat("yyyy-MM-dd").parse(expirdat); 
                        Date dhoy = new Date();
                        boolean condicion = dhoy.compareTo(dexpir)>0;
                        
                        if(condicion==true){
                            //poliza vencida
                            resultado = 2;
                        }else{
                            //poliza no vencida
                            resultado = 1;
                            break;
                        }
                    }
                }
            }
            
            if(resultado==0){
                resultado = 4;
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: execution failed - statement: " + cmd);
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public int ContratanteAsociado(String placa, String docIdentidad, int tipoDocumento) throws ParseException{
        //String placa, String docIdentidad, String tipoDocumento
        int resultado = 0;
        
        //ENVÍO DEL QUERY
        //=============================================================================================
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            String query;
            
            if(tipoDocumento == 2){
                query = "select first 1 client.ruc from policy inner join \n" +
                        "(select policy, certif from auto_peru where \n" +
                        "regist = '"+placa+"' and certif = 0) as tabla1 \n" +
                        "on tabla1.policy=policy.policy inner join \n" +
                        "client on policy.titularc = client.code \n" +
                        "order by policy.expirdat desc";
            }else{
                query = "select first 1 client.elec_book from policy inner join \n" +
                        "(select policy, certif from auto_peru where \n" +
                        "regist = '"+placa+"' and certif = 0) as tabla1 \n" +
                        "on tabla1.policy=policy.policy inner join \n" +
                        "client on policy.titularc = client.code \n" +
                        "order by policy.expirdat desc";
            }
            
            rs = stmt.executeQuery(query);
            
            while ( rs.next() ) {
                String documento;
                
                if(tipoDocumento == 2){
                    documento= rs.getString("ruc");
                }else{
                    documento= rs.getString("elec_book");   
                }
                
                System.out.println("Documento: "+documento);
                
                if(documento!=null){
                    if(documento.contains(docIdentidad))
                    {
                        resultado = 1;
                        break;
                    } 
                }
            }
            if(resultado==0){
                resultado = 2;
            }
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: execution failed - statement: " + cmd);
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public int TarjetaCorrecta (String marcaTarjeta, String nroTarjeta, String vencimientoTarjeta, String CVVTarjeta){
        
        int resultado = 0;
        
        List<String[]> listaTarjetas = Tarjetas();
        Iterator<String[]> itTarjetas = listaTarjetas.listIterator();
        
        while(itTarjetas.hasNext()){
            String[] tarjeta = itTarjetas.next();
            
            if(tarjeta[0].equals(marcaTarjeta)&tarjeta[1].equals(nroTarjeta)&tarjeta[2].equals(vencimientoTarjeta)&tarjeta[3].equals(CVVTarjeta))
            {
                resultado = 1;
                break;
            }
            else{
                resultado = 2;
            }
        }
        return resultado;
    }
    
    public List Tarjetas ()
    {
        List<String[]> listaTarjetas = new ArrayList<String[]>();
        
        String [] tarjeta1 = {"Visa","4111111111111111","09/20","123"};
        String [] tarjeta2 = {"Master Card","5111111111111118","06/20","039"};
        String [] tarjeta3 = {"American Express","371212121212122","11/20","2841"};
        String [] tarjeta4 = {"Diners Club","36001212121210","04/20","964"};
        
        listaTarjetas.add(tarjeta1);
        listaTarjetas.add(tarjeta2);
        listaTarjetas.add(tarjeta3);
        listaTarjetas.add(tarjeta4);
        
        return listaTarjetas;
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
