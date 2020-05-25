package io.github.radcraftplay.justfly;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommandExecutor implements CommandExecutor {

    private Server _server;

    public FlyCommandExecutor(Server server) {
        _server = server;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("fly"))
            return false;
        if (args.length > 0)
            return false;
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can be run only by players!");
            return true;
        }
        if (!_server.getAllowFlight()) {
            sender.sendMessage(ChatColor.RED + "Flying is disabled on this server!");
            return true;
        }

        Player target = (Player)sender;

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.sendMessage("Flying is now disabled!");
        }
        else {
            target.setAllowFlight(true);
            target.sendMessage("Flying is now enabled!");
        }

        return true;
    }
}
