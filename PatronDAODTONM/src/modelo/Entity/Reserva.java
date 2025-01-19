package modelo.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long idPersona;
	private Long idHotel;
	private LocalDate fecha;
	public Reserva(Long id,Long idPersona, Long idHotel, LocalDate fecha) {
		super();
		this.id = id;
		this.idPersona = idPersona;
		this.idHotel = idHotel;
		this.fecha = fecha;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fecha, idHotel, idPersona);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(idHotel, other.idHotel)
				&& Objects.equals(idPersona, other.idPersona);
	}
	@Override
	public String toString() {
		return "Reservas [idPersona=" + idPersona + ", idHotel=" + idHotel + ", fecha=" + fecha + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
