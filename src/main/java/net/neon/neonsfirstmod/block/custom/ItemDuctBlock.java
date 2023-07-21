package net.neon.neonsfirstmod.block.custom;

import org.lwjgl.system.windows.INPUT;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ItemDuctBlock extends Block implements IItemDuctConnectable {

    public static final DirectionProperty INPUT_DIRECTION = DirectionProperty.create("input_direction");
    public static final DirectionProperty OUTPUT_DIRECTION = DirectionProperty.create("output_direction");

    public static final BooleanProperty IS_INPUT_CONNECTED = BooleanProperty.create("is_input_connected");
    public static final BooleanProperty IS_OUTPUT_CONNECTED = BooleanProperty.create("is_output_connected");

    public ItemDuctBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(INPUT_DIRECTION, Direction.NORTH)
                .setValue(OUTPUT_DIRECTION, Direction.SOUTH)
                .setValue(IS_INPUT_CONNECTED, false)
                .setValue(IS_OUTPUT_CONNECTED, false));
    }

    private static final VoxelShape SHAPE = Block.box(3, 3, 0, 12, 12, 16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction side, BlockState p_196271_3_, LevelAccessor world,
            BlockPos pos, BlockPos p_196271_6_) {

        if (world.isClientSide()) {
            return state;
        }
        for (Direction direction : Direction.values()) {
            tryConnectItemDucts(pos, state, direction, world);
        }
        return state;
    }

    private void tryConnectItemDucts(BlockPos pos, BlockState state, Direction direction, LevelAccessor world) {
        // Get the neighbour block data
        BlockPos neighbourPos = pos.relative(direction);
        BlockState neighbourState = world.getBlockState(neighbourPos);
        Block neighbourBlock = neighbourState.getBlock();

        // Check if the neighbour can connect to ItemDucts and return otherwise
        if (!(neighbourBlock instanceof IItemDuctConnectable)) {
            return;
        }

        // Check if the side is already occupied
        if (isSideOccupied(state, direction)) {
            return;
        }

        // Check if the neighbour doesn't have its input connected and this doesn't have
        // its output connected
        if (!neighbourState.getValue(IS_INPUT_CONNECTED) && !state.getValue(IS_OUTPUT_CONNECTED)) {

            // Connect the input of the neighbour with the output of this
            connectItemDucts(pos, state, direction, neighbourPos, neighbourState, true, world);
            return;
        }

        // Check if the neighbour doesn't have its output connected and this doesn't
        // have its input connected
        if (!neighbourState.getValue(IS_OUTPUT_CONNECTED) && !state.getValue(IS_INPUT_CONNECTED)) {

            // Connect the output of the neighbour with the input of this
            connectItemDucts(pos, state, direction, neighbourPos, neighbourState, false, world);
            return;
        }
    }

    private boolean isSideOccupied(BlockState state, Direction direction) {
        if (state.getValue(IS_INPUT_CONNECTED)) {
            if (state.getValue(INPUT_DIRECTION) == direction) {
                return true;
            }
        }
        if (state.getValue(IS_OUTPUT_CONNECTED)) {
            if (state.getValue(OUTPUT_DIRECTION) == direction) {
                return true;
            }
        }
        return false;
    }

    private void connectItemDucts(BlockPos pos, BlockState state, Direction direction, BlockPos neighbourPos,
            BlockState neighbourState,
            boolean outputOfThis, LevelAccessor world) {

        if (outputOfThis) {
            neighbourState.setValue(INPUT_DIRECTION, direction.getOpposite());
            state.setValue(OUTPUT_DIRECTION, direction);
        } else {
            neighbourState.setValue(OUTPUT_DIRECTION, direction.getOpposite());
            state.setValue(INPUT_DIRECTION, direction);
        }
        world.setBlock(pos, state, 3);
        world.setBlock(neighbourPos, neighbourState, 3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(INPUT_DIRECTION, OUTPUT_DIRECTION, IS_INPUT_CONNECTED, IS_OUTPUT_CONNECTED);
    }
}