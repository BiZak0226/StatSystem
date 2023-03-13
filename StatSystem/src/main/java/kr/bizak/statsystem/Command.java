package kr.bizak.statsystem;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor{

    private final StatInventory inventory;

    public Command() {
        this.inventory = new StatInventory();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 이 명령어를 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equals("open")) {
            inventory.open(player);
            return true;
        } else if (args.length == 3 && (args[0].equals("set") || args[0].equals("add"))) {
            String playerName = args[1];
            int points = Integer.parseInt(args[2]);
            // 플레이어의 스탯 포인트를 조정하는 코드

            return true;
        }

        return false;
    }
}
