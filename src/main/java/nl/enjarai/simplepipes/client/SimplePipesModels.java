package nl.enjarai.simplepipes.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import nl.enjarai.simplepipes.SimplePipes;
import nl.enjarai.simplepipes.blocks.BlockPipe;
import nl.enjarai.simplepipes.blocks.SimplePipesBlocks;
import nl.enjarai.simplepipes.client.model.PipeRenderer;

public class SimplePipesModels {
    public static final EntityModelLayer PIPE = getModelLayer("pipe");

    public static void register() {
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(((BlockPipe) SimplePipesBlocks.COBBLESTONE_PIPE_BLOCK).getTexture());
        });

        EntityModelLayerRegistry.registerModelLayer(PIPE, PipeRenderer::getTexturedModelData);
    }

    private static EntityModelLayer getModelLayer(String path) {
        return new EntityModelLayer(SimplePipes.id(path), "main");
    }
}
