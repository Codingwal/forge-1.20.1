package net.neon.neonsfirstmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neon.neonsfirstmod.block.ModBlocks;
import net.neon.neonsfirstmod.item.ModCreativeModeTabs;
import net.neon.neonsfirstmod.item.ModItems;

@Mod(NeonsFirstMod.MOD_ID)
public class NeonsFirstMod {

    public static final String MOD_ID = "neonsfirstmod";

    public NeonsFirstMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC); 
    }
}
