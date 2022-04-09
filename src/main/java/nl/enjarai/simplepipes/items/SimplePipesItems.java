package nl.enjarai.simplepipes.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import nl.enjarai.simplepipes.blocks.SimplePipesBlocks;

public class SimplePipesItems {
    public static final Item COBBLESTONE_PIPE = registerBlockItem(SimplePipesBlocks.COBBLESTONE_PIPE_BLOCK);

    public static void register() {}

    private static Item registerBlockItem(Block block) {
        return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block),
                new BlockItem(block, new FabricItemSettings().group(ItemGroup.REDSTONE)));
    }
}
