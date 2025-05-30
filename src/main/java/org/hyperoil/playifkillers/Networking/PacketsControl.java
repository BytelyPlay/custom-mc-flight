package org.hyperoil.playifkillers.Networking;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketsControl {
    public static void register(PayloadRegistrar registrar) {
        registrar.playToClient(
                CUpdateVelocityPacket.TYPE,
                CUpdateVelocityPacket.STREAM_CODEC,
                ClientPayLoadHandler::handleCUpdateVelocityPacket
        );
        registrar.playToServer(
                SRequiredFlightInformation.TYPE,
                SRequiredFlightInformation.STREAM_CODEC,
                ServerPayLoadHandler::handleSRequiredFlightInformationPacket
        );
    }

    public static void sendKeyPressPacketToServer(SRequiredFlightInformation p) {
        PacketDistributor.sendToServer(p);
    }

    public static void sendUpdateVelocityPacketToClient(CUpdateVelocityPacket p, ServerPlayer sp) {
        PacketDistributor.sendToPlayer(sp, p);
    }
}
