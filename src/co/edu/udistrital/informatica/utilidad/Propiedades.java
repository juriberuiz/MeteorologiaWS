package co.edu.udistrital.informatica.utilidad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {

	private Properties properties;
    
	public Propiedades(String archivo) throws Exception{
		iniciar(archivo);
	}
	
	/**
	 * Metodo para recuperar una propiedad del archivo
	 * @param key clave a buscar en las propiedades
	 * @return valor de la propiedad en el archivo
	 */
	public String getProperty(String key){

		return properties.getProperty(key);
	}
	
	/**
	 * Metodo encargado de cargar el archivo de propiedades
	 */
	public void iniciar(String archivo) throws Exception{
		File propertiesFile;
		InputStream inputStream = null;
		try {
			properties = new Properties();
			propertiesFile = new File(archivo);
			inputStream = new FileInputStream(propertiesFile);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					
				}
			}
		}
	}
	
}