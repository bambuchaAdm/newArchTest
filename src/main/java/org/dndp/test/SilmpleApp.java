package org.dndp.test;
import javax.sql.rowset.WebRowSet;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.BrowserApplicationContext;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

public class SilmpleApp implements Application
{
	Window	window;

	public void startup(Display display, Map<String, String> properties)
			throws Exception
	{
		BXMLSerializer parser = new BXMLSerializer();
		window = (Window)parser.readObject(MyTwoTextSubmitWindow.class,
				"testOne.xml");
		window.setVisible(true);
		window.open(display);
	}

	public boolean shutdown(boolean optional) throws Exception
	{
		if(window != null)
			window.close();
		return false;
	}

	public void suspend() throws Exception
	{
		// TODO Auto-generated method stub

	}

	public void resume() throws Exception
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		DesktopApplicationContext.main(SilmpleApp.class, args);

	}
}
