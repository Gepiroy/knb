package knb;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import knb.GUI;

public class main extends JavaPlugin implements Listener{
	ArrayList<ArrayList<Player>> plist = command.plist;
	ArrayList<Integer> pintlist = command.pintlist;
	ArrayList<ArrayList<Player>> playing = command.playing;
	ArrayList<Integer> pint = command.pint;
	ArrayList<ArrayList<String>> psel = command.psel;
	public void onEnable(){
		getLogger().info(ChatColor.YELLOW + "Kamen-Noznetci-Bumaga!");
		getCommand("knb").setExecutor(new command(this));
		Bukkit.getPluginManager().registerEvents(new GUI(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
			
			@Override
			public void run() {
				for(int i = 0; i < pintlist.size();i++){
					pintlist.set(i, pintlist.get(i)+1);
					if(pintlist.get(i) >= 15){
						pintlist.remove(i);
						plist.get(i).get(0).sendMessage(ChatColor.RED + "Прошло 15 секунд, запрос отклонён.");
						plist.get(i).get(1).sendMessage(ChatColor.RED + "Прошло 15 секунд, запрос отклонён.");
						plist.remove(i);
					}
				}
				for(int i = 0; i < playing.size();i++){
					pint.set(i, pint.get(i)+1);
					if(pint.get(i) >= 5){
						pint.remove(i);
						playing.get(i).get(0).sendMessage(ChatColor.RED + "Прошло 5 секунд, игра окончена.");
						playing.get(i).get(1).sendMessage(ChatColor.RED + "Прошло 5 секунд, игра окончена.");
						playing.get(i).get(0).closeInventory();
						playing.get(i).get(1).closeInventory();
						playing.remove(i);
					}
				}
			}
			
			
        }, 20, 20);
	}
	public void onDisable(){
		getLogger().info(ChatColor.RED + "kamen,noznetci,bumaga... ;((((");
	}
	
	@EventHandler
	public void select(InventoryClickEvent e){
		if(e.getClickedInventory() != null) {
			if(e.getCurrentItem().getItemMeta()!=null){
				Player p = (Player) e.getWhoClicked();
				if(e.getInventory().getName().equalsIgnoreCase(ChatColor.YELLOW + "КНБ")){
					e.setCancelled(true);
				}
				else{return;}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Камень!") && e.getCurrentItem().getType() == Material.STONE){
					for(int i=0; i<playing.size();i++){
						if(playing.get(i).get(0) == p){
							psel.get(i).set(0, "kam");
							p.closeInventory();
						}
						if(playing.get(i).get(1) == p){
							psel.get(i).set(1, "kam");
							p.closeInventory();
						}
					}
					p.closeInventory();
				}
				else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Ножнецы!") && e.getCurrentItem().getType() == Material.SHEARS){
					for(int i=0; i<playing.size();i++){
						if(playing.get(i).get(0) == p){
							psel.get(i).set(0, "noz");
							p.closeInventory();
						}
						if(playing.get(i).get(1) == p){
							psel.get(i).set(1, "noz");
							p.closeInventory();
						}
					}
					p.closeInventory();
				}
				else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Бумага!") && e.getCurrentItem().getType() == Material.PAPER){
					for(int i=0; i<playing.size();i++){
						if(playing.get(i).get(0) == p){
							psel.get(i).set(0, "bum");
							p.closeInventory();
						}
						if(playing.get(i).get(1) == p){
							psel.get(i).set(1, "bum");
							p.closeInventory();
						}
					}
					p.closeInventory();
				}
				for(int i=0;i<playing.size();i++){
					if(psel.get(i).get(0) != null && psel.get(i).get(1) != null){
						if(psel.get(i).get(0) == "kam" && psel.get(i).get(1) == "kam"){
							playing.get(i).get(0).sendMessage(ChatColor.YELLOW + "Ничья.");
							playing.get(i).get(1).sendMessage(ChatColor.YELLOW + "Ничья.");
						}
						if(psel.get(i).get(0) == "kam" && psel.get(i).get(1) == "noz"){
							playing.get(i).get(0).sendMessage(ChatColor.GREEN + "Ваш камень в конец затупил ножнецы игрока " + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.GREEN + ". Вы победили!");
							playing.get(i).get(1).sendMessage(ChatColor.RED + "Ваши ножнецы сломались об камень игрока "  + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.RED + ". Вы проиграли.");
						}
						if(psel.get(i).get(0) == "kam" && psel.get(i).get(1) == "bum"){
							playing.get(i).get(1).sendMessage(ChatColor.GREEN + "Ваша бумага сожрала камень игрока " + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.GREEN + ". Вы победили!");
							playing.get(i).get(0).sendMessage(ChatColor.RED + "Ваш камень застрял внутри бумаги игрока "  + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.RED + ". Вы проиграли.");
						}
						if(psel.get(i).get(0) == "noz" && psel.get(i).get(1) == "kam"){
							playing.get(i).get(1).sendMessage(ChatColor.GREEN + "Ваш камень в конец затупил ножнецы игрока " + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.GREEN + ". Вы победили!");
							playing.get(i).get(0).sendMessage(ChatColor.RED + "Ваши ножнецы сломались об камень игрока "  + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.RED + ". Вы проиграли.");
						}
						if(psel.get(i).get(0) == "noz" && psel.get(i).get(1) == "noz"){
							playing.get(i).get(0).sendMessage(ChatColor.YELLOW + "Ничья.");
							playing.get(i).get(1).sendMessage(ChatColor.YELLOW + "Ничья.");
						}
						if(psel.get(i).get(0) == "noz" && psel.get(i).get(1) == "bum"){
							playing.get(i).get(0).sendMessage(ChatColor.GREEN + "Ваши ножнецы разрезали бумагу игрока " + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.GREEN + " на маленькие кусочки, которые разбежались в панике. Вы победили!");
							playing.get(i).get(1).sendMessage(ChatColor.RED + "Ваша бумага стала спасаться бегством, когда ножнецы игрока "  + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.RED + " разрезали её на маленькие кусочки. Вы проиграли.");
						}
						if(psel.get(i).get(0) == "bum" && psel.get(i).get(1) == "kam"){
							playing.get(i).get(0).sendMessage(ChatColor.GREEN + "Ваша бумага сожрала камень игрока " + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.GREEN + ". Вы победили!");
							playing.get(i).get(1).sendMessage(ChatColor.RED + "Ваш камень застрял внутри бумаги игрока "  + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.RED + ". Вы проиграли.");
						}
						if(psel.get(i).get(0) == "bum" && psel.get(i).get(1) == "noz"){
							playing.get(i).get(1).sendMessage(ChatColor.GREEN + "Ваши ножнецы разрезали бумагу игрока " + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.GREEN + " на маленькие кусочки, которые разбежались в панике. Вы победили!");
							playing.get(i).get(0).sendMessage(ChatColor.RED + "Ваша бумага стала спасаться бегством, когда ножнецы игрока "  + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.RED + " разрезали её на маленькие кусочки. Вы проиграли.");
						}
						if(psel.get(i).get(0) == "bum" && psel.get(i).get(1) == "bum"){
							playing.get(i).get(0).sendMessage(ChatColor.YELLOW + "Ничья.");
							playing.get(i).get(1).sendMessage(ChatColor.YELLOW + "Ничья.");
						}
						playing.remove(i);
						pint.remove(i);
						psel.remove(i);
					}
				}
			}
		}
	}
}
