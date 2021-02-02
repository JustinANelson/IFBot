package JustinBot.commands;

import JustinBot.bot.ConfigVars;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class GetMemberInfo extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s");

        if (args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "lookup")) {
            if(args[1].contains("@")) {
                args[1] = args[1].substring(3, 21);
                System.out.println(args[1]);
            }
            Member member = event.getGuild().getMemberById(args[1]);
            User user = event.getGuild().getJDA().getUserById(args[1]);

            assert user != null;
            Instant created = user.getTimeCreated().toInstant();
            assert member != null;
            Instant join = member.getTimeJoined().toInstant();
            Instant now = Instant.now();
            Duration dCreated = Duration.between(created, now);
            Duration dJoin = Duration.between(join, now);
            String createdAge = format(dCreated);
            String joinAge = format(dJoin);
            EmbedBuilder members = new EmbedBuilder();
            members.setColor(0x66d8ff);
            members.setTitle(user.getName());
            members.setThumbnail(user.getAvatarUrl());
            members.addField("ID", member.getId(), true);
            members.addField("Account Created", user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
            members.addField("Account Created Age", createdAge, true);
            members.addField("Joined Server", member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
            members.addField("Joined Server Age", joinAge, true);
            members.addField("Status", member.getOnlineStatus().toString(), true);
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage((members.build())).queue();
        }
    }

    public static String format(Duration d) {
        long years = (d.toDays() / 365);
        d = d.minusDays(years * 365);
        long days = d.toDays();
        d = d.minusDays(days);
        long hours = d.toHours();
        d = d.minusHours(hours);
        long minutes = d.toMinutes();
        d = d.minusMinutes(minutes);
        long seconds = d.getSeconds() ;
        return
                        (years == 0?"":years+" years, ")+
                        (days ==  0?"":days+" days, ")+
                        (hours == 0?"":hours+" hours, ")+
                        (minutes ==  0?"":minutes+" minutes, ")+
                        (seconds == 0?"":seconds+" seconds");
    }
}
