package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;


import mapper.PersonaAdapter;
import mapper.PersonaMapper;
import modelo.AbstractDAO.PersonaDAO;
import modelo.Entity.Persona;
import modelo.acceso.AccessJdbc;

public class PersonaDAOJdbc implements PersonaDAO {

	private AccessJdbc accessJdbc;
	private PersonaMapper personaMapper;

	public PersonaDAOJdbc(AccessJdbc accessJdbc) {
		super();
		this.accessJdbc = accessJdbc;
		personaMapper=new PersonaMapper();
	}

	@Override
	public void create(Persona entidad) {
		String sql = "INSERT INTO "+accessJdbc.getBBDD()+".persona(id,nombre) VALUES (?,?)";
		accessJdbc.update(sql,new PersonaAdapter(entidad));
	}

	@Override
	public Collection<Persona> findAll() {
		ResultSet conjuntoResultados = accessJdbc.execute("SELECT * FROM persona");
		Collection<Persona> personas = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				personas.add(new PersonaMapper().map(conjuntoResultados));
			} 
		} catch (Exception e) {
			return null;
		}
		return personas;
	}

	@Override
	public Persona findById(Long id) {
		String sql = "SELECT * FROM "+accessJdbc.getBBDD()+".persona WHERE id LIKE "+id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		return personaMapper.map(conjuntoResultados);
	}


	@Override
	public Persona findByName(String name) {
		String sql = "select * from "+accessJdbc.getBBDD()+".persona where nombre LIKE '"+name+"'";
		ResultSet execute = accessJdbc.execute(sql);
		return personaMapper.map(execute);
	}

	@Override
	public Persona delete(Long id) {
		Persona byId = findById(id);
		if(byId!=null) {
			String sql = "delete from "+accessJdbc.getBBDD()+".persona where id = "+String.valueOf(id);
			accessJdbc.executeUpdate(sql);
			return byId;
		}
		return null;
	}

}
