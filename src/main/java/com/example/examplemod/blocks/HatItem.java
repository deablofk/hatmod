package com.example.examplemod.blocks;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

/** HatItem */
public class HatItem extends BlockItem implements Equipable {

  public HatItem(Block block) {
    super(block, new Item.Properties());
  }

  @Override
  public EquipmentSlot getEquipmentSlot() {
    return EquipmentSlot.HEAD;
  }
}
