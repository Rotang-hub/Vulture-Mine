package rotang.VultureMine;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import rotang.VultureMine.Manager.Events;
import rotang.VultureMine.Manager.Manager;

public class VultureMine extends JavaPlugin
{
	private Manager manager;
	
	@Override
	public void onEnable() 
	{
		if(manager == null)
			manager = new Manager();
		
		getServer().getPluginManager().registerEvents(new Events(manager), this);
		getCommand("vm").setTabCompleter(new CommandTab());
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Vulture Mine 플러그인 활성화");
	}
	
	public void onDisable()
	{
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Vulture Mine 플러그인 비활성화");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase("vm"))
		{
			if(args[0].equalsIgnoreCase("item"))
			{
				player.getInventory().addItem(manager.getitem());
			}
			
			if(args[0].equalsIgnoreCase("range"))
			{
				double range = Double.parseDouble(args[1]);
				
				getServer().broadcastMessage(ChatColor.GREEN + "[VM] 범위 : " + ChatColor.YELLOW + manager.getRange() + " -> " + range);
				manager.setRange(range);
			}
			
			if(args[0].equalsIgnoreCase("speed"))
			{
				double speed = Double.parseDouble(args[1]);
				
				getServer().broadcastMessage(ChatColor.GREEN + "[VM] 속도 : " + ChatColor.YELLOW + manager.getSpeed() + " -> " + speed);
				manager.setSpeed(speed);
			}
			
			if(args[0].equalsIgnoreCase("power"))
			{
				float power = Float.parseFloat(args[1]);
				
				getServer().broadcastMessage(ChatColor.GREEN + "[VM] 위력 : " + ChatColor.YELLOW + manager.getPower() + " -> " + power);
				manager.setPower(power);
			}
			
			if(args[0].equalsIgnoreCase("info"))
			{
				player.sendMessage(ChatColor.GREEN + "[VM] 범위 : " + ChatColor.YELLOW + manager.getRange());
				player.sendMessage(ChatColor.GREEN + "[VM] 속도 : " + ChatColor.YELLOW + manager.getSpeed());
				player.sendMessage(ChatColor.GREEN + "[VM] 위력 : " + ChatColor.YELLOW + manager.getPower());
			}
			
			if(args[0].equalsIgnoreCase("help"))
			{
				player.sendMessage(ChatColor.GREEN + "/vm item : " + ChatColor.YELLOW + "지뢰 설치기를 획득합니다");
				player.sendMessage(ChatColor.GREEN + "/vm range [범위]: " + ChatColor.YELLOW + "지뢰의 감지 범위를 설정합니다");
				player.sendMessage(ChatColor.GREEN + "/vm speed [속도]: " + ChatColor.YELLOW + "지뢰의 추적 속도를 설정합니다");
				player.sendMessage(ChatColor.GREEN + "/vm power [위력]: " + ChatColor.YELLOW + "지뢰의 폭발 위력을 설정합니다");
				player.sendMessage(ChatColor.GREEN + "/vm info : " + ChatColor.YELLOW + "현재 설정된 지뢰의 정보를 보여줍니다");
			}
		}
		
		return false;
	}
	
}
