package com.blackgecko.moro.mod.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.blackgecko.moro.mod.Moro;
import com.mojang.authlib.properties.PropertyMap;

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
		return "moro";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/moro Displays information for the Moro Mod";
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
		    
		    System.out.println(MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername("Jacky2611").getProperties().get("remainingTime").toString());
		    
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
