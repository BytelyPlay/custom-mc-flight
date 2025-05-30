package org.hyperoil.playifkillers.Listeners;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.hyperoil.playifkillers.ServerSideCommands.VFly;
import org.hyperoil.playifkillers.hyperoil;

public class RegisterServerCommands {
    @SubscribeEvent
    public static void onRegisterCommandsEvent(RegisterCommandsEvent e) {
        // new Fly(e.getServer().getCommands().getDispatcher());
        new VFly(e.getDispatcher());
    }
}
