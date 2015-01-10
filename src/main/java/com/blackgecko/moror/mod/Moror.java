package com.blackgecko.moror.mod;

import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Moror.MODID, version = Moror.VERSION)
public class Moror
{
    public static final String MODID = "moror";
    public static final String VERSION = "1.0";
    @Instance(MODID)
	public static Moror instance;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
