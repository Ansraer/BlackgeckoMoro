package com.blackgecko.moro.mod.commands;

import java.util.ArrayList;
import java.util.List;

import com.blackgecko.moro.mod.Moro;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class MoroHelp implements ICommand{

	 private List aliases;
	  public MoroHelp()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("mhelp");
	    this.aliases.add("morohelp");
	    this.aliases.add("moroh");
	  }
	
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "/mhelp shows moro help";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "mhelp <text>";
	}

	@Override
	public List getAliases() {
		return aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "++++++++++Moro Help++++++++++"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Version: " + EnumChatFormatting.WHITE + Moro.VERSION));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "/moro                 --> show general information"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "/moro undeathban --> revive a death player"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "/moro time <text>  --> show general information"));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "++++++++++Moro Help++++++++++"));
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
