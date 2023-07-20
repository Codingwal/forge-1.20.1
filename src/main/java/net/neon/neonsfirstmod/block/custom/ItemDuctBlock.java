package net.neon.neonsfirstmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ItemDuctBlock extends Block {

    // public static final DirectionProperty INPUT_DIRECTION = DirectionProperty.create("input_direction");
    // public static final DirectionProperty OUTPUT_DIRECTION = DirectionProperty.create("output_direction");

    public ItemDuctBlock(Properties pProperties) {
        super(pProperties);
    }

    private static final VoxelShape SHAPE = Block.box(3, 3, 0, 12, 12, 16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add();
    }
}
