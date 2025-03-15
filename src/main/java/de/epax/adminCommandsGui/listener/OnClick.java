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
        boolean creative = false;
        boolean survival = false;
        boolean ban = false;
        boolean kick = false;
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
            if (getInv.equals("§cSeletedPlayer")){
                if (ban==true){
                    ban=false;
                    Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(target.getName(), "AdminBan", null, null);
                    target.kickPlayer("Du wurdest von einem Admin gebannt");
                    p.sendMessage("Du hast " + target.getName() + " gebannt.");
                    p.closeInventory();
                } else if (kick==true) {
                    kick = false;
                    target.kickPlayer("Du wurdest von einen Admin gekickt!");
                    p.closeInventory();
                } else if (survival == true) {
                    if (target.getGameMode().equals(GameMode.SURVIVAL)){
                        p.sendMessage("Dieser Spieler ist bereits im SuvivalModus");
                        p.closeInventory();
                    }else {
                        target.sendMessage("Du wurdest in SurivalModus Gesetzt");
                        target.setGameMode(GameMode.SURVIVAL);
                    }
                } else if (creative == true) {
                    if (target.getGameMode().equals(GameMode.CREATIVE)){
                        p.sendMessage("Dieser Spieler ist bereits im CreativeModus");
                        p.closeInventory();
                    }else {
                        target.sendMessage("Du wurdest in CreativeModus Gesetzt");
                        target.setGameMode(GameMode.CREATIVE);
                    }
                }
            }
            if (slot == 0) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    ban=true;
                }
            } else if (slot == 1) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    kick =true;
                }
            } else if (slot == 2) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    creative = true;
                }
            } else if (slot == 3) {
                if (getInv.equals("§cAdmin-gui")) {
                    AdminCommandsGui.openPlayerMenu(0, p);
                    survival = true;
                }
            }
        }
    }
}
