package DiscordIntegration.API;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;

public interface IDiscordService {
    JDA getJDA();
    Guild getGuild(String id);
}
