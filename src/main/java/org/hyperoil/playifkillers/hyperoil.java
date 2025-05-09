package org.hyperoil.playifkillers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.hyperoil.playifkillers.Listeners.RegisterServerCommands;
import org.hyperoil.playifkillers.Listeners.SendKeyPressedPackets;
import org.hyperoil.playifkillers.Listeners.VFlyListeners;
import org.hyperoil.playifkillers.Networking.PacketsHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(hyperoil.MODID)
public class hyperoil {
    public static final String MODID = "hyperoil";
    public hyperoil(FMLJavaModLoadingContext context) {
        IEventBus EventBus = context.getModEventBus();
        EventBus.addListener(this::onClientSetup);
        EventBus.addListener(this::onCommonSetup);
    }
    public void onClientSetup(FMLClientSetupEvent e) {
        IEventBus iEventBus = MinecraftForge.EVENT_BUS;
        iEventBus.register(new SendKeyPressedPackets());
    }
    public void onCommonSetup(FMLCommonSetupEvent e) {
        IEventBus iEventBus = MinecraftForge.EVENT_BUS;
        iEventBus.register(new VFlyListeners());
        iEventBus.register(new RegisterServerCommands());
        e.enqueueWork(() -> {
            PacketsHandler.register();
        });
    }
}
