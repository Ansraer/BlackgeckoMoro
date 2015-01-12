package com.blackgecko.moro.mod;

import com.blackgecko.moro.mod.commands.CommandInfo;
import com.blackgecko.moro.mod.commands.MoroHelp;
import com.blackgecko.moro.mod.commands.MoroTime;
import com.blackgecko.moro.mod.events.MoroEventHandler;
import com.blackgecko.moro.mod.proxies.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
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
    
    @SidedProxy(serverSide="com.blackgecko.moro.mod.proxies.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
      event.registerServerCommand(new CommandInfo());
      event.registerServerCommand(new MoroHelp());
      event.registerServerCommand(new MoroTime());
    }
    
    
    
    public static boolean timeLimitEnabled;
    public static int banTimeOnDeath;
    public static int timeDay;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();

        timeLimitEnabled = config.get(Configuration.CATEGORY_GENERAL, "TimeLimitEnabled", false).getBoolean(false);
        
        timeDay = config.getInt("TimeEveryDay(MIN)", Configuration.CATEGORY_GENERAL, 30, 10, 1440, "Time added every day to a players play time");

        banTimeOnDeath = config.getInt("BanTime", Configuration.CATEGORY_GENERAL, 0, 0, Integer.MAX_VALUE, "How Long Should a player be banned after death?");
        

        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(new MoroEventHandler());
    	MinecraftForge.EVENT_BUS.register(new MoroEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		// some example code
        System.out.println("postinit");
    }
}
