package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import mapper.HotelAdapter;
import mapper.HotelMapper;
import mapper.PersonaMapper;
import modelo.AbstractDAO.HotelDao;
import modelo.Entity.Hotel;
import modelo.acceso.AccessJdbc;

public class HotelDAOJdbc implements HotelDao {
	private AccessJdbc accessJdbc;
	private HotelMapper hotelMapper;

	public HotelDAOJdbc(AccessJdbc accessJdbc) {
		super();
		this.accessJdbc = accessJdbc;
		hotelMapper=new HotelMapper();
	}

	@Override
	public void create(Hotel entidad) {
		String sql = "INSERT INTO "+accessJdbc.getBBDD()+".hotel(id,nombre) VALUES (?,?)";
		accessJdbc.update(sql,new HotelAdapter(entidad));
		
	}

	@Override
	public Collection<Hotel> findAll() {
		ResultSet conjuntoResultados = accessJdbc.execute("SELECT * FROM hotel");
		Collection<Hotel> hoteles = new ArrayList<>();
		try {
			while (conjuntoResultados.next()) {
				hoteles.add(new HotelMapper().map(conjuntoResultados));
			} 
		} catch (Exception e) {
			return null;
		}
		return hoteles;
	}

	@Override
	public Hotel findById(Long id) {
		String sql = "SELECT * FROM "+accessJdbc.getBBDD()+".hotel WHERE id LIKE "+id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		return hotelMapper.map(conjuntoResultados);
	}

	

	@Override
	public Hotel findByName(String name) {
		String sql = "select * from "+accessJdbc.getBBDD()+".hotel where nombre LIKE '"+name+"'";
		ResultSet execute = accessJdbc.execute(sql);
		return hotelMapper.map(execute);
	}
	@Override
	public Hotel delete(Long id) {
		Hotel byId = findById(id);
		if(byId!=null) {
			String sql = "delete from "+accessJdbc.getBBDD()+".hotel where id = "+String.valueOf(id);
			accessJdbc.executeUpdate(sql);
			return byId;
		}
		return null;
	}

}
