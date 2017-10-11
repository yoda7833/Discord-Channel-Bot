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
    	Message message = event.getMessage();
        String content = message.getRawContent(); 
        if (event.getAuthor().isBot()) return;
        if(message.isMentioned(event.getJDA().getSelfUser()))//responds when someone mentions the bot
        {
        	MessageChannel channel = event.getChannel();
            channel.sendMessage("You called "+message.getAuthor().getAsMention()+"? Well I can't do much more than respond with this dull message").queue();
        }
        if (content.equals("!ping"))//basically to test if the bot works
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong").queue();
        }
        else if(content.equals("!pong"))//to check the ping between the bot and discord server
        {
        	MessageChannel channel = event.getChannel();
            channel.sendMessage("Ping "+event.getJDA().getPing()+"ms").queue();
        }
        else if(content.startsWith("!afk "))//will move everyone mentioned to the afk channel
        {
        	if(message.getAuthor().getIdLong()==139171653480349697L)
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
	        	if(message.getMentionedUsers().isEmpty())
	        		message.getChannel().sendMessage("@"+message.getAuthor().getName()+" You need to mention atleast one user").queue();
	        	else
	        	{
	        		for(User x : message.getMentionedUsers())
	        		{
	        			event.getGuild().getController().moveVoiceMember(event.getGuild().getMember(x), event.getGuild().getAfkChannel()).complete();;
	        		}
	        	}
        	}
        	else
        		event.getChannel().sendMessage("You don't have permission to do that").queue();
        	
        }
        else if(content.equals("!help"))//reponds with the commands
        {
        	MessageChannel channel = event.getChannel();
            channel.sendMessage("!ping - responds with Pong\n!pong - responds with Ping\n!afk @mentions will move users to the afk channel (admin only)").queue();
        }
        else if(content.equals("!stop"))//ends the bot safely
        {
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
        		MessageChannel channel = event.getChannel();
        		channel.sendMessage("Shutting down....").queue();
        		Main.eventM.clearAll();
        		System.out.println("Stopped by command");
        		event.getJDA().shutdown();
        	}
        }
        else if(content.equals("!restart"))//broken
        {
        	if(message.getMember().hasPermission(Permission.ADMINISTRATOR))
        	{
        		MessageChannel channel = event.getChannel();
        		channel.sendMessage("Broken command... please report this issue").queue();
        		/*Main.eventM.clearAll();
        		System.out.println("Restarting by Command");
        		new Main();
        		event.getJDA().shutdown();*/
        	}
        }
        /*else if(content.startsWith("!noTemp"))
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
        	
        }*/
    }
    
}