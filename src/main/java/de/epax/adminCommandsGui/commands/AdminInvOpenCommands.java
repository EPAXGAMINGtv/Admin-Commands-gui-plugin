package de.epax.adminCommandsGui.commands;

import de.epax.adminCommandsGui.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Material.BLACK_STAINED_GLASS_PANE;
import static org.bukkit.Material.PAPER;

public class AdminInvOpenCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p){
               if (p.isOp()){
                   Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Admin-gui");

                   for (int i = 0; i < 9 * 6; i++) {
                       inventory.setItem(i, new ItemBuilder(BLACK_STAINED_GLASS_PANE).setDisplayname("").build());
                   }

                   inventory.setItem(0, new ItemBuilder(PAPER).setDisplayname("§cBan Player").build());
                   inventory.setItem(1, new ItemBuilder(PAPER).setDisplayname("§cKick Player").build());
                   p.openInventory(inventory);
               }else {
                   p.sendMessage("Du hast keine Erlaubnis dies zu tun !");
               }
        }else{
            commandSender.sendMessage("Du must ein Spieler sein um diesen Command zu Nutzen!");
        }
        return false;
    }
}
