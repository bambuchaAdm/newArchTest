import org.eclipse.jetty.server.Server;
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
		rHandler.setResourceBase(".");
		rHandler.setWelcomeFiles(new String[] { "index.html" });
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/s");
		HandlerList list = new HandlerList();
		list.addHandler(rHandler);
		list.addHandler(context);
		server.setHandler(list);

		context.addServlet(HalloServlet.class, "/c");
		context.addServlet(NextServlet.class, "/n");

		server.start();
		server.join();
	}
}
