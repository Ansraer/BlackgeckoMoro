package com.blackgecko.moro.mod.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class MoroEventHandler {
	
	
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent event){

	}
	
	@SubscribeEvent
	public void livingDeath(LivingDeathEvent event){
		if(event.entity instanceof EntityPlayer){
			
			
			
		}
	}	

}
