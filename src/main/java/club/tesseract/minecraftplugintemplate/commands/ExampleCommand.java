package club.tesseract.minecraftplugintemplate.commands;

import club.tesseract.minecraftplugintemplate.MinecraftPluginTemplate;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;

/**
 * An example command.
 */
@CommandAlias("example")
public class ExampleCommand extends BaseCommand {

    @Dependency
    private MinecraftPluginTemplate plugin;


    @Subcommand("default")
    @Default
    public void onDefault(CommandIssuer sender) {
        sender.sendMessage("Hello, Minecraft!");
    }

}
