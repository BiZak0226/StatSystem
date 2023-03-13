package kr.bizak.statsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class StatSystem extends JavaPlugin {

    ConsoleCommandSender consol = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        // Plugin startup logic
        consol.sendMessage(ChatColor.AQUA + "#========#========#========#========#========#========#");
        consol.sendMessage("" +
                ChatColor.GRAY + "[Magpie]" +
                ChatColor.WHITE + ":: Stat System Plugin " +
                ChatColor.AQUA +"Enabled");
        consol.sendMessage(ChatColor.AQUA + "#========#========#========#========#========#========#");

        // 이벤트 등록
        getServer().getPluginManager().registerEvents(new StatInventory(), this);
        getServer().getPluginManager().registerEvents(new Event(), this);

        getCommand("stat").setExecutor(new Command());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        consol.sendMessage(ChatColor.RED + "#========#========#========#========#========#========#");
        consol.sendMessage("" +
                ChatColor.GRAY + "[Magpie]" +
                ChatColor.WHITE + ":: Stat System Plugin " +
                ChatColor.RED +"Disabled");
        consol.sendMessage(ChatColor.RED + "#========#========#========#========#========#========#");
    }
}
