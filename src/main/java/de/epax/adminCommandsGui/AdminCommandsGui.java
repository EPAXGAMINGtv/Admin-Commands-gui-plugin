package de.epax.adminCommandsGui;

import de.epax.adminCommandsGui.commands.AdminInvOpenCommands;
import de.epax.adminCommandsGui.listener.OnClick;
import de.epax.adminCommandsGui.listener.OnClickPlayerSelection;
import de.epax.adminCommandsGui.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Material.ARROW;

public final class AdminCommandsGui extends JavaPlugin {


    @Override
    public void onEnable() {
        getCommand("admingui").setExecutor(new AdminInvOpenCommands());
        getServer().getPluginManager().registerEvents(new OnClick(),this);
        getServer().getPluginManager().registerEvents(new OnClickPlayerSelection(),this);
    }

    @Override
    public void onDisable() {


    }

    public static void openPlayerMenu(int page, Player player) {
        final HashMap<UUID, Integer> playerPages = new HashMap<>();

        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        int maxPlayersPerPage = 45;
        int totalPages = (int) Math.ceil((double) onlinePlayers.size() / maxPlayersPerPage);

        if (page < 0) page = 0;
        if (page >= totalPages) page = totalPages - 1;

        Inventory SelectPlayer = Bukkit.createInventory(null, 9 * 6, "§cSeletedPlayer");

        for (int i = 0; i < 9 * 6; i++) {
            SelectPlayer.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayname("").build());
        }
        int start = page * maxPlayersPerPage;
        int end = Math.min(start + maxPlayersPerPage, onlinePlayers.size());
        for (int i = start; i < end; i++) {
            Player target = onlinePlayers.get(i);
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            if (meta != null) {
                meta.setOwningPlayer(target);
                meta.setDisplayName(target.getName());
                skull.setItemMeta(meta);
            }
            SelectPlayer.setItem(i - start, skull);
        }
        if (page > 0) {
            SelectPlayer.setItem(45, new ItemBuilder(ARROW).setDisplayname("← Vorherige Seite").build());
        }
        if (page < totalPages - 1) {
            SelectPlayer.setItem(53, new ItemBuilder(ARROW).setDisplayname("→ Nächste Seite").build());
        }
        playerPages.put(player.getUniqueId(), page);
        player.openInventory(SelectPlayer);
    }

}
