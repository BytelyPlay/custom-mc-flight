package org.hyperoil.playifkillers.Listeners;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.hyperoil.playifkillers.Networking.PacketsControl;
import org.hyperoil.playifkillers.hyperoil;

public class GetPayLoadRegistrar {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("hyperoil")
                .versioned("1.0.0")
                .executesOn(HandlerThread.NETWORK)
                .optional();
        PacketsControl.register(registrar);
    }
}
