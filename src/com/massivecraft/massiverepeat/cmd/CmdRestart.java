package com.massivecraft.massiverepeat.cmd;

import com.massivecraft.massiverepeat.Permission;
import com.massivecraft.massiverepeat.Repeater;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;

public class CmdRestart extends RepeatCommand
{
	public CmdRestart()
	{
		this.addAliases("restart","replay","run");
		this.addRequiredArg("id");
		this.addRequirements(new ReqHasPerm(Permission.RESTART.node));
	}

	@Override
	public void perform()
	{
		Repeater repeater = this.argAs(0, Repeater.class);
		if (repeater == null) return;
		
		repeater.restart();
		
		this.msg("<i>Repeater \"<h>"+repeater.getId()+"<i>\" was restarted.");
	}
}