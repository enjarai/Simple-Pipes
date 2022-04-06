package nl.enjarai.simplepipes.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BlockEntityPipeCobblestone extends BlockEntityPipe {
    public BlockEntityPipeCobblestone(BlockPos pos, BlockState state) {
        super(SimplePipesBlocks.COBBLESTONE_PIPE_BLOCK_ENTITY, pos, state);
    }
}
