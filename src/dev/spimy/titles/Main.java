package dev.spimy.titles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.spimy.titles.commands.Title;
import dev.spimy.titles.data.TitlesConfig;
import dev.spimy.titles.utils.ChatUtils;
import dev.spimy.titles.utils.TitleManager;

public class Main extends JavaPlugin {
	
	TitlesConfig titles;
	ChatUtils chatutils;
	TitleManager titlemanager = new TitleManager();

	public void onEnable() {
		
		getServer().getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				
				if (!TitleConfig().getTitleConfig().contains("titles")) return;
				for (String key : TitleConfig().getTitleConfig().getConfigurationSection("titles").getKeys(false)) {
					
					Scoreboard scoreboard = getServer().getScoreboardManager().getMainScoreboard();
					Team team = scoreboard.getTeam(key);
					
					String prefix = TitleConfig().getTitleConfig().getString("titles." + key + ".title");
					String perm = TitleConfig().getTitleConfig().getString("titles." + key + ".permission");
					
					team.setPrefix(ChatUtils().format(prefix));
					
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (player.hasPermission(perm) && !player.isOp()) {
							team.addEntry(player.getName());
						} else if (!player.hasPermission(perm) && player.isOp()) {
							team.addEntry(player.getName());
						} else continue;
					}
				}
			}
			
		}, 0, 40);
		
		titles = new TitlesConfig(this);
		chatutils = new ChatUtils(this);
		saveDefaultConfig();
		this.getCommand("title").setExecutor(new Title(this));
		getLogger().info(
				String.format("%s Version %s Loaded!", getDescription().getName(), getDescription().getVersion()));
	}

	public void onDisable() {
		getLogger().info(
				String.format("%s Version %s Disabled!", getDescription().getName(), getDescription().getVersion()));
	}
	
	public TitlesConfig TitleConfig() {
		return this.titles;
	}
	
	public ChatUtils ChatUtils() {
		return this.chatutils;
	}

}
