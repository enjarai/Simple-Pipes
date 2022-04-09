package nl.enjarai.simplepipes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import nl.enjarai.simplepipes.blocks.SimplePipesBlocks;
import nl.enjarai.simplepipes.items.SimplePipesItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePipes implements ModInitializer {
	public static final String MODID = "simplepipes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		SimplePipesBlocks.register();
		SimplePipesItems.register();
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
