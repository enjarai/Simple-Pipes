package nl.enjarai.skinthing;

import net.fabricmc.api.ModInitializer;
import nl.enjarai.skinthing.config.ModConfig;

public class SkinThing implements ModInitializer {
    public static final String MODID = "skinthing";

    @Override
    public void onInitialize() {
        ModConfig.init();
    }
}
