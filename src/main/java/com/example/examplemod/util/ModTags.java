package com.example.examplemod.util;

import com.example.examplemod.HatMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.example.examplemod.HatMod.MODID;

//falta adicionar as ferramentas :3

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
    }
        public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = tag("needs_sapphire_tool");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(HatMod.MODID, name));
    }

    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(HatMod.MODID, name));
        }
    }
}