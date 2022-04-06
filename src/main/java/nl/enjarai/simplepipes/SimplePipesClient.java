package nl.enjarai.simplepipes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import nl.enjarai.simplepipes.blocks.SimplePipesBlocks;
import nl.enjarai.simplepipes.client.SimplePipesModels;
import nl.enjarai.simplepipes.client.model.PipeRenderer;

@Environment(EnvType.CLIENT)
public class SimplePipesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(SimplePipesBlocks.COBBLESTONE_PIPE_BLOCK, RenderLayer.getCutout());

//        SimplePipesModels.register();
//
//        BlockEntityRendererRegistry.INSTANCE.register(SimplePipesBlocks.COBBLESTONE_PIPE_BLOCK_ENTITY, PipeRenderer::new);
    }
}
