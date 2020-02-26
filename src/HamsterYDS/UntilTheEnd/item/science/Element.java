package HamsterYDS.UntilTheEnd.item.science;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import HamsterYDS.UntilTheEnd.api.UntilTheEndApi;
import HamsterYDS.UntilTheEnd.cap.hum.Humidity;
import HamsterYDS.UntilTheEnd.guide.CraftGuide;
import HamsterYDS.UntilTheEnd.item.ItemLoader;
import HamsterYDS.UntilTheEnd.item.ItemProvider;
import HamsterYDS.UntilTheEnd.item.materials.Brick;
import HamsterYDS.UntilTheEnd.item.materials.Hail;

/**
 * @author 南外丶仓鼠
 * @version V5.1.1
 */
public class Element implements Listener{
	public static ItemStack item;
	public static NamespacedKey nsk=new NamespacedKey(Humidity.plugin,"ute.element");
	public Element() {		
		ShapelessRecipe recipe=new ShapelessRecipe(nsk,item);
		recipe.addIngredient(6,Brick.item.getType());
		recipe.addIngredient(1,Hail.item.getType());
		recipe.addIngredient(2,Material.GOLD_INGOT);
		Bukkit.addRecipe(recipe); 
		ItemLoader.plugin.getServer().getPluginManager().registerEvents(this,ItemLoader.plugin);
		ItemProvider.addItem(this.getClass(),item);

		Inventory inv=CraftGuide.getCraftInventory();
		inv.setItem(11,item);
		ItemStack item6=Brick.item.clone();
		item6.setAmount(6);
		inv.setItem(14,item6);
		inv.setItem(15,Hail.item);
		inv.setItem(16,new ItemStack(Material.GOLD_INGOT,2));
		UntilTheEndApi.GuideApi.addCraftToItem(item,inv);
		UntilTheEndApi.GuideApi.addItemToCategory("§6科学",item);
	}
	
	@EventHandler public void onCraft1(CraftItemEvent event) {
		ItemStack item=event.getRecipe().getResult();
        item.setAmount(1);
        if (item.equals(this.item)) {
            if (!event.getInventory().containsAtLeast(Brick.item,6)) {
                event.setCancelled(true);
            }
            if (!event.getInventory().containsAtLeast(Hail.item,1)) {
                event.setCancelled(true);
            }
        }
	}
}
