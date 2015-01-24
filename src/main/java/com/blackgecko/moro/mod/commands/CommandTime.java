package com.blackgecko.moro.mod.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.Constants.NBT;

import com.blackgecko.moro.mod.Moro;
import com.blackgecko.moro.mod.playerdata.MoroPlayer;
import com.blackgecko.moro.mod.playerdata.WorldDataMoro;
import com.blackgecko.moro.mod.proxies.CommonProxy;

public class CommandTime implements ICommand{
	
	private List aliases;
	  public CommandTime()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("mtime");
	    this.aliases.add("mtime add");
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
	    	
			
			long minute = TimeUnit.SECONDS.toMinutes(MoroPlayer.get(((EntityPlayer)sender.getCommandSenderEntity())).getTime()/20);
	        long second = TimeUnit.SECONDS.toSeconds(MoroPlayer.get(((EntityPlayer)sender.getCommandSenderEntity())).getTime()/20) - TimeUnit.MINUTES.toSeconds(minute);

			
    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "You have " + EnumChatFormatting.GOLD + minute + EnumChatFormatting.WHITE + " minutes and " +EnumChatFormatting.GOLD+ second + EnumChatFormatting.WHITE + " seconds left."));

    	}
		
		else if(args[0].equals("add")) {
    			
			boolean permissions=false;
			if(sender instanceof EntityPlayer){
				for(int i =0; i<MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames().length; i++){
					if(((EntityPlayer)sender).getName().equals(MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames()[i])){
						permissions=true;
					}
				}
			} else {
				permissions=true;
			}
					

				if(args.length == 3 && permissions){

    				int time=0;
    				try {
    				      time = Integer.parseInt(args[1]);
    				} catch (NumberFormatException e) {
    					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + args[1] +  "is no valid number."));
    				}
    				

    				boolean online = false;
    				for(int i=0; i<MinecraftServer.getServer().worldServers[0].playerEntities.size(); i++){
    				if(((EntityPlayer)MinecraftServer.getServer().worldServers[0].playerEntities.get(i)).getName().equals(args[2])){
    					online = true;//Player is online
    				}
    				}
    				
    				if(online){
    					
    					EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(args[2]);
    					MoroPlayer.get(player).addTime(time*20*60);
    					
    					
    					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Added " +EnumChatFormatting.GOLD + time +EnumChatFormatting.WHITE + " minutes to " +EnumChatFormatting.GOLD + args[2] +EnumChatFormatting.WHITE + "."));
    					player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "You got " +EnumChatFormatting.GOLD + time +EnumChatFormatting.WHITE + " minutes from " +EnumChatFormatting.GOLD + sender.getName() +EnumChatFormatting.WHITE + "."));
    				} else {
    				
    					String name = args[2];
    		    		WorldDataMoro data = WorldDataMoro.forWorld(MinecraftServer.getServer().getEntityWorld());
    		    		NBTTagList tagList =data.getData().getTagList("MoroTimeAdd",  NBT.TAG_STRING);
    		    		
    		    		
    			    		if(tagList==null){
    			    			tagList = new NBTTagList();
    			    		}
    			    		
    			    		
    			    		boolean alreadyInList = false;
    			    		for(int i = 0; i < tagList.tagCount(); i++){
    			    			String s = tagList.getStringTagAt(i);
    			    			if(s.equalsIgnoreCase(name)){
    				    			alreadyInList = true;
    			    		  }
    			    		}
    			    		if(!alreadyInList){

    			    			tagList.appendTag(new NBTTagString(name));
    			    		}
    		    		
    		    		data.getData().setTag("MoroTimeAdd", tagList);
    		    		data.markDirty();
    					

    					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "The player " + args[2] +  " could not be found. Should he already be out of time he should be able to join again."));
    				}
    				
    				
    				
    			} else {
    				if(permissions){
    					sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Missing Arguments!"));
    				} else {
	    				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "You don't have the required permissions to perform this command."));
    				}
    				
    				
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
