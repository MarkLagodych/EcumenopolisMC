package org.sgengine;

import com.mojang.logging.LogUtils;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;


@Mod(SGEngine.MODID)
public class SGEngine {
    public static final String MODID = "sgengine";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SGEngine(IEventBus modEventBus, ModContainer ignoredModContainer) {
        modEventBus.register(this);
    }

    @SubscribeEvent
    public void registerCommandsEvent(RegisterCommandsEvent event) {
        event.getDispatcher().getRoot().addChild(
            Commands.literal("fuck").executes(context -> {
                context.getSource().sendSuccess(
                    () -> Component.literal("Hello from custom command!"),
                    true
                );
                return 1;
            }).build()
        );
    }
}

class SGEngineNeoForgeEventHandler {
    public SGEngineNeoForgeEventHandler(SGEngine)
}
