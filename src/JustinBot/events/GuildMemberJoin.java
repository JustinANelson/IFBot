package JustinBot.events;

import JustinBot.bot.ConfigVars;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static JustinBot.commands.GetMemberInfo.format;

public class GuildMemberJoin extends ListenerAdapter {

    String[] messages = {
            "[member] joined. You must construct additional pylons.",
            "Never gonna give [member] up. Never let [member] down!",
            "Hey! Listen! [member] has joined!",
            "Ha! [member] has joined! You activated my trap card!",
            "We've been expecting you, [member].",
            "It's dangerous to go alone, take [member]!",
            "Swoooosh. [member] just landed.",
            "Brace yourselves. [member] just joined the server.",
            "A wild [member] appeared."
    };

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        Random rand = new Random();
        int number = rand.nextInt(messages.length);

        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(messages[number].replace("[member]", event.getMember().getAsMention()));

        event.getGuild().getDefaultChannel().sendMessage((join.build())).queue();

        //BUILD EMBED FOR JOIN_LEAVE

        Member member = event.getMember();
        User user = event.getUser();

        Instant created = user.getTimeCreated().toInstant();
        Instant now = Instant.now();
        Duration dCreated = Duration.between(created, now);
        String createdAge = format(dCreated);
        EmbedBuilder jl = new EmbedBuilder();
        jl.setColor(0x66d8ff);
        jl.setTitle(user.getName() + " joined");
        jl.setThumbnail(user.getAvatarUrl());
        jl.addField("ID", member.getId(), true);
        jl.addField("Account Created", user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
        jl.addField("Account Age", createdAge, true);
        jl.addField("Joined Server", member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
        event.getGuild().getTextChannelById(ConfigVars.getJoinLeaveChannel()).sendMessage((jl.build())).queue();

        //Add role
        if (ConfigVars.getAddRoleOnJoin()) {
            Role role = event.getGuild().getRolesByName(ConfigVars.getJoinRole(), true).get(0);
            event.getGuild().addRoleToMember(member, role).complete();
        }



    }
}