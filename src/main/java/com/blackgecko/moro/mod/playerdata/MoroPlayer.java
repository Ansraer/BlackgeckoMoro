package com.blackgecko.moro.mod.playerdata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.blackgecko.moro.mod.Moro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MoroPlayer implements IExtendedEntityProperties {
	/*
	 * Here I create a constant EXT_PROP_NAME for this class of properties. You
	 * need a unique name for every instance of IExtendedEntityProperties you
	 * make, and doing it at the top of each class as a constant makes it very
	 * easy to organize and avoid typos. It's easiest to keep the same constant
	 * name in every class, as it will be distinguished by the class name:
	 * ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME
	 * 
	 * Note that a single entity can have multiple extended properties, so each
	 * property should have a unique name. Try to come up with something more
	 * unique than the tutorial example.
	 */
	public final static String EXT_PROP_NAME = "MoroPlayer";

	// I always include the entity to which the properties belong for easy
	// access
	// It's final because we won't be changing which player it is
	private final EntityPlayer player;

	// Declare other variables you want to add here

	// We're adding mana to the player, so we'll need current and max mana
	private int remainingTime;

	private boolean hasTimeLimit;
	
	private int lastPlayedDate;
	
	private int bannedUntilDate;
	/*
	 * The default constructor takes no arguments, but I put in the Entity so I
	 * can initialize the above variable 'player'
	 * 
	 * Also, it's best to initialize any other variables you may have added,
	 * just like in any constructor.
	 */
	public MoroPlayer(EntityPlayer player) {
		this.player = player;
		// Start with max mana. Every player starts with the same amount.
		this.remainingTime= 20*60*Moro.timeDay;
		this.hasTimeLimit=true;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		
		//this.bannedUntilDate=dateFormat.format(date);
		//this.lastPlayedDate=dateFormat.format(date);
	}

	/**
	 * Used to register these extended properties for the player during
	 * EntityConstructing event This method is for convenience only; it will
	 * make your code look nicer
	 */
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(MoroPlayer.EXT_PROP_NAME,new MoroPlayer(player));
	}

	/**
	 * Returns ExtendedPlayer properties for player This method is for
	 * convenience only; it will make your code look nicer
	 */
	public static final MoroPlayer get(EntityPlayer player) {
		return (MoroPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// We need to create a new tag compound that will save everything for
		// our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();

		// We only have 2 variables currently; save them both to the new tag
		properties.setInteger("remainingTime", this.remainingTime);
		properties.setBoolean("hasTimeLimit", this.hasTimeLimit);
		properties.setInteger("lastPlayedDate", this.lastPlayedDate);
		properties.setInteger("bannedUntilDate", this.bannedUntilDate);
		/*
		 * Now add our custom tag to the player's tag with a unique name (our
		 * property's name). This will allow you to save multiple types of
		 * properties and distinguish between them. If you only have one type,
		 * it isn't as important, but it will still avoid conflicts between your
		 * tag names and vanilla tag names. For instance, if you add some
		 * "Items" tag, that will conflict with vanilla. Not good. So just use a
		 * unique tag name.
		 */
		compound.setTag(EXT_PROP_NAME, properties);
	}

	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// Here we fetch the unique tag compound we set for this class of
		// Extended Properties
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		// Get our data from the custom tag compound
		
		this.hasTimeLimit = properties.getBoolean("hasTimeLimit");
		this.remainingTime = properties.getInteger("remainingTime");
		this.lastPlayedDate = properties.getInteger("lastPlayedDate");
		this.bannedUntilDate = properties.getInteger("bannedUntilDate");
		
	}

	/*
	 * I personally have yet to find a use for this method. If you know of any,
	 * please let me know and I'll add it in!
	 */
	@Override
	public void init(Entity entity, World world) {
	}

	/*
	 * That's it for the IExtendedEntityProperties methods, but we need to add a
	 * few of our own in order to interact with our new variables. For now,
	 * let's make one method to consume mana and one to replenish it.
	 */

	public void switchTimeLimit(boolean b){
		this.hasTimeLimit=b;
	}
	public boolean getTimeLimit(){
		return this.hasTimeLimit;
	}
	
	public void setTime(int time){
		this.remainingTime=time;
	}
	public void addTime(int time){
		this.remainingTime+=time;
	}
	public int getTime(){
		return this.remainingTime;
	}

	public String getLastPlayedDate() {
		return lastPlayedDate+"";
	}

	public void setLastPlayedDate(int lastPlayedDate) {
		this.lastPlayedDate = lastPlayedDate;
	}

	public String getBannedUntilDate() {
		return bannedUntilDate+"";
	}

	public void setBannedUntilDate(int bannedUntilDate) {
		this.bannedUntilDate = bannedUntilDate;
	}

}