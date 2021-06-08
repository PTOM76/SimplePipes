package alexiil.mc.mod.pipes.blocks;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import alexiil.mc.mod.pipes.container.SimplePipeContainers;

public class BlockTriggerInvSpace extends BlockTriggerItemInv {

    public BlockTriggerInvSpace(Block.Settings settings) {
        super(settings);
    }

    @Override
    public TileTrigger createBlockEntity(BlockPos pos, BlockState state) {
        return new TileTriggerInvSpace(pos, state);
    }

    @Override
    public ActionResult onUse(
        BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit
    ) {
        BlockEntity be = world.getBlockEntity(pos);

        if (be instanceof TileTriggerInvSpace) {
            if (!world.isClient) {
                ContainerProviderRegistry.INSTANCE
                    .openContainer(SimplePipeContainers.TRIGGER_ITEM_INV_SPACE, player, (buffer) -> {
                        buffer.writeBlockPos(pos);
                    });
            }
            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
}
