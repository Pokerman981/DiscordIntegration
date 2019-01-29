package DiscordIntegration.API;

import DiscordIntegration.Main;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;

public class Service implements IDiscordService{
    @Override
    public JDA getJDA() {
        return Main.jda;
    }

    @Override
    public Guild getGuild(String id) {
        return Main.jda.getGuildById(id);
    }
}
