package dev.spimy.titles.commands;

import org.bukkit.command.CommandSender;

public interface SubCommand {

	public abstract void execute(CommandSender sender, String[] args);
	
}
