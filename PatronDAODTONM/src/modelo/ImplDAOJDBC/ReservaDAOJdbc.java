package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import mapper.HotelMapper;
import mapper.PersonaMapper;
import mapper.ReservaAdapter;
import mapper.ReservaMapper;
import modelo.AbstractDAO.ReservaDao;
import modelo.Entity.Hotel;
import modelo.Entity.Reserva;
import modelo.acceso.AccessJdbc;

public class ReservaDAOJdbc implements ReservaDao {
	
	private AccessJdbc accessJdbc;
	private ReservaMapper reservaMapper;
	
	public ReservaDAOJdbc(AccessJdbc accessJdbc) {
		super();
		this.accessJdbc = accessJdbc;
		reservaMapper=new ReservaMapper();
	}
	
	@Override
	public void create(Reserva entidad) {
		String sql = "INSERT INTO "+accessJdbc.getBBDD()+".reserva(id,idPersona,idHotel,fecha) VALUES (?,?,?,?)";
		accessJdbc.update(sql,new ReservaAdapter(entidad));
		
	}

	@Override
	public Collection<Reserva> findAll() {
		ResultSet conjuntoResultados = accessJdbc.execute("SELECT * FROM reserva");
		Collection<Reserva> reservas = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				reservas.add(new ReservaMapper().map(conjuntoResultados));
			} 
		} catch (Exception e) {
			return null;
		}
		return reservas;
	}

	@Override
	public Reserva findById(Long id) {
		String sql = "SELECT * FROM "+accessJdbc.getBBDD()+".reserva WHERE id LIKE "+id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		return reservaMapper.map(conjuntoResultados);
	}
	

	@Override
	public Reserva delete(Long id) {
		Reserva byReserva = findById(id);
		if(byReserva!=null) {
			String sql = "delete from "+accessJdbc.getBBDD()+".reserva where id = "+String.valueOf(id);
			accessJdbc.executeUpdate(sql);
			return byReserva;
		}
		return null;
	}

	@Override
	public Reserva find(Reserva reserva) {
		String sql = extracted(reserva);
		ResultSet execute = accessJdbc.execute(sql);
		return reservaMapper.map(execute);
	}

	private String extracted(Reserva reserva) {
		String sql = "select * from "+accessJdbc.getBBDD()+".reserva where idPersona LIKE '"+reserva.getIdPersona()+ "' " + 
														   "AND idHotel LIKE '"+reserva.getIdHotel()+ "' " +
														   "AND fecha LIKE '"+reserva.getFecha()+ "' ";
		return sql;
	}
}
