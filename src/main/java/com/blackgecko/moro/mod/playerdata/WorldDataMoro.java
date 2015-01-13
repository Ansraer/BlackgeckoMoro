package com.blackgecko.moro.mod.playerdata;

import com.blackgecko.moro.mod.Moro;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class WorldDataMoro extends WorldSavedData {

	   final static String key = Moro.MODID;
	   
	   public static WorldDataMoro forWorld(World world) {
	      MapStorage storage = world.getMapStorage();
	      WorldDataMoro result = (WorldDataMoro)storage.loadData(WorldDataMoro.class, key);
	      if (result == null) {
	         result = new WorldDataMoro(key);
	         storage.setData(key, result);
	      }
	      return result;
	   }
	   
	   private NBTTagCompound data = new NBTTagCompound();

	   public WorldDataMoro(String tagName) {
	       super(tagName);
	   }

	   @Override
	   public void readFromNBT(NBTTagCompound compound) {
	  	 data = compound.getCompoundTag(key);
	   }

	   @Override
	   public void writeToNBT(NBTTagCompound compound) {
	       compound.setTag(key, data);
	   }

	   public NBTTagCompound getData() {
	       return data;
	   }
	}