package nl.enjarai.simplepipes.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import nl.enjarai.simplepipes.blocks.BlockPipe;
import nl.enjarai.simplepipes.client.SimplePipesModels;

@Environment(EnvType.CLIENT)
public class PipeRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private final ModelPart coreModel;
    private final ModelPart[] facingModels = new ModelPart[6];

    public PipeRenderer(BlockEntityRendererFactory.Context ctx) {
        ModelPart modelPart = ctx.getLayerModelPart(SimplePipesModels.PIPE);

        coreModel = modelPart.getChild("core");

//        for (Direction direction : Direction.values()) {
//            facingModels[direction.ordinal()] = modelPart.getChild(direction.getName());
//        }
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("core", ModelPartBuilder.create().uv(5, 5).cuboid(4.0F, 4.0F, 4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.NONE);

        for (Direction direction : Direction.values()) {
            double x = 0.5 + direction.getOffsetX() * 0.375;
            double y = 0.5 + direction.getOffsetY() * 0.375;
            double z = 0.5 + direction.getOffsetZ() * 0.375;
            double rx = direction.getAxis() == Direction.Axis.X ? 0.125 : 0.25;
            double ry = direction.getAxis() == Direction.Axis.Y ? 0.125 : 0.25;
            double rz = direction.getAxis() == Direction.Axis.Z ? 0.125 : 0.25;
            // VoxelShape faceShape = VoxelShapes.cuboid(x - rx, y - ry, z - rz, x + rx, y + ry, z + rz);
            // modelPartData.addChild(direction.getName(), )
        }

        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = entity.getCachedState();
        Block block = blockState.getBlock();

        if (block instanceof BlockPipe pipe) {
            matrices.push();

            SpriteIdentifier spriteIdentifier = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, pipe.getTexture());
            VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);

            coreModel.render(matrices, vertexConsumer, light, overlay);

            matrices.pop();
        }
    }
}
