package modelo.AbstractDAO;

import modelo.Entity.Persona;

public interface PersonaDAO extends GenericDAO<Persona, Long> {
	public Persona findByName(String name);
}
