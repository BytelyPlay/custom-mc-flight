package org.hyperoil.playifkillers.Networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.hyperoil.playifkillers.Listeners.VFlyListeners;

public class CUpdateVelocityPacket {
    public final Vec3 velocity;

    public CUpdateVelocityPacket(Vec3 vel) {
        velocity = vel;
    }

    public CUpdateVelocityPacket(FriendlyByteBuf friendlyByteBuf) {
        this(friendlyByteBuf.readVec3());
    }

    public static void encode(CUpdateVelocityPacket p, FriendlyByteBuf buffer) {
        buffer.writeVec3(p.velocity);
    }

    public void handle(CustomPayloadEvent.Context context) {
        LocalPlayer lp = Minecraft.getInstance().player;
        if (lp == null) return;
        lp.setDeltaMovement(velocity);
        System.out.println(lp.getYRot());
        context.setPacketHandled(true);
    }
}
