/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.informatica.utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Juan David Uribe Ruiz
 */
public class ConexionBDPostgres {
    
    private String driver = "org.postgresql.Driver";
    private String connectString = "jdbc:postgresql://localhost:5432/BD_INFORMATICA";
    private String user = "INFORMATICA";
    private String password = "informatica";
    
    public Connection obtenerConexion() throws Exception{
        try{
            Class.forName(getDriver());
            Connection con = DriverManager.getConnection(getConnectString(), getUser(), getPassword());
            return con;
        }
        catch ( Exception e ){
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
    }
    
    public void cerrarConexion(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
        con = null;
    }
    
    public void cerrarStatement(Statement stmt) throws Exception{
        if(stmt!=null){
            stmt.close();
        }
        stmt = null;
    }
    
    public void cerrarPreparedStatement(PreparedStatement pst) throws Exception{
        if(pst!=null){
            pst.close();
        }
        pst = null;
    }
    
    public void cerrarResultSet(ResultSet rs) throws Exception{
        if(rs!=null){
            rs.close();
        }
        rs = null;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @return the connectString
     */
    public String getConnectString() {
        return connectString;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
