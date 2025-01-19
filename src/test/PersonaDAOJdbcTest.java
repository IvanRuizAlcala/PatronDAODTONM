package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Entity.Persona;
import modelo.ImplDAOJDBC.PersonaDAOJdbc;
import modelo.acceso.AccessJdbc;

class PersonaDAOJdbcTest {
	static PersonaDAOJdbc personaDAOJdbc;
	static AccessJdbc accessJdbc;
	Persona entidad = new Persona(6l, "Juan");

	@BeforeAll
	static void setUp() throws Exception {
		accessJdbc = new AccessJdbc("hoteles", "harnina", "zzzzzz");
		personaDAOJdbc = new PersonaDAOJdbc(accessJdbc);

	}

	@AfterEach
	void tearDown() {
		personaDAOJdbc.delete(entidad.getId());
	}

	@Test
	void testCreate() {
		int size = personaDAOJdbc.findAll().size();
		personaDAOJdbc.create(entidad);
		assertEquals(personaDAOJdbc.findAll().size(), size + 1);
	}

	@Test
	void testDelete() {
		int size = personaDAOJdbc.findAll().size();
		Persona delete = personaDAOJdbc.delete(entidad.getId());
		if (delete != null)
			assertEquals(personaDAOJdbc.findAll().size(), size - 1);
		else
			assertEquals(personaDAOJdbc.findAll().size(), size);
	}

	@Test
	void testByName() {
		Persona byName = personaDAOJdbc.findByName("Juan");
		if(byName==null) {
			personaDAOJdbc.create(entidad);
		}
		byName = personaDAOJdbc.findByName("Juan");
		assertNotNull(byName);
		assertEquals(byName.getId(), entidad.getId());
	}

	@Test
	void testFindById() {
		Persona byId = personaDAOJdbc.findById(entidad.getId());
		if(byId==null)
			personaDAOJdbc.create(entidad);
		assertNotNull(personaDAOJdbc.findById(entidad.getId()));
	}

}
