package de.epax.adminCommandsGui.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class ItemBuilder {
    private ItemMeta itemMeta;
    private ItemStack itemStack;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayname(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchant,int level) {
        itemMeta.addEnchant(enchant,level,true);
        return this;
    }

    public ItemBuilder setCustomModeldata(int name) {
        itemMeta.setCustomModelData(name);
        return this;
    }

    public ItemBuilder setNBT(String customNBT) {
        if (itemMeta != null) {
            NamespacedKey key = new NamespacedKey("perks", "custom_nbt");

            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            container.set(key, PersistentDataType.STRING, customNBT);
        }
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... flags) {
        itemMeta.addItemFlags(flags);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
