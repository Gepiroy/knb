package knb;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor{
	public static ArrayList<ArrayList<Player>> plist = new ArrayList<ArrayList<Player>>();
	public static ArrayList<Integer> pintlist = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Player>> playing = new ArrayList<ArrayList<Player>>();
	public static ArrayList<Integer> pint = new ArrayList<Integer>();
	public static ArrayList<ArrayList<String>> psel = new ArrayList<ArrayList<String>>();
	@SuppressWarnings("unused")
	private main plugin;
	public command(main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender send, Command com, String label, String[] args) {
		ArrayList<Player> pla = new ArrayList<Player>();
		if(args.length == 0){
			send.sendMessage(ChatColor.YELLOW + "Чтобы играть в Камень-Ножнецы-Бумага, выберите игрока, с которым вы хотите сыграть, и введите его имя в комманде " + ChatColor.AQUA + "/knb НИК");
			return true;
		}
		if(args.length >= 2){
			send.sendMessage(ChatColor.YELLOW + "Вы можете играть в Камень-Ножнецы-Бумага сразу только с одним игроком. " + ChatColor.AQUA + "/knb НИК");
			return true;
		}
		String name = args[0];
		if(name.equalsIgnoreCase("go")){
			for(int i=0;i<plist.size();i++){
				if(plist.get(i).get(1) == send){
					GUI.OpenGUI(plist.get(i).get(1));
					GUI.OpenGUI(plist.get(i).get(0));
					playing.add(plist.get(i));
					pint.add(0);
					ArrayList<String> str = new ArrayList<String>();
					str.add(null);
					str.add(null);
					psel.add(str);
					pintlist.remove(i);
					plist.remove(i);
					return true;
				}
			}
			send.sendMessage(ChatColor.GOLD + "Вам никто не отправлял заявку/Вышло время ожидания.");
			return true;
		}
		Player p = Bukkit.getPlayerExact(name);
		if(p==null){send.sendMessage(ChatColor.YELLOW + "Этот игрок не в сети/не существует/не на этом сервере(миниигре).");return true;}
		if(name.equalsIgnoreCase(send.getName())){send.sendMessage(ChatColor.RED + "Неужели вы и в правду думаете, что мы этого не учли? >:(");return true;}
		if(p!=null){
			for(int i=0;i<plist.size();i++){
				if(plist.get(i).get(0)==p || plist.get(i).get(1)==p){
					send.sendMessage(ChatColor.RED + "Этому игроку уже отправили заявку.");
					return true;
				}
			}
			pla.add((Player) send);
			pla.add(p);
			plist.add(pla);
			pintlist.add(0);
			send.sendMessage(ChatColor.YELLOW + "Вы отправили заявку игроку " + ChatColor.GREEN + "" + p.getName() + ".");
			p.sendMessage(ChatColor.GREEN + "" + send.getName() + ChatColor.YELLOW + " Хочет сыграть с вами в Камень-Ножнецы-Бумага. Для принятия запроса, напишите /knb go");
		}
		return true;
	}
}
