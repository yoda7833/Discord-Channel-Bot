
import java.util.List;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.EventListener;

/*public class EventManager implements EventListener {
	private boolean[] channelsTemp = null;
	
	@Override
	public void onEvent(Event event) {
		if(event instanceof GuildVoiceJoinEvent)
		{
			if(channelsTemp==null)
			{
				channelsTemp = new boolean[Main.base.size()];
			}
			GuildVoiceJoinEvent voice = (GuildVoiceJoinEvent)event;
			//System.out.println(voice.getChannelJoined().getName());
			if(voice.getChannelJoined().getMembers().size()>1&&Main.base.contains(voice.getChannelJoined())&&!Main.nonTemp.contains(voice.getChannelJoined()))
			{
				if(Main.base.contains(voice.getChannelJoined())&&!channelsTemp[Main.base.indexOf(voice.getChannelJoined())])
				{
					channelsTemp[Main.base.indexOf(voice.getChannelJoined())] = true;
					voice.getGuild().getController().createCopyOfChannel(voice.getChannelJoined()).setName(voice.getChannelJoined().getName()+" TEMP").complete();;
					voice.getGuild().getTextChannelsByName("bot_commander_mods", false).get(0).sendMessage("A new "+voice.getChannelJoined().getName()+" was made").queue();
					List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelJoined().getName()+" TEMP", false);
					int posO = voice.getChannelJoined().getPosition();
					int posN = channels.get(0).getPosition();
					voice.getGuild().getController().modifyVoiceChannelPositions().selectPosition(channels.get(0)).moveUp(posN-posO-1).queue();
				}
				
			}
			
		}
		else if(event instanceof GuildVoiceMoveEvent)
		{
			if(channelsTemp==null)
			{
				channelsTemp = new boolean[Main.base.size()];
			}
			
			GuildVoiceMoveEvent voice = (GuildVoiceMoveEvent)event;
			if(((GuildVoiceMoveEvent) event).getChannelLeft().getMembers().isEmpty()&&channelsTemp[Main.base.indexOf(((GuildVoiceMoveEvent) event).getChannelLeft())])
			{
				if(voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName()+" TEMP", false).get(0).getMembers().isEmpty() )
				{
					//System.out.println("Deleting");
					List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName()+" TEMP", false);
					channels.get(0).delete().queue();
					channelsTemp[Main.base.indexOf(voice.getChannelLeft())] = false;
				}
					
			}
			if(voice.getChannelJoined().getMembers().size()>1&&Main.base.contains(voice.getChannelJoined())&&!Main.nonTemp.contains(voice.getChannelJoined()))
			{
				if(Main.base.contains(voice.getChannelJoined())&&!channelsTemp[Main.base.indexOf(voice.getChannelJoined())])
				{
					channelsTemp[Main.base.indexOf(voice.getChannelJoined())] = true;
					voice.getGuild().getController().createCopyOfChannel(voice.getChannelJoined()).setName(voice.getChannelJoined().getName()+" TEMP").complete();
					voice.getGuild().getTextChannelsByName("bot_commander_mods", false).get(0).sendMessage("A new "+voice.getChannelJoined().getName()+" was made").queue();
					List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelJoined().getName()+" TEMP", false);
					int posO = voice.getChannelJoined().getPosition();
					int posN = channels.get(0).getPosition();
					voice.getGuild().getController().modifyVoiceChannelPositions().selectPosition(channels.get(0)).moveUp(posN-posO-1).queue();
				}
				
			}
			if(((GuildVoiceMoveEvent) event).getChannelLeft().getMembers().isEmpty()&&channelsTemp[Main.base.indexOf(voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName().substring(0, voice.getChannelLeft().getName().lastIndexOf(" TEMP")), false).get(0))])
			{
				System.out.println("Test2");
				if(voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName().replaceAll(" TEMP", ""), false).get(0).getMembers().isEmpty() )
				{
					System.out.println("Deleting2");
					List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName()+" TEMP", false);
					channels.get(0).delete().queue();
					channelsTemp[Main.base.indexOf(voice.getChannelLeft())] = false;
				}
			}
			
		}
		else if(event instanceof GuildVoiceLeaveEvent)
		{
			if(channelsTemp==null)
			{
				channelsTemp = new boolean[Main.base.size()];
			}
			GuildVoiceLeaveEvent voice = (GuildVoiceLeaveEvent)event;
			if(((GuildVoiceLeaveEvent) event).getChannelLeft().getMembers().isEmpty()&&channelsTemp[Main.base.indexOf(((GuildVoiceLeaveEvent) event).getChannelLeft())])
			{
				if(voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName()+" TEMP", false).get(0).getMembers().isEmpty() )
				{
					//System.out.println("Deleting");
					List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName()+" TEMP", false);
					channels.get(0).delete().queue();
					channelsTemp[Main.base.indexOf(voice.getChannelLeft())] = false;
				}
					
			}
			if(((GuildVoiceLeaveEvent) event).getChannelLeft().getMembers().isEmpty()&&channelsTemp[Main.base.indexOf(voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName().substring(0, voice.getChannelLeft().getName().indexOf("TEMP")), true).get(0))])
			{
				System.out.println("Test");
				if(voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName().replaceAll(" TEMP", ""), false).get(0).getMembers().isEmpty() )
				{
					System.out.println("Deleting");
					List<VoiceChannel> channels = voice.getGuild().getVoiceChannelsByName(voice.getChannelLeft().getName()+" TEMP", false);
					channels.get(0).delete().queue();
					channelsTemp[Main.base.indexOf(voice.getChannelLeft())] = false;
				}
			}
		}
	}
}*/
