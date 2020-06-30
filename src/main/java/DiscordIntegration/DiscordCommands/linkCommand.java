package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.user.UserStorageService;

import java.io.IOException;
import java.util.Optional;

public class linkCommand extends Command {

    public linkCommand(){
        this.name = "link";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (!event.isFromType(ChannelType.PRIVATE)) return;

        String[] command = event.getArgs().split(" ");
        String authorID = event.getAuthor().getId();


        if (command.length != 2) {
            event.reply("You must supply your username then pin!");
            return;
        }

        Task.builder()
                .execute(task -> {
                    Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
                    if (!userStorage.get().get(command[0]).isPresent()) {
                        event.reply("Unable to find your name in our records");
                        return;
                    }

                    String name = command[0];
                    String uuid = userStorage.get().get(name).get().getIdentifier();
                    String pin = command[1];
                    boolean requestedLink = Main.config().getNode("linked-info", uuid).isVirtual();
                    boolean linked = !Main.config().getNode("linked-info", uuid, "linked").isVirtual();

                    if (requestedLink) {
                        event.replyError("You must do /link in-game first!");
                        return;
                    }
                    if (linked) {
                        event.replyError("You've already linked your account!");
                        return;
                    }

                    if (!pin.equals(Main.config().getNode("linked-info", uuid, "pin").getString())) {
                        event.reply("Your pin does not match the pin on our files!");
                        return;
                    }
                    Guild guild = event.getJDA().getGuildById("258797004757532672");

                    Main.config().getNode("linked-info", uuid, "linked").setValue(true);
                    try {Main.getInstance().save();} catch (IOException e) {e.printStackTrace();}

                    String server = guild.getSelfMember().getNickname() == null ? guild.getSelfMember().getEffectiveName().toLowerCase() : guild.getSelfMember().getNickname().toLowerCase();

                    guild.getController().addSingleRoleToMember(guild.getMemberById(authorID), guild.getRoleById(Main.donorRankIDS.get(server))).queue();

                    event.replySuccess("You are now able to talk in the server chat");

                })
                .submit(Main.getInstance());
    }
}
