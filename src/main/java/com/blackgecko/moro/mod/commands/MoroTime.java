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
		return "mtime <text> <text> <text> <text>";
	}

	@Override
	public List getAliases() {
		// TODO Auto-generated method stub
		return aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {

		
		
		if(args.length == 0) {
	    	
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "You have " + EnumChatFormatting.WHITE + "mororesttime" + EnumChatFormatting.GOLD + " secounds!"));
    		
    	}
		
		else if(args[0].equals("add")) {
    			
				
    			if(args.length == 3){
    				
    				
    				System.out.println("3 arguments");
    				/*
    				int mororesttime = 0;
    				int addtime = Integer.parseInt(args[2]);
    				
    				mororesttime = mororesttime  + addtime;
    				*/
    				
    			} else {
    				
    				
    				
    				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Missing Arguments!"));
    			}
    			
    		}	
    		
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
