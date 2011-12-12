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
import org.apache.pivot.serialization.BinarySerializer;
import org.apache.pivot.serialization.Serializer;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.web.GetQuery;
import org.apache.pivot.web.PostQuery;
import org.apache.pivot.web.QueryException;
import org.apache.pivot.wtk.Alert;
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
				PostQuery post = new PostQuery("localhost", 8080, "/s/n", false);
				post.setSerializer(new BinarySerializer()); // Domyślny to JSON
				show.store(new BeanAdapter(atr));
				post.setValue(atr);
				try
				{
					post.execute(); // Wywołanie synchroniczne
				}
				catch(QueryException e)
				{
					Alert.alert(
							"Nie powiodła się publickacja\n"
									+ e.getLocalizedMessage(),
							MyTwoTextSubmitWindow.this);
				}

			}
		});
		dump.getButtonPressListeners().add(new ButtonPressListener()
		{
			public void buttonPressed(Button button)
			{
				GetQuery get = new GetQuery("localhost", 8080, "/s/n", false);
				get.setSerializer(new BinarySerializer()); // Jak poprzednio
				get.execute(new TaskListener<Object>()
				{
					// Wywołanie asynchroniczne
					@Override
					public void executeFailed(Task<Object> task)
					{
						Alert.alert("Nie można pobrać danych\n"
								+ task.getFault().getMessage(),
								MyTwoTextSubmitWindow.this);
					}

					@Override
					public void taskExecuted(Task<Object> task)
					{
						atr = (Atrybut)task.getResult();
						show.load(new BeanAdapter(atr));
					}
				});
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
