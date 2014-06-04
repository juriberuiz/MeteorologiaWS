package co.edu.udistrital.informatica.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.udistrital.informatica.dto.DatosMeteorologicosDTO;
import co.edu.udistrital.informatica.persistencia.DatosMeteorologicosDAO;

/**
 * Clase encargada de exponer los servicios web
 * para ser consumidos por los clientes que desean
 * consultar.
 * 
 * @author Juan David Uribe Ruiz
 *
 */
public class MeteorologiaWS {
	
	DatosMeteorologicosDAO consultaDatosMeteorologicosDAO;
	
	/**
	 * Metodo que consulta un datos para un sensor especifico
	 * 
	 * @param idSensor
	 * @return
	 */
	public DatosMeteorologicosDTO cargarValorSensorXIdSensor(int idSensor){
		consultaDatosMeteorologicosDAO = new DatosMeteorologicosDAO();
		try {
			return consultaDatosMeteorologicosDAO.cargarValorSensorXIdSensor(idSensor);
		} catch (Exception e) {
			DatosMeteorologicosDTO datosMeteorologico = new DatosMeteorologicosDTO();
			datosMeteorologico.setDato(e.getMessage());
			return datosMeteorologico;
		}
	}
	
	/**
	 * Metodo encargado de consultar el historial para un sensor
	 * 
	 * @param idSensor
	 * @return
	 */
	public DatosMeteorologicosDTO[] cargarValorHistoricoXIdSensor(int idSensor){
		consultaDatosMeteorologicosDAO = new DatosMeteorologicosDAO();
		List<DatosMeteorologicosDTO> lista;
		DatosMeteorologicosDTO[] listaDatos;
		try {
			lista = consultaDatosMeteorologicosDAO.cargarValorHistoricoXIdSensor(idSensor);
			listaDatos = new DatosMeteorologicosDTO[lista.size()];
			int i = 0;
			for(DatosMeteorologicosDTO datosMeteorologicos : lista){
				System.out.println("[" + i + "] " + datosMeteorologicos.toString());
				listaDatos[i] = datosMeteorologicos;
				i++;
			}
			return listaDatos;
		} catch (Exception e) {
			listaDatos = new DatosMeteorologicosDTO[1];
			DatosMeteorologicosDTO datosMeteorologicos = new DatosMeteorologicosDTO();
			datosMeteorologicos.setDato(e.getMessage());
			listaDatos[0] = datosMeteorologicos;
			return listaDatos;
		}
	}
	
	/**
	 * Metodo encargado de alamacenar en BD un registro.
	 * 
	 * @param estacion
	 * @param latitud
	 * @param longitud
	 * @param dato
	 * @param tipoDato
	 * @param fecha
	 */
	public void insertarDatos(int estacion, String latitud, String longitud, String dato, String tipoDato, String fecha){
		consultaDatosMeteorologicosDAO = new DatosMeteorologicosDAO();
		DatosMeteorologicosDTO datosMeteorologico;
		int ultimoId;
		try {
			ultimoId = consultaDatosMeteorologicosDAO.obtenerUltimoId(); 
			ultimoId++;
			
			datosMeteorologico = new DatosMeteorologicosDTO();
			datosMeteorologico.setEstacion(estacion);
			datosMeteorologico.setLatitud(latitud);
			datosMeteorologico.setLongitud(longitud);
			datosMeteorologico.setDato(dato);
			datosMeteorologico.setTipoDato(tipoDato);
			datosMeteorologico.setFecha(fecha);
			consultaDatosMeteorologicosDAO.insertarDatos(ultimoId, datosMeteorologico);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		MeteorologiaWS meteorologiaWS = new MeteorologiaWS();
//		meteorologiaWS.insertarDatos(2, "5645634", "6345634569865", "33", "°C", "2014/05/15 14:25:12");
//	}

}
