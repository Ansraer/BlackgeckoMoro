package com.blackgecko.moro.mod;

import com.blackgecko.moro.mod.commands.CommandInfo;
import com.blackgecko.moro.mod.commands.MoroHelp;
import com.blackgecko.moro.mod.events.MoroEventHandler;

import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Moro.MODID, version = Moro.VERSION, acceptableRemoteVersions = "*")
public class Moro
{
    public static final String MODID = "moro";
    public static final String VERSION = "1.0";
    @Instance(MODID)
	public static Moro instance;
    
    
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
      event.registerServerCommand(new CommandInfo());
      event.registerServerCommand(new MoroHelp());
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		// some example code
        System.out.println("preinit");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("init");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		// some example code
        System.out.println("postinit");
    }
}
