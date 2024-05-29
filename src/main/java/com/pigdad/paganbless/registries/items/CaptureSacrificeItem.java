package com.pigdad.paganbless.registries.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/**
 * Item that when crafted through runic ritual captures the sacrificed entity
 */
public interface CaptureSacrificeItem {
    default void setEntity(Entity entity, ItemStack itemStack) {
        if (entity instanceof LivingEntity livingEntity) {
            CompoundTag tag = new CompoundTag();
            tag.put("entity", entity.serializeNBT(entity.registryAccess()));
            tag.getCompound("entity").putFloat("Health", livingEntity.getMaxHealth());
            tag.getCompound("entity").remove("HandItems");
            tag.getCompound("entity").remove("ArmorItems");
            CustomData data = CustomData.of(tag);
            itemStack.set(DataComponents.ENTITY_DATA, data);
        }
    }

    default void setEntity(EntityType<?> entity, ItemStack itemStack) {
        CompoundTag tag = new CompoundTag();
        tag.put("entity", new CompoundTag());
        tag.getCompound("entity").putString("id", EntityType.getKey(entity).toString());
        CustomData data = CustomData.of(tag);
        itemStack.set(DataComponents.ENTITY_DATA, data);
    }

}
