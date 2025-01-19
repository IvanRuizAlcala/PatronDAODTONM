package mapper;

import java.sql.SQLException;

public interface Mapper<S, T> {
	public T map(S s);
}
