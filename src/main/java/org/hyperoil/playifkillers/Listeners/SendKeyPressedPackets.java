package org.hyperoil.playifkillers.Listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.hyperoil.playifkillers.Networking.PacketsControl;
import org.hyperoil.playifkillers.Networking.SRequiredFlightInformation;
import org.hyperoil.playifkillers.hyperoil;

@EventBusSubscriber(modid = hyperoil.MODID)
public class SendKeyPressedPackets {
    @SubscribeEvent
    public static void onClientTickEvent(ClientTickEvent.Post e) {
        Minecraft mc = Minecraft.getInstance();
        Options o = mc.options;
        if (mc.getConnection() == null || mc.player == null || mc.level == null) return;
        PacketsControl.sendKeyPressPacketToServer(new SRequiredFlightInformation(
                o.keySprint.isDown(),
                o.keyJump.isDown(),
                o.keyUp.isDown(),
                o.keyDown.isDown(),
                o.keyLeft.isDown(),
                o.keyRight.isDown(),
                o.keyShift.isDown(),
                mc.player.getYRot()
        ));
    }
}
