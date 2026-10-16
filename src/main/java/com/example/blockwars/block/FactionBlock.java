package com.example.blockwars.block;

import com.example.blockwars.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FactionBlock extends Block {
    public enum Faction { RED, BLUE }

    private final Faction faction;

    public FactionBlock(Faction faction, BlockBehaviour.Properties properties) {
        super(properties);
        this.faction = faction;
    }

    @Override
    public void randomTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.isClientSide || !level.hasChunkAt(pos)) return;

        // One attempt per random tick keeps the spread gradual.
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        BlockPos targetPos = pos.relative(direction);

        // Do not load distant chunks and never spread into air.
        if (!level.hasChunkAt(targetPos)) return;
        BlockState target = level.getBlockState(targetPos);
        if (target.isAir()) return;

        Block red = ModBlocks.RED_BLOCK.get();
        Block blue = ModBlocks.BLUE_BLOCK.get();
        Block enemy = faction == Faction.RED ? blue : red;

        // Same-faction blocks do nothing. When factions meet, either can win
        // the local fight; against ordinary blocks, the faction simply expands.
        if (target.is(this)) return;

        if (target.is(enemy)) {
            // 50/50 battle outcome when attacking an enemy faction block.
            if (random.nextBoolean()) {
                level.setBlock(targetPos, defaultBlockState(), Block.UPDATE_ALL);
            }
        } else {
            // Any non-air block is eligible, including stone, dirt, ores, etc.
            level.setBlock(targetPos, defaultBlockState(), Block.UPDATE_ALL);
        }
    }
}
