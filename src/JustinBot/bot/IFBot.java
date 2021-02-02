package JustinBot.bot;

import JustinBot.commands.*;
import JustinBot.events.GuildMemberJoin;
import JustinBot.events.GuildMemberRemove;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.*;

public class IFBot {
    public static JDA jda;

    // Main Method
    public static void main(String args[]) throws Exception {
        String str = connect();
        jda = new JDABuilder(AccountType.BOT).setToken(str).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("iRobot"));
        registerListeners();
    }

    public static String connect() throws Exception {
        File file = new File("src\\config.dat");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }

    public static void registerListeners() {
        //Register Commands
        jda.addEventListener(new PingPong());
        jda.addEventListener(new AddRemoveRole());
        jda.addEventListener(new Clear());
        //jda.addEventListener(new Poll()); #incomplete
        jda.addEventListener(new Time());
        jda.addEventListener(new ListServerMembers());
        jda.addEventListener(new GetMemberInfo());
        jda.addEventListener(new Config());

        //Register Events
        jda.addEventListener(new GuildMemberJoin());
        jda.addEventListener(new GuildMemberRemove());
    }
}
