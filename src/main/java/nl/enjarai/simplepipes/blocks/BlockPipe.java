package nl.enjarai.simplepipes.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public abstract class BlockPipe extends BlockWithEntity implements Waterloggable {
    private static final VoxelShape CORE_SHAPE;
    private static final VoxelShape[] SHAPES;

    static {
        CORE_SHAPE = VoxelShapes.cuboid(0.25, 0.25, 0.25, 0.75, 0.75, 0.75);
        SHAPES = new VoxelShape[6];
        for (Direction direction : Direction.values()) {
            double x = 0.5 + direction.getOffsetX() * 0.375;
            double y = 0.5 + direction.getOffsetY() * 0.375;
            double z = 0.5 + direction.getOffsetZ() * 0.375;
            double rx = direction.getAxis() == Direction.Axis.X ? 0.125 : 0.25;
            double ry = direction.getAxis() == Direction.Axis.Y ? 0.125 : 0.25;
            double rz = direction.getAxis() == Direction.Axis.Z ? 0.125 : 0.25;
            VoxelShape faceShape = VoxelShapes.cuboid(x - rx, y - ry, z - rz, x + rx, y + ry, z + rz);
            SHAPES[direction.ordinal()] = faceShape;
        }
    }

    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty UP = Properties.UP;
    public static final BooleanProperty DOWN = Properties.DOWN;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    protected BlockPipe() {
        super(FabricBlockSettings.of(Material.AGGREGATE).strength(0.5f, 1f).nonOpaque());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(NORTH);
        builder.add(EAST);
        builder.add(SOUTH);
        builder.add(WEST);
        builder.add(UP);
        builder.add(DOWN);
        builder.add(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState()
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var shape = CORE_SHAPE;

        for (Direction direction : Direction.values()) {
            if (isConnected(state, direction)) {
                shape = VoxelShapes.union(shape, SHAPES[direction.ordinal()]);
            }
        }

        return shape;
    }

    private boolean isConnected(BlockState state, Direction direction) {
        BooleanProperty property = ConnectingBlock.FACING_PROPERTIES.get(direction);
        return state.get(property);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BooleanProperty property = ConnectingBlock.FACING_PROPERTIES.get(direction);
        return state.with(property, neighborState.getBlock() instanceof BlockPipe);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);

    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Nullable
    @Override
    public abstract BlockEntity createBlockEntity(BlockPos pos, BlockState state);

    public abstract Identifier getTexture();
}
