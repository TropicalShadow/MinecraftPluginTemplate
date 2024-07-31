package club.tesseract.minecraftplugintemplate;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the plugin.
 */
public final class MinecraftPluginTemplate extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("Hello, Minecraft!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("Goodbye, Minecraft!");
    }
}
