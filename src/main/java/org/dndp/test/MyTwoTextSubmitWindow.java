package org.dndp.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pivot.beans.BeanAdapter;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
import org.dndp.beans.Atrybut;

public class MyTwoTextSubmitWindow extends Window implements Bindable
{
	private static final String	host	= "http://localhost:8080/s/c";
	private static URL			url;
	private static Logger		log		= Logger.getLogger("applet");

	BoxPane						show;
	TextInput					first;
	TextInput					second;
	TextInput					one;
	TextInput					two;
	PushButton					submit;
	PushButton					dump;
	Atrybut						atr;

	static
	{
		try
		{
			log.addHandler(new FileHandler("applet.log"));
		}
		catch(SecurityException e)
		{
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "Wyjątek", e);
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "Wyjątek", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections
	 * .Map, java.net.URL, org.apache.pivot.util.Resources)
	 */
	public void initialize(Map<String, Object> namespace, final URL location,
			Resources resources)
	{
		submit = (PushButton)namespace.get("submit");
		dump = (PushButton)namespace.get("dump");

		one = (TextInput)namespace.get("one");
		two = (TextInput)namespace.get("two");
		show = (BoxPane)namespace.get("show");
		first = (TextInput)namespace.get("firstResponse");
		second = (TextInput)namespace.get("secondResponse");
		try
		{
			url = new URL(host);
		}
		catch(MalformedURLException e1)
		{
			log.log(Level.SEVERE, "Wyjątek", e1);
		}
		submit.getButtonPressListeners().add(new ButtonPressListener()
		{

			public void buttonPressed(Button button)
			{
				HttpURLConnection con = null;
				ObjectOutputStream stream = null;
				try
				{

					con = (HttpURLConnection)url.openConnection();
					con.setRequestMethod("POST");
					con.setDoOutput(true);
					stream = new ObjectOutputStream(con.getOutputStream());
					if(atr == null)
						atr = new Atrybut();
					show.store(atr);
					stream.writeObject(atr);
					con.connect();
					second.setText(con.getResponseMessage());
				}
				catch(IOException e)
				{
					log.log(Level.SEVERE, "Wyjątek", e);
				}
				finally
				{
					try
					{
						stream.close();
					}
					catch(IOException e)
					{
						log.log(Level.SEVERE, "Wyjątek", e);
					}
					con.disconnect();
				}
			}
		});
		dump.getButtonPressListeners().add(new ButtonPressListener()
		{
			public void buttonPressed(Button button)
			{
				ObjectInputStream in = null;
				HttpURLConnection con = null;

				try
				{
					con = (HttpURLConnection)url.openConnection();
					con.setDoInput(true);
					con.connect();
					in = new ObjectInputStream(con.getInputStream());
					atr = (Atrybut)in.readObject();
					show.load(new BeanAdapter(atr));
					in.close();

				}
				catch(IOException e)
				{
					log.log(Level.SEVERE, "Wyjątek", e);
				}
				catch(ClassNotFoundException e)
				{
					log.log(Level.SEVERE, "Wyjątek", e);
				}
				finally
				{
					con.disconnect();
				}
			}
		});

	}

	/**
	 * @return the submit
	 */
	public PushButton getSubmit()
	{
		return submit;
	}

	/**
	 * @param submit
	 *            the submit to set
	 */
	public void setSubmit(PushButton submit)
	{
		this.submit = submit;
	}

	/**
	 * @return the dump
	 */
	public PushButton getDump()
	{
		return dump;
	}

	/**
	 * @param dump
	 *            the dump to set
	 */
	public void setDump(PushButton dump)
	{
		this.dump = dump;
	}

}
