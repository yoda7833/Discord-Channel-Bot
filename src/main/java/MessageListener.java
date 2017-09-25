import java.util.List;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getRawContent(); 
        // getRawContent() is an atomic getter
        // getContent() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        else if(content.equals("!pong"))
        {
        	MessageChannel channel = event.getChannel();
            channel.sendMessage("Ping").queue();
        }
        else if(content.startsWith("!afk "))
        {
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
	        	if(message.getMentionedUsers().isEmpty())
	        		message.getChannel().sendMessage("@"+message.getAuthor().getName()+" You need to mention atleast one user").queue();
	        	else
	        	{
	        		for(User x : message.getMentionedUsers())
	        		{
	        			event.getGuild().getController().moveVoiceMember(event.getGuild().getMember(x), event.getGuild().getAfkChannel()).queue();;
	        		}
	        	}
        	}
        	else
        		event.getChannel().sendMessage("You don't have permission to do that").queue();
        	
        }
        else if(content.equals("!help"))
        {
        	MessageChannel channel = event.getChannel();
            channel.sendMessage("!ping - responds with Pong\n!pong - responds with Ping\n!afk @mentions will move users to the afk channel (admin only)").queue();
        }
        else if(content.equals("!stop"))
        {
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
        		MessageChannel channel = event.getChannel();
        		channel.sendMessage("Shutting down....").queue();
        		List<VoiceChannel> channels = event.getGuild().getVoiceChannels();
        		for(VoiceChannel x : channels)
        		{
        			if(!Main.base.contains(x))
        			{
        				//System.out.println("Here");
        				x.delete().queue();
        			}
        		}
        		System.out.println("Stopped by command");
        		event.getJDA().shutdown();
        	}
        }
        else if(content.equals("!restart"))
        {
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
        		MessageChannel channel = event.getChannel();
        		channel.sendMessage("Restarting....").queue();
        		List<VoiceChannel> channels = event.getGuild().getVoiceChannels();
        		for(VoiceChannel x : channels)
        		{
        			if(!Main.base.contains(x))
        			{
        				//System.out.println("Here");
        				x.delete().queue();
        			}
        		}
        		System.out.println("Stopped by command");
        		event.getJDA().shutdown();
        		new Main();
        	}
        }
        else if(content.startsWith("!noTemp"))
        {
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
	        	if(event.getGuild().getVoiceChannelsByName(content.substring(7), true).isEmpty())
	        		message.getChannel().sendMessage("@"+message.getAuthor().getName()+" You need to mention 1 channel").queue();
	        	else//needs work
	        	{
	        		
	        	}
        	}
        	else
        		event.getChannel().sendMessage("You don't have permission to do that").queue();
        	
        }
    }
    
}