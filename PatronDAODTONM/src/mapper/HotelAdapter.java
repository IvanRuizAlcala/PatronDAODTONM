package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Entity.Hotel;

public class HotelAdapter implements Adapter {
	private Hotel hotel;
	
	public HotelAdapter(Hotel hotel) {
		super();
		this.hotel = hotel;
	}

	@Override
	public void adapt(PreparedStatement instruccion) throws SQLException {
		// Establecer los valores de los par�metros en la sentencia SQL
				instruccion.setLong(1, hotel.getId());
				instruccion.setString(2,hotel.getNombre());

	}

	

}
