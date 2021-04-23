package rotang.VultureMine.Manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener
{
	private Manager manager;
	
	public Events(Manager manager) 
	{
		this.manager = manager;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		Action action = event.getAction();
		
		if(item == null || item.getType().equals(Material.AIR))
			return;
			
		if(item.equals(manager.getitem()))
		{
			if(event.getHand().equals(EquipmentSlot.HAND))
			{
				if(action.equals(Action.RIGHT_CLICK_BLOCK))
				{
					Mine mine = new Mine();
					mine.setUpMine(player, event.getClickedBlock().getLocation().add(0.5, -1.2, 0.5));
				}
			}
		}
	}
}
