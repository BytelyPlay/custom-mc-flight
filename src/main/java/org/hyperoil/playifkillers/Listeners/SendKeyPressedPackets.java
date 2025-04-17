package org.hyperoil.playifkillers.Listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.hyperoil.playifkillers.Networking.PacketsHandler;
import org.hyperoil.playifkillers.Networking.SRequiredFlightInformation;
import org.hyperoil.playifkillers.hyperoil;

@Mod.EventBusSubscriber(modid = hyperoil.MODID)
public class SendKeyPressedPackets {
    @SubscribeEvent
    public void onClientTickEvent(TickEvent.ClientTickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        Options o = mc.options;
        if (mc.getConnection() == null || mc.player == null || mc.level == null) return;
        PacketsHandler.sendKeyPressPacketToServer(new SRequiredFlightInformation(
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
