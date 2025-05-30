package org.hyperoil.playifkillers.Networking;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.hyperoil.playifkillers.Listeners.VFlyListeners;

public class ServerPayLoadHandler {
    public static void handleSRequiredFlightInformationPacket(final SRequiredFlightInformation packet, final IPayloadContext ctx) {
        VFlyListeners.packetHashMap.put((ServerPlayer) ctx.player(), packet);
    }
}
