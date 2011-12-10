package org.dndp.test;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

public class MyTwoTextSubmitWindow extends Window implements Bindable
{
	// @BXML
	PushButton	submit;
	// @BXML
	PushButton	dump;

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

		submit.getButtonPressListeners().add(new ButtonPressListener()
		{
			private String	loc	= location.toString();

			public void buttonPressed(Button button)
			{
				Alert.alert("Submit has cliced and " + loc,
						MyTwoTextSubmitWindow.this);
			}
		});
		dump.getButtonPressListeners().add(new ButtonPressListener()
		{
			public void buttonPressed(Button button)
			{
				Alert.alert("BXML", MyTwoTextSubmitWindow.this);
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
