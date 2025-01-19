package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import modelo.Entity.Reserva;

public class ReservaMapper implements Mapper<ResultSet, Reserva> {

	@Override
	public Reserva map(ResultSet s) {
		try {
			while (s.next()) {
				Long id = s.getLong("id");
				Long idPersona = s.getLong("idPersona");
				Long idHotel = s.getLong("idHotel"); 
				LocalDate fecha = s.getDate("fecha").toLocalDate(); 
				return new Reserva(id,idPersona, idHotel,fecha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
