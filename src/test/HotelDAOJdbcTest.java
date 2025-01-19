package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Entity.Hotel;
import modelo.Entity.Persona;
import modelo.ImplDAOJDBC.HotelDAOJdbc;
import modelo.acceso.AccessJdbc;

public class HotelDAOJdbcTest {
	static HotelDAOJdbc hotelDAOJdbc;
	static AccessJdbc accessJdbc;
	Hotel entidad = new Hotel(42l, "la estrella");

	@BeforeAll
	static void setUp() throws Exception {
		accessJdbc = new AccessJdbc("hoteles", "harnina", "zzzzzz");
		hotelDAOJdbc = new HotelDAOJdbc(accessJdbc);

	}

	@AfterEach
	void tearDown() {
		hotelDAOJdbc.delete(entidad.getId());
	}

	@Test
	void testCreate() {
		int size = hotelDAOJdbc.findAll().size();
		hotelDAOJdbc.create(entidad);
		assertEquals(hotelDAOJdbc.findAll().size(), size + 1);
	}

	@Test
	void testDelete() {
		int size = hotelDAOJdbc.findAll().size();
		Hotel delete = hotelDAOJdbc.delete(entidad.getId());
		if (delete != null)
			assertEquals(hotelDAOJdbc.findAll().size(), size - 1);
		else
			assertEquals(hotelDAOJdbc.findAll().size(), size);
	}

	@Test
	void testByName() {
		Hotel byName = hotelDAOJdbc.findByName("la estrella");
		if(byName==null) {
			hotelDAOJdbc.create(entidad);
		}
		byName = hotelDAOJdbc.findByName("la estrella");
		assertNotNull(byName);
		assertEquals(byName.getId(), entidad.getId());
	}

	@Test
	void testFindById() {
		Hotel byId = hotelDAOJdbc.findById(entidad.getId());
		if(byId==null)
			hotelDAOJdbc.create(entidad);
		assertNotNull(hotelDAOJdbc.findById(entidad.getId()));
	}
}
