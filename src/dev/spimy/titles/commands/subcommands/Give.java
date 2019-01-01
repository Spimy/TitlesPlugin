package dev.spimy.titles.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.spimy.titles.Main;
import dev.spimy.titles.commands.SubCommand;

public class Give implements SubCommand {

	private Main main;
	
	public Give(Main main) {
		this.main = main;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if (!sender.hasPermission("customtitles.give")) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("NoPerm")));
			return;
		}
		
		if (args.length < 2) {
			main.ChatUtils().helpMsg(sender);
			return;
		}
		
		if (args.length > 2) {
			main.ChatUtils().helpMsg(sender);
			return;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		String title = args[1];
		
		if (target == null) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("PlayerNotFound").replace("{target}", args[0])));
			return;
		}
		
		Scoreboard scoreboard = main.getServer().getScoreboardManager().getMainScoreboard();
		Team team = scoreboard.getTeam(title);
		
		if (!main.TitleConfig().getTitleConfig().contains("titles." + title) || team == null) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("TitleNotFound").replace("{title}", title)));
			return;
		}
		
		team.addEntry(target.getName());
		
		String msg = main.ChatUtils().format(main.getConfig().getString("TitleGiven"));
		msg = msg.replace("{target}", target.getName());
		msg = msg.replace("{title}", main.TitleConfig().getTitleConfig().getString("titles." + title + ".title"));
		sender.sendMessage(main.ChatUtils().format(msg));
		
	}
	
}
