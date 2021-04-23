package rotang.VultureMine.Manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Manager 
{
	private double range = 3;
	
	private double speed = 1;
	
	private float power = 5;
	
	public double getRange() 
	{
		return range;
	}
	
	public void setRange(double range) 
	{
		this.range = range;
	}

	public double getSpeed() 
	{
		return speed;
	}

	public void setSpeed(double speed) 
	{
		this.speed = speed;
	}
	
	public float getPower()
	{
		return power;
	}
	
	public void setPower(float power)
	{
		this.power = power;
	}
	
	//=========================
	
	public ItemStack getitem()
	{
		ItemStack setUpMine = new ItemStack(Material.RED_DYE);
		ItemMeta meta = setUpMine.getItemMeta();
		
		meta.setDisplayName(ChatColor.RED + "Set UP Mine");
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		setUpMine.setItemMeta(meta);
		
		return setUpMine;
	}
}
