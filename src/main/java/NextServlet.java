import java.net.URL;

import javax.servlet.ServletException;

import org.apache.pivot.serialization.BinarySerializer;
import org.apache.pivot.serialization.Serializer;
import org.apache.pivot.web.QueryException;
import org.apache.pivot.web.Query.Method;
import org.apache.pivot.web.server.QueryServlet;
import org.dndp.beans.Atrybut;

public class NextServlet extends QueryServlet
{
	private Atrybut	atr;

	@Override
	public void init() throws ServletException
	{
		atr = new Atrybut(2, 5);
	}

	@Override
	protected Object doGet(Path path) throws QueryException
	{
		atr.setBonus(atr.getBonus() + 1);
		return atr;
	}

	@Override
	protected URL doPost(Path path, Object value) throws QueryException
	{
		atr = (Atrybut)value;
		return null;
	}

	@Override
	protected Serializer<?> createSerializer(Method arg0, Path arg1)
			throws QueryException
	{
		return new BinarySerializer();
	}

}
