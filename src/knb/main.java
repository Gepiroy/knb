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
						plist.get(i).get(0).sendMessage(ChatColor.RED + "������ 15 ������, ������ �������.");
						plist.get(i).get(1).sendMessage(ChatColor.RED + "������ 15 ������, ������ �������.");
						plist.remove(i);
					}
				}
				for(int i = 0; i < playing.size();i++){
					pint.set(i, pint.get(i)+1);
					if(pint.get(i) >= 5){
						pint.remove(i);
						playing.get(i).get(0).sendMessage(ChatColor.RED + "������ 5 ������, ���� ��������.");
						playing.get(i).get(1).sendMessage(ChatColor.RED + "������ 5 ������, ���� ��������.");
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
				if(e.getInventory().getName().equalsIgnoreCase(ChatColor.YELLOW + "���")){
					e.setCancelled(true);
				}
				else{return;}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "������!") && e.getCurrentItem().getType() == Material.STONE){
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
				else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "�������!") && e.getCurrentItem().getType() == Material.SHEARS){
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
				else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "������!") && e.getCurrentItem().getType() == Material.PAPER){
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
							playing.get(i).get(0).sendMessage(ChatColor.YELLOW + "�����.");
							playing.get(i).get(1).sendMessage(ChatColor.YELLOW + "�����.");
						}
						if(psel.get(i).get(0) == "kam" && psel.get(i).get(1) == "noz"){
							playing.get(i).get(0).sendMessage(ChatColor.GREEN + "��� ������ � ����� ������� ������� ������ " + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.GREEN + ". �� ��������!");
							playing.get(i).get(1).sendMessage(ChatColor.RED + "���� ������� ��������� �� ������ ������ "  + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.RED + ". �� ���������.");
						}
						if(psel.get(i).get(0) == "kam" && psel.get(i).get(1) == "bum"){
							playing.get(i).get(1).sendMessage(ChatColor.GREEN + "���� ������ ������� ������ ������ " + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.GREEN + ". �� ��������!");
							playing.get(i).get(0).sendMessage(ChatColor.RED + "��� ������ ������� ������ ������ ������ "  + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.RED + ". �� ���������.");
						}
						if(psel.get(i).get(0) == "noz" && psel.get(i).get(1) == "kam"){
							playing.get(i).get(1).sendMessage(ChatColor.GREEN + "��� ������ � ����� ������� ������� ������ " + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.GREEN + ". �� ��������!");
							playing.get(i).get(0).sendMessage(ChatColor.RED + "���� ������� ��������� �� ������ ������ "  + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.RED + ". �� ���������.");
						}
						if(psel.get(i).get(0) == "noz" && psel.get(i).get(1) == "noz"){
							playing.get(i).get(0).sendMessage(ChatColor.YELLOW + "�����.");
							playing.get(i).get(1).sendMessage(ChatColor.YELLOW + "�����.");
						}
						if(psel.get(i).get(0) == "noz" && psel.get(i).get(1) == "bum"){
							playing.get(i).get(0).sendMessage(ChatColor.GREEN + "���� ������� ��������� ������ ������ " + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.GREEN + " �� ��������� �������, ������� ����������� � ������. �� ��������!");
							playing.get(i).get(1).sendMessage(ChatColor.RED + "���� ������ ����� ��������� ��������, ����� ������� ������ "  + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.RED + " ��������� � �� ��������� �������. �� ���������.");
						}
						if(psel.get(i).get(0) == "bum" && psel.get(i).get(1) == "kam"){
							playing.get(i).get(0).sendMessage(ChatColor.GREEN + "���� ������ ������� ������ ������ " + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.GREEN + ". �� ��������!");
							playing.get(i).get(1).sendMessage(ChatColor.RED + "��� ������ ������� ������ ������ ������ "  + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.RED + ". �� ���������.");
						}
						if(psel.get(i).get(0) == "bum" && psel.get(i).get(1) == "noz"){
							playing.get(i).get(1).sendMessage(ChatColor.GREEN + "���� ������� ��������� ������ ������ " + ChatColor.DARK_RED + playing.get(i).get(0).getName() + ChatColor.GREEN + " �� ��������� �������, ������� ����������� � ������. �� ��������!");
							playing.get(i).get(0).sendMessage(ChatColor.RED + "���� ������ ����� ��������� ��������, ����� ������� ������ "  + ChatColor.DARK_RED + playing.get(i).get(1).getName() + ChatColor.RED + " ��������� � �� ��������� �������. �� ���������.");
						}
						if(psel.get(i).get(0) == "bum" && psel.get(i).get(1) == "bum"){
							playing.get(i).get(0).sendMessage(ChatColor.YELLOW + "�����.");
							playing.get(i).get(1).sendMessage(ChatColor.YELLOW + "�����.");
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
