package net.neon.neonsfirstmod.item;

import net.neon.neonsfirstmod.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.neon.neonsfirstmod.NeonsFirstMod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NeonsFirstMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NEONS_FIRST_MOD_TAB = CREATIVE_MODE_TABS.register("neons_first_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL.get()))
            .title(Component.translatable("creativetab.neons_first_mod_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.STEEL.get());

                pOutput.accept(ModBlocks.STEEL_BLOCK.get());
            })
            .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
