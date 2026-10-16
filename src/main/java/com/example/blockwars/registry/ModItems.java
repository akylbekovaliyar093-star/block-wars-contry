package com.example.blockwars.registry;

import com.example.blockwars.BlockWars;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BlockWars.MOD_ID);

    public static final RegistryObject<Item> RED_BLOCK_ITEM = ITEMS.register("red_block",
            () -> new BlockItem(ModBlocks.RED_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLUE_BLOCK_ITEM = ITEMS.register("blue_block",
            () -> new BlockItem(ModBlocks.BLUE_BLOCK.get(), new Item.Properties()));

    private ModItems() {}
}
