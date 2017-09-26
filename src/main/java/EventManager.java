
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EventManager implements EventListener {
	private ArrayList<ChannelList> lists = new ArrayList<>();
	public EventManager(Guild myGuild)
	{
		super();
		createLinkedChannels(myGuild);
	}
	
	@Override
	public void onEvent(Event event) {
		if(event instanceof GuildVoiceJoinEvent)
		{
			
		}
		else if(event instanceof GuildVoiceMoveEvent)
		{	
			
		}
		else if(event instanceof GuildVoiceLeaveEvent)
		{
			
		}
	}
	
	public void createLinkedChannels(Guild myGuild)
	{
		
	}
}
