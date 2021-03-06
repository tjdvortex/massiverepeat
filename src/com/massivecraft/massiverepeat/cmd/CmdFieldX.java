package com.massivecraft.massiverepeat.cmd;

import java.lang.reflect.Field;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;

public class CmdFieldX<T> extends RepeatCommand
{
	String fieldName;
	Class<T> classOfT;
	public CmdFieldX(String fieldName, Class<T> classOfT, Permission perm)
	{
		this.addAliases(fieldName);
		this.addRequiredArg("id");
		this.addOptionalArg("val", "*read*");
		this.addRequirements(new ReqHasPerm(perm.node));
		this.fieldName = fieldName;
		this.classOfT = classOfT;
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		if (this.argIsSet(1))
		{
			T val = this.argAs(1, classOfT);
			if (val == null) return;
			this.set(repeater, val);
		}
		
		this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" has "+fieldName+" = "+this.get(repeater)+".");
	}
	
	@SuppressWarnings("unchecked")
	public T get(Repeater repeater)
	{
		try
		{
			Field field = Repeater.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(repeater);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void set(Repeater repeater, Object val)
	{
		try
		{
			Field field = Repeater.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(repeater, val);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}