package org.hyperoil.playifkillers.Utilities;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public abstract class CommandRegistrar {
    // TODO: add a way to use arguments just like you did in the hyperoil anarchy mod
    public CommandRegistrar(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(getCommandName()).executes(this::commandExecute));
    }

    protected abstract int commandExecute(CommandContext<CommandSourceStack> context);
    protected abstract String getCommandName();
}
