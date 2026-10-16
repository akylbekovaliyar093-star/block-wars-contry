package com.example.blockwars.registry;

import com.example.blockwars.BlockWars;
import com.example.blockwars.block.FactionBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BlockWars.MOD_ID);

    public static final RegistryObject<Block> RED_BLOCK = BLOCKS.register("red_block",
            () -> new FactionBlock(FactionBlock.Faction.RED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                            .strength(2.0F).sound(SoundType.STONE).randomTicks()));

    public static final RegistryObject<Block> BLUE_BLOCK = BLOCKS.register("blue_block",
            () -> new FactionBlock(FactionBlock.Faction.BLUE,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE)
                            .strength(2.0F).sound(SoundType.STONE).randomTicks()));

    private ModBlocks() {}
}
