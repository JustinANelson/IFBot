package JustinBot.commands;

import JustinBot.bot.ConfigVars;
import JustinBot.bot.IFBot;
import JustinBot.util.Embeds;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingPong extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "Ping") ) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(Embeds.embeds("Pong test").build()).queue();
        }
        else if(args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "pong") ) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(Embeds.embeds("Ping test").build()).queue();
        }
    }

}
