package club.tesseract.minecraftplugintemplate;

import club.tesseract.minecraftplugintemplate.commands.ExampleCommand;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the plugin.
 */
public final class MinecraftPluginTemplate extends JavaPlugin {

    private static PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new ExampleCommand());

        commandManager.setDefaultExceptionHandler((command, registeredCommand, sender, args, t) -> {
            sender.sendMessage("An error occurred while executing the command.");
            getLogger().warning("Error occured while executing command " + command.getName());
            getLogger().severe(t.getMessage());
            return true; // mark as handled to prevent further handlers from being called.
        });
        this.getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        this.getLogger().info("Plugin disabled!");
    }

    public static PaperCommandManager getCommandManager() {
        return commandManager;
    }

    public static MinecraftPluginTemplate getPlugin() {
        return MinecraftPluginTemplate.getPlugin(MinecraftPluginTemplate.class);
    }
}
