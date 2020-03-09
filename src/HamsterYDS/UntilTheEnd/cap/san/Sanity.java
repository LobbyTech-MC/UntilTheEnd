package HamsterYDS.UntilTheEnd.cap.san;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import HamsterYDS.UntilTheEnd.Config;
import HamsterYDS.UntilTheEnd.UntilTheEnd;

/**
 * @author 南外丶仓鼠
 * @version V5.1.1
 */
public class Sanity {
	public static UntilTheEnd plugin;
	public static YamlConfiguration yaml;
	public Sanity(UntilTheEnd plugin) {
		this.plugin=plugin;
		Config.autoUpdateConfigs("sanity.yml");
		File file=new File(plugin.getDataFolder(),"sanity.yml");
		yaml=YamlConfiguration.loadConfiguration(file);
		System.out.println("[UntilTheEnd]正在加载理智计算模块......");
		SanityProvider.loadAura();
		new ChangeTasks(plugin);
		new InfluenceTasks(plugin);
		plugin.getServer().getPluginManager().registerEvents(new InfluenceEvents(), plugin);
	}
}
