package club.tesseract.minecraftplugintemplate.utils;

import club.tesseract.minecraftplugintemplate.MinecraftPluginTemplate;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class for plugin metadata.
 */
public final class PluginMetaUtils {

    @Nullable
    private static PluginMeta pluginMeta = null;


    public static PluginMeta getMeta() {
        if (pluginMeta != null){
            return pluginMeta;
        }

        pluginMeta = PluginMeta.empty();

        try(
            InputStream pluginMetaIS = MinecraftPluginTemplate.class.getResourceAsStream("plugin-meta.properties");
        ){
            Map<String, Object> pluginMetaData = new HashMap<>();
            if (pluginMetaIS == null) {
                return pluginMeta;
            }
            try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(pluginMetaIS));
            ){
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    if (parts.length < 2) {
                        continue;
                    }
                    String key = parts[0];
                    String value = String.join("=", parts).substring(key.length() + 1);

                    pluginMetaData.put(key, value);
                }
            }

            pluginMeta = PluginMeta.fromProperties(pluginMetaData);
        } catch (IOException e) {
            MinecraftPluginTemplate.getPlugin().getLogger().warning("Failed to read plugin meta file.");
            e.printStackTrace();
        }

        return pluginMeta;
    }

    public record PluginMeta(
            String pluginName,
            String pluginVersion,
            @Nullable Integer bstatsPluginId
    ) {

        public static PluginMeta fromProperties(Map<String, Object> properties) {
            Integer bstatsPluginId = (Integer) properties.getOrDefault("bstats_id", null);
            String pluginVersion = (String) properties.getOrDefault("plugin_version", MinecraftPluginTemplate.getPlugin().getPluginMeta().getVersion());
            String pluginName = (String) properties.getOrDefault("plugin_name", MinecraftPluginTemplate.getPlugin().getPluginMeta().getName());

            return new PluginMeta(
                    pluginName,
                    pluginVersion,
                    bstatsPluginId
            );

        }

        public static PluginMeta empty() {
            return new PluginMeta(
                    MinecraftPluginTemplate.getPlugin().getPluginMeta().getName(),
                    MinecraftPluginTemplate.getPlugin().getPluginMeta().getVersion(),
                    null);
        }
    }
}


