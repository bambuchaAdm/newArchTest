import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dndp.beans.Atrybut;

public class HalloServlet extends HttpServlet
{
	private Atrybut	atr;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("text/html;charset=utf-8");
		resp.setStatus(HttpServletResponse.SC_OK);
		ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());
		if(atr != null)
			atr.setBonus(atr.getBonus() + 1);
		else
			atr = new Atrybut(2, 3);
		out.writeObject(atr);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		ObjectInputStream stream = new ObjectInputStream(req.getInputStream());

		try
		{
			this.atr = (Atrybut)stream.readObject();
			System.out.println(atr.getValue());
			System.out.println(atr.getBonus());
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println("Przyjąłem");
			resp.getWriter().flush();
		}
		catch(ClassNotFoundException e)
		{
			throw new IOException(e);
		}

	}
}
