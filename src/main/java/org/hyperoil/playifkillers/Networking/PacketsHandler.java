package org.hyperoil.playifkillers.Networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class PacketsHandler {
    private static final SimpleChannel CHANNEL = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath("hyperoil", "main")).
            serverAcceptedVersions((status, i) -> true).
            clientAcceptedVersions((status, i) -> true).
            networkProtocolVersion(1).simpleChannel();

    public static void register() {
        CHANNEL.messageBuilder(SRequiredFlightInformation.class, NetworkDirection.PLAY_TO_SERVER).
                encoder(SRequiredFlightInformation::encode).
                decoder(SRequiredFlightInformation::new).
                consumerMainThread(SRequiredFlightInformation::handle).
                add();
        CHANNEL.messageBuilder(CUpdateVelocityPacket.class, NetworkDirection.PLAY_TO_CLIENT).
                encoder(CUpdateVelocityPacket::encode).
                decoder(CUpdateVelocityPacket::new).
                consumerMainThread(CUpdateVelocityPacket::handle).
                add();
    }

    public static void sendKeyPressPacketToServer(SRequiredFlightInformation p) {
        CHANNEL.send(p, PacketDistributor.SERVER.noArg());
    }

    public static void sendUpdateVelocityPacketToClient(CUpdateVelocityPacket p, ServerPlayer sp) {
        CHANNEL.send(p, PacketDistributor.PLAYER.with(sp));
    }
}
