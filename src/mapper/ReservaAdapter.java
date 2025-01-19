package mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Entity.Reserva;

public class ReservaAdapter implements Adapter {
	private Reserva reserva;
	
	public ReservaAdapter(Reserva reserva) {
		super();
		this.reserva = reserva;
	}
	
	@Override
	public void adapt(PreparedStatement instruccion) throws SQLException {
		// Establecer los valores de los par�metros en la sentencia SQL
		instruccion.setLong(1, reserva.getId());
		instruccion.setLong(2, reserva.getIdPersona());
		instruccion.setLong(3, reserva.getIdHotel());
		instruccion.setDate(4, Date.valueOf(reserva.getFecha()));
	}



}
