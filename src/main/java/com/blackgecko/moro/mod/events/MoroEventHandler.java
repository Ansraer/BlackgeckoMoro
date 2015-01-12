package com.blackgecko.moro.mod.events;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import com.blackgecko.moro.mod.Moro;
import com.blackgecko.moro.mod.playerdata.MoroPlayer;

public class MoroEventHandler {
	
	
	
	
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	/*
	Be sure to check if the entity being constructed is the correct type for the extended properties you're about to add! The null check may not be necessary - I only use it to make sure properties are only registered once per entity
	*/
	if (event.entity instanceof EntityPlayer && MoroPlayer.get((EntityPlayer) event.entity) == null)
		MoroPlayer.register((EntityPlayer) event.entity);
		// That will call the constructor as well as cause the init() method
		// to be called automatically

	// If you didn't make the two convenient methods from earlier, your code would be
	// much uglier:
	if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(MoroPlayer.EXT_PROP_NAME) == null)
	event.entity.registerExtendedProperties(MoroPlayer.EXT_PROP_NAME, new MoroPlayer((EntityPlayer) event.entity));
	}
	
	
	
	
	//20 ticks a second
	
	boolean serverHasTimeLimit = true;
	
	
	/*Tags:
	 * remainingTime
	 * 
	 * hasTimeLimit
	 * 
	 * 
	 */
	
	
	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			MoroPlayer moroPlayer = MoroPlayer.get(player);
			
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Moro   Version " +Moro.VERSION));
			

			NBTTagCompound playerData = Moro.proxy.getEntityData(((EntityPlayer) event.entity).getName());
			// make sure the compound isn't null
			if (playerData != null) {
			// then load the data back into the player's IExtendedEntityProperties
			System.out.println("Player Joined "+ event.entity.getName());
			moroPlayer.loadNBTData(playerData);
			
			}
			
			
				if(Moro.timeLimitEnabled){	
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				
				try {
					date = dateFormat.parse(dateFormat.format(date));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				Date lastPlayed = null;
				Date bannedUntil = null;
	
			 
				try {
					if(moroPlayer.getLastPlayedDate()==null || moroPlayer.getLastPlayedDate().equals("")){
						moroPlayer.setLastPlayedDate(dateFormat.format(date));
					}
					if(moroPlayer.getBannedUntilDate()==null || moroPlayer.getBannedUntilDate().equals("")){
						moroPlayer.setBannedUntilDate(dateFormat.format(date));
					}
					
					lastPlayed = dateFormat.parse(moroPlayer.getLastPlayedDate());
					bannedUntil = dateFormat.parse(moroPlayer.getBannedUntilDate());
				
					
					
					if(bannedUntil.getTime()>date.getTime()){
						
						dateFormat = new SimpleDateFormat("dd.MM.yyyy");
						((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("You are banned until " + dateFormat.format(date) + ".");
						event.setCanceled(true);
					}
					
					
					
					
					//long diff = dateObj2.getTime() - dateObj1.getTime();
					
					long diffDaysl =date.getTime() - lastPlayed.getTime();
					int diffDays = (int) (diffDaysl / (24 * 60 * 60 * 1000));
					
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "" + diffDays +" Have passed since you logged in the last time. You got " + diffDays*Moro.timeDay*20*60 + "minutes."));
				
				} catch (Exception e){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "CRASHED " +e));
				}
				
				
				
				moroPlayer.setLastPlayedDate(dateFormat.format(date));
				
			
				
				
				
			}
			

		}
	}	
	
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent event){
			EntityPlayer player = event.player;
			MoroPlayer moroPlayer = MoroPlayer.get(player);

			
			
			if(Moro.timeLimitEnabled){
			
				if(moroPlayer.getTimeLimit()){
					moroPlayer.addTime(-1);
					
					if(moroPlayer.getTime()<=0){
						((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("Your time to play for today is over...");
					}

					//System.out.println("Player " +player.getName() + " has " + moroPlayer.getTime()/20 + " secs left.");
					
					
				}
			}
			
		
	}
	
	@SubscribeEvent
	public void onlivingDeath(LivingDeathEvent event){
		// we only want to save data for players (most likely, anyway)
		
		
		
		
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer)event.entity);
			MoroPlayer moroPlayer = MoroPlayer.get(player);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, Moro.banTimeOnDeath); //minus number would decrement the days
	        Date unbannedIn = cal.getTime();
			
			moroPlayer.setBannedUntilDate(dateFormat.format(unbannedIn));
			
			dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("SHIT! You died. You are banned until " + dateFormat.format(unbannedIn) + ".");
				
				
			// NOTE: See step 6 for a way to do this all in one line!!!
			// create a new NBT Tag Compound to store the IExtendedEntityProperties data
			NBTTagCompound playerData = new NBTTagCompound();
			// write the data to the new compound
			moroPlayer.get((EntityPlayer)event.entity).saveNBTData(playerData);
			// and store it in our proxy
			Moro.proxy.storeEntityData(((EntityPlayer) event.entity).getName(), playerData);
			// call our handy static one-liner to save custom data to the proxy
			//MoroPlayer.saveNBTData((EntityPlayer) event.entity);
		}
	}
}