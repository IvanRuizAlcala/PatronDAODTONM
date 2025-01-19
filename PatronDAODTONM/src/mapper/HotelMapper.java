package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Entity.Hotel;

public class HotelMapper implements Mapper<ResultSet, Hotel> {

	@Override
	public Hotel map(ResultSet s) {
		try {
			while (s.next()) {
				Object object1 = s.getObject(1);
				Long object2 = (long) object1;
				Object object3 = s.getObject(2);
				String object4 = (String) object3;
				return new Hotel(object2, object4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
