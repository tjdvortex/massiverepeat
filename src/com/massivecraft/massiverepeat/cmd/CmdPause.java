package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;

public class CmdPause extends RepeatCommand
{
	public CmdPause()
	{
		this.addAliases("pause","hold");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.PAUSE.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		boolean result = repeater.pause();
		
		if (result)
		{
			this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" paused.");
		}
		else
		{
			this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" is already paused.");
		}
	}
}