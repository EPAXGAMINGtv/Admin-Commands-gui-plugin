package de.epax.adminCommandsGui.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.UUID;

public class OnClickPlayerSelection implements Listener {


    private static final HashMap<UUID, UUID> selectedPlayers = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory getInv = e.getClickedInventory();

        if (getInv == null) return;

        String title = e.getView().getTitle();

        if (title.startsWith("SeletedPlayer")) {
            e.setCancelled(true);

            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() != Material.PLAYER_HEAD) return;

            SkullMeta meta = (SkullMeta) clickedItem.getItemMeta();
            if (meta == null || meta.getOwningPlayer() == null) return;

            Player selectedPlayer = Bukkit.getPlayer(meta.getOwningPlayer().getUniqueId());

            if (selectedPlayer != null) {
                selectedPlayers.put(player.getUniqueId(), selectedPlayer.getUniqueId());
                player.sendMessage("Du hast " + selectedPlayer.getName() + " ausgewählt!");
            } else {
                player.sendMessage("Dieser Spieler ist nicht mehr online.");
            }
        }
    }


    public static Player getSelectedPlayer(Player player) {
        UUID selectedUUID = selectedPlayers.get(player.getUniqueId());
        return (selectedUUID != null) ? Bukkit.getPlayer(selectedUUID) : null;
    }

}
