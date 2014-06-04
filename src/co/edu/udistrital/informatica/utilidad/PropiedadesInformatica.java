package co.edu.udistrital.informatica.utilidad;

import co.edu.udistrital.informatica.constantes.PropiedadConstantes;
 /**
  * Clase encargada de administrar el archivo de 
  * propiedades
  * 
  * @author Juan David Uribe Ruiz.
  *
  */
public class PropiedadesInformatica {
	
	/** Instancia de la clase */ 
	private static PropiedadesInformatica instancia ;  
	
	/** Referencia el objeto con la logica para cargar
	 * los archivos de propiedades */
	private Propiedades propiedad;
	  
	/**
	 * Constructor por defecto privado
	 * @throws Exception 
	 */
	private PropiedadesInformatica() throws Exception {  
		setPropiedad(new Propiedades(PropiedadConstantes.ARCHIVO_PROPIEDADES));
	}  
	  
	  
	/**
	 * Metodo para obtener la instancia del objeto
	 * @return
	 * @throws Exception 
	 */
	public static PropiedadesInformatica getInstance() throws Exception {  
		if(instancia==null) 
			instancia = new PropiedadesInformatica(); 
		return instancia;  
	}


	/**
	 * @return the propiedad
	 */
	public Propiedades getPropiedad() {
		return propiedad;
	}


	/**
	 * @param propiedad the propiedad to set
	 */
	public void setPropiedad(Propiedades propiedad) {
		this.propiedad = propiedad;
	}
	
}
