package com.blackgecko.moro.mod.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import com.blackgecko.moro.mod.Moro;
import com.blackgecko.moro.mod.playerdata.MoroPlayer;
import com.blackgecko.moro.mod.proxies.CommonProxy;

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

		
		
		if(args.length == 0 && (sender.getCommandSenderEntity() instanceof EntityPlayer)) {
	    	
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "You have " + EnumChatFormatting.WHITE + MoroPlayer.get(((EntityPlayer)sender.getCommandSenderEntity())).getTime()/20 + EnumChatFormatting.GOLD + " secounds!"));
    		
    	}
		
		else if(args[0].equals("add")) {
    			
				if(args.length == 3){

					
    				int time=0;
    				try {
    				      time = Integer.parseInt(args[1]);
    				} catch (NumberFormatException e) {
    					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + args[1] +  "is no valid number."));
    				}
    				

    				boolean online = false;
    				for(int i=0; i<MinecraftServer.getServer().worldServers[0].playerEntities.size(); i++){
    				if(((EntityPlayer)MinecraftServer.getServer().worldServers[0].playerEntities.get(i)).getName().equalsIgnoreCase(args[2])){
    					online = true;//Player is online
    				}
    				}
    				
    				if(online){
    					
    					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(args[2]);
    					System.out.println("Test "+player.getName());
    					MoroPlayer.get(player).addTime(time*20*60);
    				} else {
    					
    					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + args[1] +  "Player is not online."));
    				}
    				
    				
    				
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
