package JustinBot.commands;

import JustinBot.bot.ConfigVars;
import JustinBot.bot.IFBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.WidgetUtil;

import java.util.ArrayList;
import java.util.List;

public class ListServerMembers extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s");

        if (args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "list")) {
            if (args[1].equalsIgnoreCase("members")) {
                int memberCount = event.getGuild().getMemberCount();
                List<Member> membersList = new ArrayList(event.getGuild().getMembers());
                EmbedBuilder members = new EmbedBuilder();
                members.setColor(0x66d8ff);
                members.setTitle(event.getGuild().getName() + " member list.");
                System.out.println(memberCount);
                for (int x = 0; x <= 2; x++) {
                    Member member = membersList.get(x);
                    String memberID = "ID: " + member.getId();
                    String memberJoined = "Time Joined: " + member.getTimeJoined();
                    String memberName = "Name: " + member.getEffectiveName();
                    String fields = memberName + "\n" + memberID + "\n" + memberJoined;
                    members.addField("Member " + (x + 1) + ": ", fields, true);
                }
                StringBuilder memberListStr = new StringBuilder();
                for (int x = 0; x < memberCount; x++) {
                    Member member = membersList.get(x);
                    String memberID = "ID: " + member.getId();
                    String memberJoined = "Time Joined: " + member.getTimeJoined();
                    String memberName = "Name: " + member.getEffectiveName();
                    String fields = memberName + "\n" + memberID + "\n" + memberJoined + "\n\n";
                    memberListStr.append(fields);
                }
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage((members.build())).queue();
                event.getChannel().sendMessage(memberListStr).queue();
            }
        }
    }
}
