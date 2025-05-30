package org.hyperoil.playifkillers.Networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record CUpdateVelocityPacket(double x, double y, double z) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CUpdateVelocityPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("hyperoil", "clientboundupdatevelocitypacket"));

    public static final StreamCodec<ByteBuf, CUpdateVelocityPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE,
            CUpdateVelocityPacket::x,
            ByteBufCodecs.DOUBLE,
            CUpdateVelocityPacket::y,
            ByteBufCodecs.DOUBLE,
            CUpdateVelocityPacket::z,
            CUpdateVelocityPacket::new
    );

    public CUpdateVelocityPacket(Vec3 vec3) {
        this(vec3.x(), vec3.y(), vec3.z());
    }

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
