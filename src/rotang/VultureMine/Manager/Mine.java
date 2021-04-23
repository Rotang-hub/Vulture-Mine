package rotang.VultureMine.Manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import rotang.VultureMine.VultureMine;

public class Mine 
{
	private Plugin plugin = VultureMine.getPlugin(VultureMine.class);
	
	private Manager manager = new Manager();
	
	private Player miner;
	
	private Location loc;
	
	private double range = manager.getRange();
	
	private double speed = manager.getSpeed();
	
	private float power = manager.getPower();
	
	public Player getMiner() 
	{
		return miner;
	}

	public void setMiner(Player miner) 
	{
		this.miner = miner;
	}

	public Location getLoc() 
	{
		return loc;
	}

	public void setLoc(Location loc) 
	{
		this.loc = loc;
	}
	
	//================
	
	public void setUpMine(Player miner, Location loc)
	{
		setMiner(miner);
		setLoc(loc);
		
		ArmorStand mine = (ArmorStand) miner.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		mine.setVisible(false);
		mine.setSilent(true);
		mine.setGravity(false);
		mine.getEquipment().setHelmet(new ItemStack(Material.TNT));
		
		miner.sendMessage(ChatColor.GREEN + "[VM] 지뢰를 심었습니다." + ChatColor.YELLOW + "[" + loc.getX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + "]");
		
		new BukkitRunnable() 
		{
			double dist;
			
			@Override
			public void run() 
			{
				for(Player p : plugin.getServer().getOnlinePlayers())
				{
					if(p == miner)
						continue;
					
					dist = loc.distance(p.getLocation());
					
					if(dist <= range)
					{
						traceTarget(mine, p);
						cancel();
					}
				}
			}
		}.runTaskTimer(plugin, 0, 2);	//  10tick/1s
	}
	
	public void traceTarget(ArmorStand mine, Player target)
	{
		new BukkitRunnable()
		{
			int tick = 0;
			
			@Override
			public void run() 
			{
				Location mineLoc = mine.getLocation();
				
				if(tick <= 20)
				{
					Location targetPos = mineLoc.add(0, 0.05, 0);
					
					mine.teleport(targetPos);
					tick++;
				}
				
				else if(tick > 20)
				{
					Vector dir = mineLoc.toVector().subtract(target.getLocation().toVector()).normalize();
					Location targetPos = mineLoc.add(dir.multiply(speed / 10));
					//Vector vec = mineLoc.toVector().subtract(targetPos.toVector());
					
					//mine.setVelocity(vec);
					mine.getLocation().setDirection(dir);
					mine.teleport(targetPos);
					mineLoc.getWorld().spawnParticle(Particle.FLAME, 0, 0, 0, 1);
					
					if(mineLoc.distance(target.getLocation()) <= 0.5)
					{
						mineLoc.getWorld().createExplosion(mineLoc, power);
						mine.remove();
						cancel();
					}
				}
			}
		}.runTaskTimer(plugin, 0, 2);	//  10tick/1s
	}
}
