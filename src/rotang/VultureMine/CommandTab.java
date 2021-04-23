package rotang.VultureMine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandTab implements TabCompleter
{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) 
	{
		if(args.length == 1)
		{
			List<String> arguments = Arrays.asList("item", "range", "speed", "power", "info");
			List<String> result = new ArrayList<>();
			
			for(String a : arguments)
			{
				if(a.toLowerCase().startsWith(args[0].toLowerCase()))
				{
					result.add(a);
				}
			}
			return result;
		}
		return null;
	}

}
