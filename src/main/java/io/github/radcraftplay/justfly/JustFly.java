package io.github.radcraftplay.justfly;

import org.bukkit.plugin.java.JavaPlugin;

public class JustFly extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginCommand("fly").setExecutor(new FlyCommandExecutor(getServer()));
    }
}
