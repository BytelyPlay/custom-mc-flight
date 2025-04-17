package org.hyperoil.playifkillers.Listeners;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.hyperoil.playifkillers.ServerSideCommands.VFly;
import org.hyperoil.playifkillers.hyperoil;

@Mod.EventBusSubscriber(modid = hyperoil.MODID)
public class RegisterServerCommands {
    private static MinecraftServer srv;
    @SubscribeEvent
    public void onServerStartingEvent(ServerStartingEvent e) {
        // new Fly(e.getServer().getCommands().getDispatcher());
        new VFly(e.getServer().getCommands().getDispatcher());
        srv=e.getServer();
    }
}
