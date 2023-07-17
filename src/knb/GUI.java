package knb;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import utils.ItemUtil;


public class GUI implements Listener {
	public static void OpenGUI(Player p){
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.YELLOW + "���");
		inv.setItem(10,ItemUtil.create(Material.STONE, ChatColor.GOLD + "������!"));
		inv.setItem(13,ItemUtil.create(Material.SHEARS, ChatColor.GOLD + "�������!"));
		inv.setItem(16,ItemUtil.create(Material.PAPER, ChatColor.GOLD + "������!"));
		p.openInventory(inv);
		
	}
}
