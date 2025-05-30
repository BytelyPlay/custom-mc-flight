package org.hyperoil.playifkillers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.hyperoil.playifkillers.Listeners.GetPayLoadRegistrar;
import org.hyperoil.playifkillers.Listeners.RegisterServerCommands;
import org.hyperoil.playifkillers.Listeners.SendKeyPressedPackets;
import org.hyperoil.playifkillers.Listeners.VFlyListeners;

@Mod(hyperoil.MODID)
public class hyperoil {
    // TODO: finish migration packets are difficult...
    public static final String MODID = "hyperoil";
    public hyperoil(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::onClientSetup);
        eventBus.addListener(this::onCommonSetup);
        eventBus.register(GetPayLoadRegistrar.class);
    }
    public void onClientSetup(FMLClientSetupEvent e) {
        IEventBus iEventBus = NeoForge.EVENT_BUS;
    }
    public void onCommonSetup(FMLCommonSetupEvent e) {
        IEventBus iEventBus = NeoForge.EVENT_BUS;
        iEventBus.register(RegisterServerCommands.class);
    }
}
