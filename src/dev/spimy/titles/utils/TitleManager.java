package dev.spimy.titles.utils;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.spimy.titles.Main;

public class TitleManager {
	
	public void RegisterTitle(Main main, String title_name, String title) {
		Scoreboard scoreboard = main.getServer().getScoreboardManager().getMainScoreboard();
		Team team = scoreboard.registerNewTeam(title_name);
		team.setPrefix(main.ChatUtils().format(title + " "));
	}
	
	public void unRegisterTitle(Main main, String title) {
		Scoreboard scoreboard = main.getServer().getScoreboardManager().getMainScoreboard();
		Team team = scoreboard.getTeam(title);
		team.unregister();
	}
	
}
