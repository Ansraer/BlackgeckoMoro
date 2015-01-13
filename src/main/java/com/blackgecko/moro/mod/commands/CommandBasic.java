package com.blackgecko.moro.mod.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.Constants.NBT;

import com.blackgecko.moro.mod.Moro;
import com.blackgecko.moro.mod.playerdata.WorldDataMoro;
import com.sun.medialib.mlib.Constants;

public class CommandBasic implements ICommand {

	
	
	  private List aliases;
	  public CommandBasic()
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
	    	if(args[0].equals("revive")) {
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
	    		
	    		if(args.length == 2 && permissions){
	    			
	    		/*
	    		 * // using your class this time; pretend you are in some method that has access to a world object
					someMethod(World world) {
					// loads or creates the necessary data
					WorldDataFH data = WorldDataFH.forWorld(world);
					data.getData().setInteger("yourInt", someValue);
					data.markDirty();
					
					if (data.getData().getInteger("yourInt") > 10) {
					// do something
					}
}
	    		 * 
	    		 */
	    		
	    		String name = args[1]; 
	    		WorldDataMoro data = WorldDataMoro.forWorld(MinecraftServer.getServer().getEntityWorld());
	    		NBTTagList tagList =data.getData().getTagList("MoroRevived",  NBT.TAG_COMPOUND);
	    		
	    		
		    		if(tagList==null){
		    			tagList = new NBTTagList();
		    		}
		    		
		    		
		    		boolean alreadyInList = false;
		    		for(int i = 0; i < tagList.tagCount(); i++){
		    			String s = tagList.getStringTagAt(i);
		    			if(s.equals(name)){
			    			alreadyInList = true;
		    		  }
		    		}
		    		if(!alreadyInList){
		    			tagList.appendTag(new NBTTagString(name));
		    		}
	    		
	    		
	    		
	    		data.getData().setTag("MoroRevived", tagList);
	    		data.markDirty();
	    			
	    		
	    			
	    			
	    			
	    			
	    		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "The player " + args[1] + " can join the game again!"));
	    			
	    			
	    		} else if(!permissions){
    				sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "You don't have the required permissions to perform this command."));

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
