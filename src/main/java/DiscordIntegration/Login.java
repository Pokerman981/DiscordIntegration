package DiscordIntegration;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

public class Login {

    public Login(String token) throws RateLimitedException, LoginException, InterruptedException{
        Main.jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();


    }

}
