package com.example.examplemod;

import com.example.examplemod.blocks.HatBlock;
import com.example.examplemod.blocks.HatItem;
import com.example.examplemod.item.ModCreativeModTabs;
import com.example.examplemod.item.ModItem;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(HatMod.MODID)
public class HatMod {
  public static final String MODID = "hatmod";
  private static final Logger LOGGER = LogUtils.getLogger();

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
  public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
          () -> new Item (new Item.Properties()));
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

  public static final RegistryObject<Block> HAT_BLOCK =
      BLOCKS.register("hat_block", () -> new HatBlock());
  public static final RegistryObject<Item> HAT_ITEM =
      ITEMS.register("hat_block", () -> new HatItem(HAT_BLOCK.get()));

  public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB =
      CREATIVE_MODE_TABS.register(
          "example_tab",
          () ->
              CreativeModeTab.builder()
                  .withTabsBefore(CreativeModeTabs.COMBAT)
                  .icon(() -> HAT_ITEM.get().getDefaultInstance())
                  .displayItems(
                      (parameters, output) -> {
                        output.accept(
                            HAT_ITEM
                                .get()); // Add the example item to the tab. For your own tabs, this
                        // method is preferred over the event
                      })
                  .build());

  public HatMod() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(this::commonSetup);
    BLOCKS.register(modEventBus);
    ITEMS.register(modEventBus);
    CREATIVE_MODE_TABS.register(modEventBus);
    MinecraftForge.EVENT_BUS.register(this);
    ModItem.register(modEventBus);
    modEventBus.addListener(this::addCreative);
    ModCreativeModTabs.register(modEventBus);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {}

  private void  addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
      event.accept(ModItem.SAPPHIRE);
      event.accept(ModItem.RAW_SAPPHIRE);
    }
  }

  @SubscribeEvent
  public void onPlayerTick(PlayerTickEvent event) {
    Player player = event.player;
    ItemStack itemstack = player.getItemBySlot(EquipmentSlot.HEAD);
    if (itemstack.is(HAT_ITEM.get())) {
      player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 10, false, false, true));
    }
  }


}
