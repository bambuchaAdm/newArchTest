package org.dndp.beans;

import java.io.Serializable;

public class Atrybut implements Serializable
{
	int	value;
	int	modifier;
	int	bonus;

	/**
	 * @return the value
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * @return the modifier
	 */
	public int getModifier()
	{
		return modifier;
	}

	/**
	 * @param modifier
	 *            the modifier to set
	 */
	public void setModifier(int modifier)
	{
		this.modifier = modifier;
	}

	/**
	 * @return the bonus
	 */
	public int getBonus()
	{
		return bonus;
	}

	/**
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(int bonus)
	{
		this.bonus = bonus;
	}

	public Atrybut(int value, int bonus)
	{
		super();
		this.value = value;
		this.bonus = bonus;
	}

	public Atrybut()
	{
		this.value = 0;
		this.bonus = 0;
	}
}
