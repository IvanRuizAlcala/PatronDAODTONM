package modelo.AbstractDAO;

import modelo.Entity.Reserva;

public interface ReservaDao extends GenericDAO<Reserva, Long> {
	public Reserva find(Reserva reserva);
}
