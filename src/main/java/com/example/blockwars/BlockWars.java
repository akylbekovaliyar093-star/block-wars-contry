package com.example.blockwars;

import com.example.blockwars.registry.ModBlocks;
import com.example.blockwars.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BlockWars.MOD_ID)
public class BlockWars {
    public static final String MOD_ID = "blockwars";

    public BlockWars() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
    }
}
