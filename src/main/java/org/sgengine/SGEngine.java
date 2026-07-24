package org.sgengine;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;


@Mod(SGEngine.MODID)
public class SGEngine {
    public static final String MODID = "sgengine";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SGEngine(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Hello SGEngine!");
    }
}
