package nl.enjarai.simplepipes.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import nl.enjarai.simplepipes.SimplePipes;
import org.jetbrains.annotations.Nullable;

public class BlockPipeCobblestone extends BlockPipe {
    protected BlockPipeCobblestone() {
        super();
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityPipeCobblestone(pos, state);
    }

    @Override
    public Identifier getTexture() {
        return SimplePipes.id("block/cobblestone_pipe");
    }
}
