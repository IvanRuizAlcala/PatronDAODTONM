package mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Adapter {
	public void adapt(PreparedStatement instruccion) throws SQLException;
}
