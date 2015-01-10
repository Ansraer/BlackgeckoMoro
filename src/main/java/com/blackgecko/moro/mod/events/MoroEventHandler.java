package com.blackgecko.moro.mod.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
	
	int timeDay = 1;//Time every day in minutes
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
			
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Moro   Version " +Moro.VERSION));
			


		}
	}	
	
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent event){
			EntityPlayer player = event.player;
			MoroPlayer moroPlayer = MoroPlayer.get(player);
			
			if(serverHasTimeLimit){
			
				if(moroPlayer.getTimeLimit()){
					moroPlayer.addTime(-1);
					
					if(moroPlayer.getTime()<=0){
						//((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("Your time to play for today is over...");
					}

					//System.out.println("Player " +player.getName() + " has " + moroPlayer.getTime()/20 + " secs left.");
					
					
				}
			}
			
		
	}
	
	@SubscribeEvent
	public void livingDeath(LivingDeathEvent event){
		if(event.entity instanceof EntityPlayer){
			
			
			
		}
	}

}
