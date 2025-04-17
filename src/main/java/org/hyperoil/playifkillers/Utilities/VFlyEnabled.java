package org.hyperoil.playifkillers.Utilities;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;

public class VFlyEnabled {
    private static HashMap<ServerPlayer, Boolean> isVFlyEnabled = new HashMap<>();

    public static boolean getIsVFlyEnabled(ServerPlayer sp) {
        return isVFlyEnabled.getOrDefault(sp, false);
    }
    public static void setIsVFlyEnabled(ServerPlayer sp, Boolean bool) {
        isVFlyEnabled.put(sp, bool);
    }
}
