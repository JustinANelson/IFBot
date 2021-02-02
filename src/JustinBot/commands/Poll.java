package JustinBot.commands;

import JustinBot.bot.ConfigVars;
import JustinBot.bot.IFBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class Poll extends ListenerAdapter {
    boolean isPoll;
    boolean inPoll;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s");
        if (!event.getAuthor().isBot()) {
            if (args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "poll")) {
                if (args.length > 1) {
                    //USAGE
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(0xff3923);
                    usage.setTitle("Start a new poll.");
                    usage.setDescription("Usage: Please type ~poll with no arguments.");
                    event.getChannel().sendMessage(usage.build()).queue();
                }
                else {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(0xff3923);
                    usage.setTitle("Type ~end when done.");
                    usage.setDescription("Type ~1 then your first option.");
                    event.getChannel().sendMessage(usage.build()).queue();
                    event.getChannel().sendMessage(" - Poll - ").queue();
                    isPoll = true;
                }
            }

            if (inPoll) {
                String string = String.join("\\s", Arrays.toString(args));

                if (args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "1")) {
                    if (args.length < 1) {
                        //ERROR TOO FEW ARGUMENTS
                        inPoll = false;
                    }
                    else {
                        event.getChannel().sendMessage(string).queue(message -> {
                            message.addReaction("✅").queue();
                            message.addReaction("❎").queue();
                        });
                    }
                }
                inPoll = false;
            }
            inPoll = true;
        }
    }
}
