package com.blackgecko.moro.mod.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.storage.SaveHandler;

import com.blackgecko.moro.mod.Moro;
import com.mojang.authlib.properties.PropertyMap;

public class CommandInfo implements ICommand {

	
	
	  private List aliases;
	  public CommandInfo()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("moro");
	    this.aliases.add("moro revive");
	  }
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "/moro Displays information for the Moro Mod";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "moro <text> <text> <text> <text>";
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
	    else if(args.length > 0){
	    	System.out.println("ARG 0 " + args[0]);
	    	if(args[0].equals("revive")) {
	    		System.out.println("Args geht 1 and args length " +args.length);
	    		
	    		
	    		if(args.length == 2){
	    			
	    			
	    			SaveHandler saveHandler = (SaveHandler)MinecraftServer.getServer().worldServers[0].getSaveHandler();
	    			String username = "someRandomUsername";
	    			NBTTagCompound playerNbt = saveHandler.getPlayerNBTManager().readPlayerData(MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(args[1]));
	    			// modify playerNbt
	    			try {
	    			    File playersDirectory = new File(saveHandler.getWorldDirectory(), "players");

	    			    File temp = new File(playersDirectory, username + ".dat.tmp");
	    			    File playerFile = new File(playersDirectory, username + ".dat");
	    			    CompressedStreamTools.writeCompressed(playerNbt, new FileOutputStream(temp));

	    			   if (playerFile.exists()) {
	    			        playerFile.delete();
	    			    }
	    			    temp.renameTo(playerFile);
	    			} catch (Exception e) {
	    			    System.out.println("Failed to save player data for " + username);
	    			}
	    			
	    			
	    			
	    			
	    			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(args[1]);
	    			
	    			player.setDead();
	    			
	    			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "The player " + player.getName() + " can join the game again!"));
	    			//if(!found){
	    			//	sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "The player is not online or does not exist"));
	    			//}
	    			
	    			
	    		}
	    		
	    		
	    	} else if(args[0].equals("time")) {
	    		
	    		if(args[1].equals("")){
	    			
	    			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Missing Arguments!"));
	    			
	    		} else if(args[1].equals("add")){
	    			
	    			if(args[2].equals("")){
	    				
	    				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Missing Arguments!"));
	    				
	    			} else {
	    				
	    				int mororesttime = 0;
	    				int addtime = Integer.parseInt(args[2]);
	    				
	    				mororesttime = mororesttime  + addtime;
	    				
	    			}
	    			
	    		}	
	    		
	    	}
	    	
	    	
	    }

	    
	    
	}

	private EntityPlayerMP getName(String string) {
		// TODO Auto-generated method stub
		return null;
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
