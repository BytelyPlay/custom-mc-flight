package org.hyperoil.playifkillers.Networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.hyperoil.playifkillers.Listeners.VFlyListeners;

public class SRequiredFlightInformation {
    public final boolean Sprinting;
    public final boolean Jumping;
    public final boolean forward;
    public final boolean backwards;
    public final boolean left;
    public final boolean right;
    public final boolean Sneak;
    // This is because forge LOVESSSSSS to desync yaw so i have to get it manually and another custom packet just gets overwritten
    // i will probably have to make sure this isn't too much data.
    public final float yaw;

    public SRequiredFlightInformation(boolean isSprinting, boolean jumping, boolean forward, boolean backwards, boolean left, boolean right, boolean sneak, float yaw1) {
        this.Sprinting = isSprinting;
        Jumping = jumping;
        this.forward = forward;
        this.backwards = backwards;
        this.left = left;
        this.right = right;
        Sneak = sneak;
        yaw = yaw1;
    }

    public SRequiredFlightInformation(FriendlyByteBuf friendlyByteBuf) {
        this(friendlyByteBuf.readBoolean(), friendlyByteBuf.readBoolean(),
                friendlyByteBuf.readBoolean(), friendlyByteBuf.readBoolean(),
                friendlyByteBuf.readBoolean(), friendlyByteBuf.readBoolean(),
                friendlyByteBuf.readBoolean(), friendlyByteBuf.readFloat());
    }

    public static void encode(SRequiredFlightInformation p, FriendlyByteBuf buffer) {
        buffer.writeBoolean(p.Sprinting);
        buffer.writeBoolean(p.Jumping);
        buffer.writeBoolean(p.forward);
        buffer.writeBoolean(p.backwards);
        buffer.writeBoolean(p.left);
        buffer.writeBoolean(p.right);
        buffer.writeBoolean(p.Sneak);
        buffer.writeFloat(p.yaw);
    }

    public void handle(CustomPayloadEvent.Context context) {
        VFlyListeners.packetHashMap.put(context.getSender(), this);
        context.setPacketHandled(true);
    }
}
