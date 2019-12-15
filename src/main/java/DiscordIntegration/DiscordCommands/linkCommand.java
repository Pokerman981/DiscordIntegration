package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import java.io.IOException;
import java.util.Optional;

public class linkCommand extends Command {

    public linkCommand(){
        this.name = "link";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] command = event.getArgs().split(" ");
        String authorID = event.getAuthor().getId();

        try {
            String id = event.getGuild().getId();
            if (!id.isEmpty()) {
                return;
            }
        } catch (NullPointerException e) {
            //Nothing
        }

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
                        event.reply("You must do /link in-game first!");
                        return;
                    }
                    if (linked) {
                        event.reply("You've already linked your account!");
                        return;
                    }

                    if (!pin.equals(Main.config().getNode("linked-info", uuid, "pin").getString())) {
                        event.reply("Your pin does not match the pin on our files!");
                        return;
                    }
                    Main.config().getNode("linked-info", uuid, "linked").setValue(true);
                    try {Main.getInstance().save();} catch (IOException e) {e.printStackTrace();}

                    String server = event.getJDA().getGuilds().get(0).getSelfMember().getNickname().toLowerCase();

                    //MessageChannel.TO_CONSOLE.send(Text.of(event.getJDA().getGuilds().toString()));

                    switch (server) {
                        case "pokedash": {
                            event.getJDA().getGuilds().get(0).getController().addSingleRoleToMember(event.getJDA().getGuilds().get(0).getMemberById(authorID), event.getJDA().getGuilds().get(0).getRoleById("401183019932581888")).queue();
                            break;
                        }
                        case "pokeclub": {
                            event.getJDA().getGuilds().get(0).getController().addSingleRoleToMember(event.getJDA().getGuilds().get(0).getMemberById(authorID), event.getJDA().getGuilds().get(0).getRoleById("401183075918151682")).queue();
                            break;
                        }
                        case "pokeverse": {
                            event.getJDA().getGuilds().get(0).getController().addSingleRoleToMember(event.getJDA().getGuilds().get(0).getMemberById(authorID), event.getJDA().getGuilds().get(0).getRoleById("401183132327608333")).queue();
                            break;
                        }
                        case "pokelegends": {
                            event.getJDA().getGuilds().get(0).getController().addSingleRoleToMember(event.getJDA().getGuilds().get(0).getMemberById(authorID), event.getJDA().getGuilds().get(0).getRoleById("401183246106361856")).queue();
                            break;
                        }
                        case "pokebrawl": {//Pokebrawl
                            event.getJDA().getGuilds().get(0).getController().addSingleRoleToMember(event.getJDA().getGuilds().get(0).getMemberById(authorID), event.getJDA().getGuilds().get(0).getRoleById("587021321536536576")).queue();
                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    event.reply("You are now able to talk in the server chat");

                })
                .submit(Main.getInstance());
    }
}
