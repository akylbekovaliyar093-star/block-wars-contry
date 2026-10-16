
package com.example.blockwars.block;

import com.example.blockwars.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FactionBlock extends Block {
    public enum Faction {
        RED, BLUE
    }

    private final Faction faction;

    public FactionBlock(Faction faction, BlockBehaviour.Properties properties) {
        super(properties);
        this.faction = faction;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isClientSide || random.nextInt(5) != 0) {
            return;
        }

        net.minecraft.core.Direction direction =
                net.minecraft.core.Direction.values()[random.nextInt(6)];
        BlockPos targetPos = pos.relative(direction);

        if (!level.hasChunkAt(targetPos)) return;

        BlockState target = level.getBlockState(targetPos);
        if (target.isAir()) return;

        Block red = ModBlocks.RED_BLOCK.get();
        Block blue = ModBlocks.BLUE_BLOCK.get();
        Block enemy = faction == Faction.RED ? blue : red;

        if (target.is(this)) return;

        if (target.is(enemy)) {
            if (random.nextBoolean()) {
                level.setBlock(targetPos, defaultBlockState(), Block.UPDATE_ALL);
            }
        } else {
            level.setBlock(targetPos, defaultBlockState(), Block.UPDATE_ALL);
        }
    }
}

