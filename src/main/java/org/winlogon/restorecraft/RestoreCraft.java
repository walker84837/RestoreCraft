package org.winlogon.restorecraft;

import org.bukkit.plugin.java.JavaPlugin;

public class RestoreCraft extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        HealCommand healCommand = new HealCommand();
        healCommand.removeEffects = getConfig().getBoolean("remove-effects", true);

        getCommand("heal").setExecutor(healCommand);
        getLogger().info("RestoreCraft enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RestoreCraft disabled!");
    }
}
