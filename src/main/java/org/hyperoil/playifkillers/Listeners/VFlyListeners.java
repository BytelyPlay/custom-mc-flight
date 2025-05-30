package org.hyperoil.playifkillers.Listeners;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.hyperoil.playifkillers.Networking.CUpdateVelocityPacket;
import org.hyperoil.playifkillers.Networking.PacketsControl;
import org.hyperoil.playifkillers.Networking.SRequiredFlightInformation;
import org.hyperoil.playifkillers.Utilities.VFlyEnabled;
import org.hyperoil.playifkillers.hyperoil;

import java.util.HashMap;

@EventBusSubscriber(modid = hyperoil.MODID)
public class VFlyListeners {
    private static final Vec3 JumpVec3 = new Vec3(0, 2, 0);
    private static final Vec3 DownVec3 = new Vec3(0, -2, 0);
    private static final Vec3 ElseVec3 = new Vec3(0, 0, 0);

    public static HashMap<ServerPlayer, SRequiredFlightInformation> packetHashMap = new HashMap<>();
    @SubscribeEvent
    public static void onServerTickEvent(ServerTickEvent.Post e) {
        for (ServerPlayer sp : packetHashMap.keySet()) {
            SRequiredFlightInformation requiredFlightInformation = packetHashMap.get(sp);
            if (requiredFlightInformation == null) {
                sp.setDeltaMovement(ElseVec3);
                return;
            }
            if (VFlyEnabled.getIsVFlyEnabled(sp)) {
                float yaw = requiredFlightInformation.yaw();
                double radians = Math.toRadians(yaw);
                Vec3 Vec3ToModify = ElseVec3;
                if (requiredFlightInformation.Jumping()) {
                    Vec3ToModify = Vec3ToModify.add(JumpVec3);
                }
                if (requiredFlightInformation.Sneak()) {
                    Vec3ToModify = Vec3ToModify.add(DownVec3);
                }
                if (requiredFlightInformation.right()) {
                    double xRight = -Math.sin(radians + Math.PI / 2);
                    double zRight = Math.cos(radians + Math.PI / 2);
                    Vec3ToModify = Vec3ToModify.add(new Vec3(xRight, 0, zRight).scale(2.5));
                }
                if (requiredFlightInformation.left()) {
                    double xLeft = -Math.sin(radians - Math.PI / 2);
                    double zLeft = Math.cos(radians - Math.PI / 2);
                    Vec3ToModify = Vec3ToModify.add(new Vec3(xLeft, 0, zLeft).scale(2.5));
                }
                if (requiredFlightInformation.forward()) {
                    double xDir = -Math.sin(radians);
                    double zDir = Math.cos(radians);
                    Vec3ToModify = Vec3ToModify.add(new Vec3(xDir, 0, zDir).scale(2.5));
                }
                if (requiredFlightInformation.backwards()) {
                    double xDir = -Math.sin(radians);
                    double zDir = Math.cos(radians);
                    Vec3ToModify = Vec3ToModify.add(new Vec3(xDir, 0, zDir).scale(-2.5));
                }
                if (requiredFlightInformation.Sprinting()) {
                    Vec3ToModify = Vec3ToModify.scale(1.5);
                }
                sp.setDeltaMovement(Vec3ToModify);
                PacketsControl.sendUpdateVelocityPacketToClient(new CUpdateVelocityPacket(Vec3ToModify), sp);
            }
        }
    }
}
