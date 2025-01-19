package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Entity.Hotel;
import modelo.Entity.Persona;
import modelo.Entity.Reserva;
import modelo.ImplDAOJDBC.HotelDAOJdbc;
import modelo.ImplDAOJDBC.PersonaDAOJdbc;
import modelo.ImplDAOJDBC.ReservaDAOJdbc;
import modelo.acceso.AccessJdbc;

public class ReservaDAOJdbcTest {
		static ReservaDAOJdbc reservaDAOjdbc;
		static AccessJdbc accessJdbc;
		static PersonaDAOJdbc personaDAOJdbc;
		static HotelDAOJdbc hotelDAOJdbc;
		Persona entidadPersona = new Persona(6l, "Juan");
		Hotel entidadHotel = new Hotel(42l, "la estrella");
		Reserva entidad = new Reserva(2l,6l,42l,LocalDate.now());

		@BeforeAll
		static void setUp() throws Exception {
			accessJdbc = new AccessJdbc("hoteles", "harnina", "zzzzzz");
			personaDAOJdbc = new PersonaDAOJdbc(accessJdbc);
			hotelDAOJdbc = new HotelDAOJdbc(accessJdbc);
			reservaDAOjdbc = new ReservaDAOJdbc(accessJdbc);

		}

		@AfterEach
		void tearDown() {
			reservaDAOjdbc.delete(entidad.getId());
			personaDAOJdbc.delete(entidadPersona.getId());
			hotelDAOJdbc.delete(entidadHotel.getId());
			
		}

		@Test
		void testCreate() {
			int size = reservaDAOjdbc.findAll().size();
			personaDAOJdbc.create(entidadPersona);
			hotelDAOJdbc.create(entidadHotel);
			reservaDAOjdbc.create(entidad);
			assertEquals(reservaDAOjdbc.findAll().size(), size + 1);
		}
		@Test
		void testFinById() {
			Reserva byId = reservaDAOjdbc.findById(entidad.getId());
			if(byId==null)
				personaDAOJdbc.create(entidadPersona);
				hotelDAOJdbc.create(entidadHotel);
				reservaDAOjdbc.create(entidad);
			assertNotNull(reservaDAOjdbc.findById(entidad.getId()));
		}
		@Test
		void testDelete() {
			int size = reservaDAOjdbc.findAll().size();
			Reserva delete = reservaDAOjdbc.delete(entidad.getId());
			if (delete != null)
				assertEquals(reservaDAOjdbc.findAll().size(), size - 1);
			else
				assertEquals(reservaDAOjdbc.findAll().size(), size);
		}
	}



