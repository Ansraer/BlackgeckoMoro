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
import net.minecraft.util.IChatComponent;

public class CommandInfo implements ICommand {

	
	
	  private List aliases;
	  public CommandInfo()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("moro");
	  }
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "moro";
	}

	@Override
	public List getAliases() {
		return aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
	    if(args.length == 0)
	    {
	    	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Moro   Version " +Moro.VERSION));
		    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "written by Jacky2611 and AruIke."));
		    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "use /mhelp to get a list of commands"));
		    return;
	    }
	    if(args.length == 1){	
	    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "INVALID ARGUMENT(S)"));	    	
	    }


	    
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
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
