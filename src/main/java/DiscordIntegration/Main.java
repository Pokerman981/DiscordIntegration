package DiscordIntegration;

import DiscordIntegration.DiscordCommands.CheckPinCommand;
import DiscordIntegration.DiscordCommands.ExecuteServerCommand;
import DiscordIntegration.ServerCommands.linkCommand;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "discordintegration", name = "DiscordIntegration", version = "1.0", dependencies = {
        @Dependency(id = "luckperms", optional = false)
})
public class Main {

    private static String token;
    public static JDA jda;

    @Listener
    public void onInit(GameInitializationEvent event) {
        instance = this;
        try {
            //
            rootNode = loader.load();
            genConfig();
            populateVariables();
            registerCommands();
        } catch (IOException e) {
            //
            e.printStackTrace();
        }




        try {new Login(token);} catch (RateLimitedException | LoginException | InterruptedException e) {e.printStackTrace();}

        CommandClientBuilder ccb = new CommandClientBuilder()
                .setPrefix("!")
                .setAlternativePrefix("..")
                .setEmojis("\u2705", "\uD83D\uDCA1", "\uD83D\uDEAB") //Unicode emojis
                .setOwnerId(Ref.ownerid)
                .addCommands(
                        new ExecuteServerCommand(),
                        new DiscordIntegration.DiscordCommands.linkCommand(),
                        new CheckPinCommand()
                );
        EventWaiter waiter = new EventWaiter();

        jda.addEventListener(waiter);
        jda.addEventListener(ccb.build());


    }

    private void genConfig() throws IOException{
        if (rootNode.getNode("token").isVirtual()){
            rootNode.getNode("token").setValue("Change Me");
        }
        save();
    }

    private void populateVariables(){
        token = rootNode.getNode("token").getString();
    }

    private void registerCommands() {
        CommandSpec link = CommandSpec.builder().executor(new linkCommand()).build();
        Sponge.getCommandManager().register(this, link, Lists.newArrayList("link"));
    }







































































    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = false)
    public ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    @org.spongepowered.api.config.ConfigDir(sharedRoot = false)
    private Path ConfigDir;

    @Inject
    public PluginContainer plugin;
    public PluginContainer getPlugin() {
        return this.plugin;
    }

    public static CommentedConfigurationNode rootNode;

    public static CommentedConfigurationNode config() {
        return rootNode;
    }

    public void save() throws IOException {
        loader.save(rootNode);
    }

    @Inject
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }


}
