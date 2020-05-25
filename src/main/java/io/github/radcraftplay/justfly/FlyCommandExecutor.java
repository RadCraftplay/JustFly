package io.github.radcraftplay.justfly;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommandExecutor implements CommandExecutor {

    private final Server _server;
    static final String NO_PERMISSIONS_MESSAGE = ChatColor.RED + "You do not have permissions to do this!";

    public FlyCommandExecutor(Server server) {
        _server = server;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;

        if (!command.getName().equalsIgnoreCase("fly"))
            return false;

        if (args.length == 1) {
            if (!checkPermission(sender, "justfly.flyothers"))
                return true;
            target = _server.getPlayer(args[0]);
        } else if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can be run only by players!");
                return true;
            }
            if (!checkPermission(sender, "justfly.fly"))
                return true;

            target = (Player)sender;
        } else {
            return false;
        }

        if (target == null) {
            sender.sendMessage(String.format("Unable to find player with nickname '%s'", args[0]));
            return false;
        }

        if (!_server.getAllowFlight()) {
            sender.sendMessage(ChatColor.RED + "Flying is disabled on this server!");
            return true;
        }

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.sendMessage("Flying is now" + ChatColor.RED + " disabled!");
        }
        else {
            target.setAllowFlight(true);
            target.sendMessage("Flying is now" + ChatColor.GREEN + " enabled!");
        }

        return true;
    }

    Boolean checkPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(NO_PERMISSIONS_MESSAGE);
            return false;
        }

        return true;
    }
}
