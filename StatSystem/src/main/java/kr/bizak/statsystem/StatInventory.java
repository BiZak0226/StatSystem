package kr.bizak.statsystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StatInventory implements Listener {
    private final Inventory inventory;
    public StatInventory() {
        this.inventory = Bukkit.createInventory(null, 6 * 9, "스탯");
//        ItemStack itemstack = new ItemStack(Material.IRON_SWORD);
//        inv.addItem(itemstack);
        // 철 검(공격력)
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName("§f철 검");
        List<String> swordLore = new ArrayList<>();
        swordLore.add("§f공격 : 0+");
        swordMeta.setLore(swordLore);
        sword.setItemMeta(swordMeta);
        inventory.setItem(0, sword);

        // 붉은색 염료(체력)
        ItemStack dye = new ItemStack(Material.RED_DYE);
        ItemMeta dyeMeta = dye.getItemMeta();
        dyeMeta.setDisplayName("§f붉은색 염료");
        List<String> dyeLore = new ArrayList<>();
        dyeLore.add("§f체력 : 0+");
        dyeMeta.setLore(dyeLore);
        dye.setItemMeta(dyeMeta);
        inventory.setItem(1, dye);

        // 깃털(이동 속도)
        ItemStack feather = new ItemStack(Material.FEATHER);
        ItemMeta featherMeta = feather.getItemMeta();
        featherMeta.setDisplayName("§f깃털");
        List<String> featherLore = new ArrayList<>();
        featherLore.add("§f이동 : 0%");
        featherMeta.setLore(featherLore);
        feather.setItemMeta(featherMeta);
        inventory.setItem(2, feather);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

//    @EventHandler
//    public void onInventoryClick(InventoryClickEvent event) {
//        Player player = (Player) event.getWhoClicked();
//        ItemStack clickedItem = event.getCurrentItem();
//
//        if (event.getView().getTitle().equals("스탯") && clickedItem != null) {
//            event.setCancelled(true);
//
//            // 아이템 클릭 시 스탯을 증가 또는 감소하는 코드
//        }
//    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 인벤토리가 스탯 인벤토리인지 확인

        ItemStack clickedItemIcon = event.getCurrentItem();
        if (event.getView().getTitle().equals("스탯") && clickedItemIcon != null) {
            event.setCancelled(true);

            // 아이템 클릭 이벤트 처리
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getClickedInventory();

            if (inventory == null) {
                return;
            }

            // 스탯을 올리는 아이템인지 확인
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || !clickedItem.getItemMeta().hasLore()) {
                return;
            }

            // 자수정 조각 소모
            ItemStack gem = new ItemStack(Material.AMETHYST_SHARD);
            if (!player.getInventory().containsAtLeast(gem, 1)) {
                player.sendMessage("§c자수정 조각이 없습니다.");
                return;
            }
            player.getInventory().removeItem(gem);

            // 스탯 증가
            ItemMeta clickedMeta = clickedItem.getItemMeta();
            List<String> clickedLore = clickedMeta.getLore();
            player.sendMessage("clickedLore :: " + clickedLore.toString());
            if (clickedLore == null || clickedLore.isEmpty()) {
                return;
            }
            String statType = clickedLore.get(0).substring(2, 4);
            player.sendMessage("statType :: " + statType);
            int statValue = Integer.parseInt(clickedLore.get(0).substring(7, clickedLore.get(0).length()-1));
            player.sendMessage("statValue :: " + statValue);
            if (statType.equals("공격")) {
                statValue += 1;
                player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(statValue+player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
            } else if (statType.equals("체력")) {
                statValue += 1;
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(statValue * 2 + player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            } else if (statType.equals("이동")) {
                statValue += (int) Math.round(statValue * 0.01);
                player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(statValue * 0.1 + player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue());
            } else {
                return;
            }

            clickedLore.set(0, "§f" + statType + " : " + statValue + "+");
            player.sendMessage("clickedLore" + clickedLore);
            clickedMeta.setLore(clickedLore);
            clickedItem.setItemMeta(clickedMeta);

            player.sendMessage("§a" + statType + " 스탯이 1 증가하였습니다.");
        }
    }
}
