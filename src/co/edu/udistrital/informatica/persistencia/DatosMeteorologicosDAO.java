package co.edu.udistrital.informatica.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.informatica.dto.DatosMeteorologicosDTO;
import co.edu.udistrital.informatica.utilidad.ConexionBDPostgres;

/**
 * 
 * @author Juan David Uribe Ruz
 *
 */
public class DatosMeteorologicosDAO {
	
	ConexionBDPostgres conexionBDPostgres;
    
	/**
	 * Constructor por defecto
	 */
    public DatosMeteorologicosDAO(){
        conexionBDPostgres = new ConexionBDPostgres();
    }
    
    /**
     * 
     * @param idSensor
     * @return
     * @throws Exception
     */
    public DatosMeteorologicosDTO cargarValorSensorXIdSensor(int idSensor) throws Exception{
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DatosMeteorologicosDTO datosMeteorologicosDTO;
        try {
            conn = conexionBDPostgres.obtenerConexion();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT " +
            		"\"ID\", \"ESTACION\", \"LATITUD\", \"LONGITUD\", \"DATO\", \"TIPO_DATO\", \"FECHA\" " +
            		"FROM \"DATOS_METEOROLOGICOS\" " +
            		"WHERE \"ESTACION\" = "+idSensor+" ORDER BY \"FECHA\" LIMIT 1");
            datosMeteorologicosDTO = new DatosMeteorologicosDTO();
            if(rs.next()){
            	
            	datosMeteorologicosDTO.setEstacion(rs.getInt("ESTACION"));
            	datosMeteorologicosDTO.setLatitud(rs.getString("LATITUD"));
            	datosMeteorologicosDTO.setLongitud(rs.getString("LONGITUD"));
            	datosMeteorologicosDTO.setDato(rs.getString("DATO"));
            	datosMeteorologicosDTO.setTipoDato(rs.getString("TIPO_DATO"));
            	datosMeteorologicosDTO.setFecha(rs.getString("FECHA"));
            }
           
            
            return datosMeteorologicosDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally{
            try {
                conexionBDPostgres.cerrarResultSet(rs);
                conexionBDPostgres.cerrarStatement(stmt);
                conexionBDPostgres.cerrarConexion(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
    
    /**
     * 
     * @param idSensor
     * @return
     * @throws Exception
     */
    public List<DatosMeteorologicosDTO> cargarValorHistoricoXIdSensor(int idSensor) throws Exception{
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<DatosMeteorologicosDTO> lista;
        DatosMeteorologicosDTO datosMeteorologicosDTO;
        try {
            conn = conexionBDPostgres.obtenerConexion();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT " +
            		"\"ID\", \"ESTACION\", \"LATITUD\", \"LONGITUD\", \"DATO\", \"TIPO_DATO\", \"FECHA\" " +
            		"FROM \"DATOS_METEOROLOGICOS\" " +
            		"WHERE \"ESTACION\" = "+idSensor+" LIMIT 40");

            lista = new ArrayList<DatosMeteorologicosDTO>();
            while(rs.next()){
            	datosMeteorologicosDTO = new DatosMeteorologicosDTO();
            	datosMeteorologicosDTO.setEstacion(rs.getInt("ESTACION"));
            	datosMeteorologicosDTO.setLatitud(rs.getString("LATITUD"));
            	datosMeteorologicosDTO.setLongitud(rs.getString("LONGITUD"));
            	datosMeteorologicosDTO.setDato(rs.getString("DATO"));
            	datosMeteorologicosDTO.setTipoDato(rs.getString("TIPO_DATO"));
            	datosMeteorologicosDTO.setFecha(rs.getString("FECHA"));
            	lista.add(datosMeteorologicosDTO);
            }
            
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally{
            try {
                conexionBDPostgres.cerrarResultSet(rs);
                conexionBDPostgres.cerrarStatement(stmt);
                conexionBDPostgres.cerrarConexion(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
    
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public int obtenerUltimoId() throws Exception{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Integer ultimoId = null;
        try {
            conn = conexionBDPostgres.obtenerConexion();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT MAX(\"ID\") AS MAX_ID FROM \"DATOS_METEOROLOGICOS\"");

            if(rs.next()){
                ultimoId = rs.getInt("MAX_ID");
            }
            else{
                return 0;
            }
            
            return ultimoId;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally{
            try {
                conexionBDPostgres.cerrarResultSet(rs);
                conexionBDPostgres.cerrarStatement(stmt);
                conexionBDPostgres.cerrarConexion(conn);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
    
    /**
     * 
     * @param id
     * @param estacion
     * @param latitud
     * @param longitud
     * @param dato
     * @param tipoDato
     * @param fecha
     * @throws Exception 
     */
    public void insertarDatos(int id, DatosMeteorologicosDTO datosMeteorologicosDTO) throws Exception{
        Connection conn = null;
        PreparedStatement pst = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
        	java.util.Date fecha = sdf.parse(datosMeteorologicosDTO.getFecha());
            conn = conexionBDPostgres.obtenerConexion();
            String stm = "INSERT INTO \"DATOS_METEOROLOGICOS\"" +
            		" (\"ID\", \"ESTACION\", \"LATITUD\", \"LONGITUD\", \"DATO\", \"TIPO_DATO\", \"FECHA\") " +
            		" VALUES(?,?,?,?,?,?,?)";
            System.out.println(stm);
            pst = conn.prepareStatement(stm);
            
            pst.setInt(1, id);
            pst.setInt(2, datosMeteorologicosDTO.getEstacion());
            pst.setString(3, datosMeteorologicosDTO.getLatitud());
            pst.setString(4, datosMeteorologicosDTO.getLongitud());
            pst.setString(5, datosMeteorologicosDTO.getDato());
            pst.setString(6, datosMeteorologicosDTO.getTipoDato());
            pst.setDate(7, new Date(fecha.getTime()));
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		try {
			DatosMeteorologicosDAO datosMeteorologicosDAO = new DatosMeteorologicosDAO();
			int x = datosMeteorologicosDAO.obtenerUltimoId();
			System.out.println(x);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
   

}
