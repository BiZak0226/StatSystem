package kr.bizak.statsystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Event implements Listener {
    @EventHandler
    public void displayDamage(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            double damage = event.getDamage();
            player.sendMessage("" +
                    ChatColor.GRAY + "[Magpie]" +
                    ChatColor.WHITE + ":: " + event.getEntity().getName() + " " +
                    ChatColor.RED + damage );
        }
    }
    @EventHandler
    public void entityDeath(EntityDeathEvent event){
        Player player;
        Player killer;
        if (!(event.getEntity().getKiller() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)){
            player = event.getEntity().getKiller();
            player.sendMessage(ChatColor.GRAY + "[Magpie]" +
                    ChatColor.WHITE + ":: Killed " + event.getEntity().getName());
        } else if (event.getEntity() instanceof Player) {
            killer = event.getEntity().getKiller().getPlayer();
            player = ((Player) event.getEntity()).getPlayer();

            player.sendMessage("" +
                    ChatColor.GRAY + "[Magpie]" +
                    ChatColor.WHITE + ":: " +
                    ChatColor.RED + killer.getDisplayName() +
                    ChatColor.WHITE + " killed " +
                    ChatColor.DARK_RED + player.getDisplayName());

            killer.sendMessage("" +
                    ChatColor.GRAY + "[Magpie]" +
                    ChatColor.WHITE + ":: " +
                    ChatColor.RED + killer.getDisplayName() +
                    ChatColor.WHITE + " killed " +
                    ChatColor.DARK_RED + player.getDisplayName());

        }

    }
}
