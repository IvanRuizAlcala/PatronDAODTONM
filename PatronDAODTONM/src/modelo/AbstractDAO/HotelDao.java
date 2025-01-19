package modelo.AbstractDAO;

import modelo.Entity.Hotel;

public interface HotelDao extends GenericDAO<Hotel, Long> {
	public Hotel findByName(String name);
}
