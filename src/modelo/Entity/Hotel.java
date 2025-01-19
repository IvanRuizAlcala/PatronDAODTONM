package modelo.Entity;

import java.io.Serializable;

public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	
	public Hotel() {
		super();
	}
	
	public Hotel(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Hotel(Long id,String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	
	
	
}
