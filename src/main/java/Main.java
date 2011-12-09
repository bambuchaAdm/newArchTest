import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main
{

	public static void main(String[] args) throws Exception
	{
		Server server = new Server(8080);
		ResourceHandler rHandler = new ResourceHandler();
		rHandler.setDirectoriesListed(true);
		rHandler.setResourceBase("target/http");
		rHandler.setWelcomeFiles(new String[] { "index.html" });
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/servlets");
		HandlerList list = new HandlerList();
		list.addHandler(rHandler);
		list.addHandler(context);
		server.setHandler(list);

		context.addServlet(SendingServlet.class, "/applet");
		context.addServlet(HalloServlet.class, "/hi");

		server.start();
		server.join();
	}
}
