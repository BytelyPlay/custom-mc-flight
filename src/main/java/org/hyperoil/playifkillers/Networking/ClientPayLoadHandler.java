package org.hyperoil.playifkillers.Networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayLoadHandler {
    public static void handleCUpdateVelocityPacket(final CUpdateVelocityPacket packet, final IPayloadContext ctx) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer lp = mc.player;
        if (lp == null) return;
        lp.setDeltaMovement(packet.x(), packet.y(), packet.z());
    }
}
