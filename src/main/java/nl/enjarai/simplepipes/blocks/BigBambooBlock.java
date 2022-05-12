package nl.enjarai.simplepipes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class BigBambooBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final EnumProperty<FlowDirection> FLOW_DIRECTION = EnumProperty.of("flow_direction", FlowDirection.class);

    public BigBambooBlock(Settings settings) {
        super(settings.ticksRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);


    }

    private boolean trySuck(BlockPos selfPos, BlockState selfState, ServerWorld world, BlockPos pos) {
        if (isWater(world, pos) && !selfState.get(WATERLOGGED)) {
            world.setBlockState(selfPos, selfState.with(WATERLOGGED, true));
            world.getBlockState(pos).getBlock()
        }
    }

    private boolean isWater(ServerWorld world, BlockPos pos) {
        return world.getFluidState(pos).isEqualAndStill(Fluids.WATER);
    }

    public enum FlowDirection implements StringIdentifiable {
        POSITIVE("positive", 1),
        NEUTRAL("neutral", 0),
        NEGATIVE("negative", -1);

        private String name;
        public int modifier;

        FlowDirection(String name, int modifier) {
            this.name = name;
            this.modifier = modifier;
        }

        @Override
        public String asString() {
            return name;
        }
    }
}
