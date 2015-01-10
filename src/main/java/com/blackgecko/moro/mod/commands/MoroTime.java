package com.blackgecko.moro.mod.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class MoroTime implements ICommand{
	
	private List aliases;
	  public MoroTime()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("mtime");
	  }
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "/mtime shows the rest of the time from a player";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "mtime";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {

		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "You have " + EnumChatFormatting.WHITE + "mororesttime" + EnumChatFormatting.GOLD + " secounds!"));
		
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
