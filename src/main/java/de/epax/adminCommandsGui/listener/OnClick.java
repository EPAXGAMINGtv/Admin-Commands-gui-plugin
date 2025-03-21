package de.epax.adminCommandsGui.listener;

import de.epax.adminCommandsGui.AdminCommandsGui;
import de.epax.adminCommandsGui.util.ItemBuilder;
import de.epax.adminCommandsGui.values.Booleans;
import org.bukkit.BanEntry;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import static de.epax.adminCommandsGui.values.Booleans.*;


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
            if (getInv.equals("§cSeletedPlayer")){
                if (target.equals(null)){
                    p.closeInventory();
                    p.sendMessage("Kein spieler ausgewählt");
                }else {

                if (ban){
                    ban=false;
                    Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(target.getName(), "AdminBan", null, null);
                    target.kickPlayer("Du wurdest von einem Admin gebannt");
                    p.sendMessage("Du hast " + target.getName() + " gebannt.");
                    p.closeInventory();
                    target = null;
                } else if (kick) {
                    kick = false;
                    target.kickPlayer("Du wurdest von einen Admin gekickt!");
                    p.closeInventory();
                    target = null;
                } else if (survival ) {
                    survival =false;
                    if (target.getGameMode().equals(GameMode.SURVIVAL)){
                        p.sendMessage("Dieser Spieler ist bereits im SuvivalModus");
                        p.closeInventory();
                        target = null;
                    }else {
                        target.sendMessage("Du wurdest in SurivalModus Gesetzt");
                        target.setGameMode(GameMode.SURVIVAL);
                        p.closeInventory();
                        target = null;
                    }
                } else if (creative ) {
                    creative = false;
                    if (target.getGameMode().equals(GameMode.CREATIVE)){
                        p.sendMessage("Dieser Spieler ist bereits im CreativeModus");
                        p.closeInventory();
                        target = null;
                    }else {
                        target.sendMessage("Du wurdest in CreativeModus Gesetzt");
                        target.setGameMode(GameMode.CREATIVE);
                        p.closeInventory();
                        target = null;
                    }
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
