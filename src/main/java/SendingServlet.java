import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendingServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html;charset=utf-8");
		ServletOutputStream out = resp.getOutputStream();
		out.println("<html>");
		out.println("<head>");
		out.println("<script xmlns=\"\" type=\"text/javascript\" src=\"http://localhost:8080/deployJava.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<script type=\"text/javascript\">");
		out.println("var attributes = { codebase: 'http://localhost:8080/', code:'org.apache.pivot.wtk.BrowserApplicationContext$HostApplet', width:'500', height:'500', archive : 'pivot-core-2.0.jar,pivot-wtk-2.0.jar,pivot-wtk-terra-2.0.jar,newArchTest-1.jar'}");
		out.println("var parameters ={ application_class_name:'org.dndp.test.SilmpleApp', java_arguments: '-Dsun.awt.noerasebackground=true -Dsun.awt.erasebackgroundonresize=true '}");
		out.println("deployJava.runApplet(attributes, parameters, \"1.6\");");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
	}
}
