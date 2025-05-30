package org.hyperoil.playifkillers.Networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SRequiredFlightInformation(
        boolean Sprinting,
        boolean Jumping,
        boolean forward,
        boolean backwards,
        boolean left,
        boolean right,
        boolean Sneak,
        float yaw
) implements CustomPacketPayload {
    // moved to neoforge check if the yaw part is still required for accurate yaw.

    public static final CustomPacketPayload.Type<SRequiredFlightInformation> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("hyperoil", "serverboundrequiredflightinformation"));

    public static final StreamCodec<ByteBuf, SRequiredFlightInformation> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::Sprinting,
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::Jumping,
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::forward,
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::backwards,
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::left,
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::right,
            ByteBufCodecs.BOOL,
            SRequiredFlightInformation::Sneak,
            ByteBufCodecs.FLOAT,
            SRequiredFlightInformation::yaw,
            SRequiredFlightInformation::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
