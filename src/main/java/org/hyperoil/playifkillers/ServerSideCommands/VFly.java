package org.hyperoil.playifkillers.ServerSideCommands;

import com.google.common.collect.ArrayListMultimap;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.extensions.IPlayerExtension;
import org.hyperoil.playifkillers.Utilities.CommandRegistrar;
import org.hyperoil.playifkillers.Utilities.VFlyEnabled;

import java.util.HashMap;
import java.util.UUID;

public class VFly extends CommandRegistrar {
    private HashMap<UUID, Boolean> allowFlight = new HashMap<>();
    public VFly(CommandDispatcher<CommandSourceStack> dispatcher) {
        super(dispatcher);
    }

    @Override
    protected int commandExecute(CommandContext<CommandSourceStack> context) {
        ServerPlayer sp = context.getSource().getPlayer();
        IPlayerExtension spExtension = sp;
        if (sp == null) return Command.SINGLE_SUCCESS;
        if (!sp.hasPermissions(2)) {
            sp.sendSystemMessage(Component.literal("Sorry You need to be at least operator level 2 to use vfly."));
            return Command.SINGLE_SUCCESS;
        }
        if (VFlyEnabled.getIsVFlyEnabled(sp)) {
            if (spExtension.mayFly()) {
                ResourceKey<?> resourceKey = NeoForgeMod.CREATIVE_FLIGHT.
                        getKey();
                if (resourceKey == null) {
                    System.out.println("resourceKey == null cannot proceed in commandExecute");
                    return Command.SINGLE_SUCCESS;
                }
                ArrayListMultimap<Holder<Attribute>,
                        AttributeModifier> attributes = ArrayListMultimap.create();
                attributes.put(NeoForgeMod.CREATIVE_FLIGHT, new AttributeModifier(NeoForgeMod.CREATIVE_FLIGHT.
                        getKey().registry(), 1D, AttributeModifier.Operation.ADD_VALUE));
                sp.getAttributes().addTransientAttributeModifiers(attributes);
            }
            VFlyEnabled.setIsVFlyEnabled(sp, false);
        } else {
            ResourceKey<?> resourceKey = NeoForgeMod.CREATIVE_FLIGHT.
                    getKey();
            if (resourceKey == null) {
                System.out.println("resourceKey == null cannot proceed in commandExecute");
                return Command.SINGLE_SUCCESS;
            }
            ArrayListMultimap<Holder<Attribute>,
                    AttributeModifier> attributes = ArrayListMultimap.create();
            attributes.put(NeoForgeMod.CREATIVE_FLIGHT, new AttributeModifier(NeoForgeMod.CREATIVE_FLIGHT.
                    getKey().registry(), 1D, AttributeModifier.Operation.ADD_VALUE));
            sp.getAttributes().addTransientAttributeModifiers(attributes);
            allowFlight.put(sp.getUUID(), spExtension.mayFly());
            VFlyEnabled.setIsVFlyEnabled(sp, true);
        }
        return Command.SINGLE_SUCCESS;
    }

    @Override
    protected String getCommandName() {
        return "vfly";
    }
}
