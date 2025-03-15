package de.epax.adminCommandsGui.listener;

import de.epax.adminCommandsGui.AdminCommandsGui;
import de.epax.adminCommandsGui.util.ItemBuilder;
import org.bukkit.BanEntry;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class OnClick implements Listener {

    @EventHandler
    public void OnClick(InventoryClickEvent e){
        if (e.getWhoClicked() instanceof  Player p) {
            String getInv = e.getView().getTitle();

            if (getInv.equals("§cAdmin-gui")) {
                e.setCancelled(true);
            }

            if (getInv.equals("§cSeletedPlayer")) {
                e.setCancelled(true);
            }

            int slot = e.getRawSlot();

            Player target = OnClickPlayerSelection.getSelectedPlayer(p);

            if (slot == 0) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(target.getName(), "AdminBan", null, null);
                    target.kickPlayer("Du wurdest von einen Admin gebant");
                    p.sendMessage("Du hast " + target.getName() + " gebannt.");
                    target = null;
                }
            } else if (slot == 1) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    target.kickPlayer("Du wurdest von einen Admin geKickt");
                    p.sendMessage("Du hast " + target.getName() + " geKickt.");
                    target = null;
                }
            } else if (slot == 2) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    target.setGameMode(GameMode.CREATIVE);
                    p.sendMessage("Du hast " + target.getName() + " im Creative Modus gesetzt.");
                    target = null;
                }

            } else if (slot == 3) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    target.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage("Du hast " + target.getName() + " im Survival Modus gesetzt.");
                    target = null;
                }
            }
        }
    }
}
