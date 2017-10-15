import java.util.List;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.requests.restaction.order.ChannelOrderAction;

public class EventManager implements net.dv8tion.jda.core.hooks.EventListener
{
  private boolean[] channelsTemp = null;
  
  public EventManager() {}
  
  public void onEvent(net.dv8tion.jda.core.events.Event event) { if ((event instanceof GuildVoiceJoinEvent))
    {
      if (channelsTemp == null)
      {
        channelsTemp = new boolean[Main.base.size()];
      }
      GuildVoiceJoinEvent voice = (GuildVoiceJoinEvent)event;
      
      if ((voice.getChannelJoined().getMembers().size() > 1) && (Main.base.contains(voice.getChannelJoined())) && (!Main.nonTemp.contains(voice.getChannelJoined())))
      {
        if ((Main.base.contains(voice.getChannelJoined())) && (channelsTemp[Main.base.indexOf(voice.getChannelJoined())] == 0))
        {
          channelsTemp[Main.base.indexOf(voice.getChannelJoined())] = true;
          voice.getGuild().getController().createCopyOfChannel(voice.getChannelJoined()).setName(voice.getChannelJoined().getName() + " TEMP").complete();
          ((net.dv8tion.jda.core.entities.TextChannel)voice.getGuild().getTextChannelsByName("bot_commander_mods", false).get(0)).sendMessage("A new " + voice.getChannelJoined().getName() + " was made").queue();
          List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelJoined().getName() + " TEMP", false);
          int posO = voice.getChannelJoined().getPosition();
          int posN = ((VoiceChannel)channels.get(0)).getPosition();
          ((ChannelOrderAction)((ChannelOrderAction)voice.getGuild().getController().modifyVoiceChannelPositions().selectPosition((VoiceChannel)channels.get(0))).moveUp(posN - posO - 1)).queue();
        }
        
      }
      
    }
    else if ((event instanceof GuildVoiceMoveEvent))
    {
      if (channelsTemp == null)
      {
        channelsTemp = new boolean[Main.base.size()];
      }
      
      GuildVoiceMoveEvent voice = (GuildVoiceMoveEvent)event;
      if ((((GuildVoiceMoveEvent)event).getChannelLeft().getMembers().isEmpty()) && (channelsTemp[Main.base.indexOf(((GuildVoiceMoveEvent)event).getChannelLeft())] != 0))
      {
        if (((VoiceChannel)voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName() + " TEMP", false).get(0)).getMembers().isEmpty())
        {

          List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName() + " TEMP", false);
          ((VoiceChannel)channels.get(0)).delete().queue();
          channelsTemp[Main.base.indexOf(voice.getChannelLeft())] = false;
        }
      }
      
      if ((voice.getChannelJoined().getMembers().size() > 1) && (Main.base.contains(voice.getChannelJoined())) && (!Main.nonTemp.contains(voice.getChannelJoined())))
      {
        if ((Main.base.contains(voice.getChannelJoined())) && (channelsTemp[Main.base.indexOf(voice.getChannelJoined())] == 0))
        {
          channelsTemp[Main.base.indexOf(voice.getChannelJoined())] = true;
          voice.getGuild().getController().createCopyOfChannel(voice.getChannelJoined()).setName(voice.getChannelJoined().getName() + " TEMP").complete();
          ((net.dv8tion.jda.core.entities.TextChannel)voice.getGuild().getTextChannelsByName("bot_commander_mods", false).get(0)).sendMessage("A new " + voice.getChannelJoined().getName() + " was made").queue();
          List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelJoined().getName() + " TEMP", false);
          int posO = voice.getChannelJoined().getPosition();
          int posN = ((VoiceChannel)channels.get(0)).getPosition();
          ((ChannelOrderAction)((ChannelOrderAction)voice.getGuild().getController().modifyVoiceChannelPositions().selectPosition((VoiceChannel)channels.get(0))).moveUp(posN - posO - 1)).queue();
        }
        
      }
      
    }
    else if ((event instanceof GuildVoiceLeaveEvent))
    {
      if (channelsTemp == null)
      {
        channelsTemp = new boolean[Main.base.size()];
      }
      GuildVoiceLeaveEvent voice = (GuildVoiceLeaveEvent)event;
      if ((((GuildVoiceLeaveEvent)event).getChannelLeft().getMembers().isEmpty()) && (channelsTemp[Main.base.indexOf(((GuildVoiceLeaveEvent)event).getChannelLeft())] != 0))
      {
        if (((VoiceChannel)voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName() + " TEMP", false).get(0)).getMembers().isEmpty())
        {

          List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName() + " TEMP", false);
          ((VoiceChannel)channels.get(0)).delete().queue();
          channelsTemp[Main.base.indexOf(voice.getChannelLeft())] = false;
        }
      }
    }
  }
}