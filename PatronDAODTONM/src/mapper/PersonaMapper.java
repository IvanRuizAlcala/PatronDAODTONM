package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Entity.Persona;

public class PersonaMapper implements Mapper<ResultSet, Persona> {

	@Override
	public Persona map(ResultSet s) {
		try {
			while (s.next()) {
				Object object1 = s.getObject(1);
				Long object2 = (long) object1;
				Object object3 = s.getObject(2);
				String object4 = (String) object3;
				return new Persona(object2, object4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
