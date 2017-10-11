import java.util.List;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.RestAction;

public class MessageListener extends net.dv8tion.jda.core.hooks.ListenerAdapter
{
  public MessageListener() {}
  
  public void onMessageReceived(MessageReceivedEvent event)
  {
    if (event.getAuthor().isBot()) { return;
    }
    Message message = event.getMessage();
    String content = message.getRawContent();
    

    if (content.equals("!ping"))
    {
      MessageChannel channel = event.getChannel();
      channel.sendMessage("Pong").queue();
    }
    else if (content.equals("!pong"))
    {
      MessageChannel channel = event.getChannel();
      channel.sendMessage("Ping").queue();
    }
    else if (content.startsWith("!afk "))
    {
      if (message.getMember().hasPermission(new Permission[] { Permission.ADMINISTRATOR }))
      {
        if (message.getMentionedUsers().isEmpty()) {
          message.getChannel().sendMessage(message.getAuthor().getName() + " You need to mention atleast one user").queue();
        }
        else {
          for (User x : message.getMentionedUsers())
          {
            event.getGuild().getController().moveVoiceMember(event.getGuild().getMember(x), event.getGuild().getAfkChannel()).queue();
          }
        }
      }
      else {
        event.getChannel().sendMessage("You don't have permission to do that").queue();
      }
    }
    else if (content.equals("!help"))
    {
      MessageChannel channel = event.getChannel();
      channel.sendMessage("!ping - responds with Pong\n!pong - responds with Ping\n!afk @mentions will move users to the afk channel (admin only)").queue();
    }
    else if (content.equals("!stop"))
    {
      if (message.getMember().hasPermission(new Permission[] { Permission.ADMINISTRATOR }))
      {
        MessageChannel channel = event.getChannel();
        channel.sendMessage("Shutting down....").queue();
        Object channels = event.getGuild().getVoiceChannels();
        for (net.dv8tion.jda.core.entities.VoiceChannel x : (List)channels)
        {
          if (!Main.base.contains(x))
          {

            x.delete().queue();
          }
        }
        System.out.println("Stopped by command");
        event.getJDA().shutdown();
      }
    }
  }
}
