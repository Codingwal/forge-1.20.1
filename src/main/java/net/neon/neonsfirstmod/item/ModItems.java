package net.neon.neonsfirstmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.neon.neonsfirstmod.NeonsFirstMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            NeonsFirstMod.MOD_ID);

    public static final RegistryObject<Item> UNFINISHED_STEEL_INGOT = ITEMS.register("unfinished_steel_ingot", 
            () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", 
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}
