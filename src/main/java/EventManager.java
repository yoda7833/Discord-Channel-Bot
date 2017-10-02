
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
				for(ChannelList x: lists)
				{
					ChannelNode current = x.getFirstNode();
					boolean notEmpty = true;
					while(current!=x.getFirstNode())
					{
						if(current.getChannel().getMembers().size()==0)
							notEmpty = false;
					}
					if(notEmpty)
						if(x.contains(((GuildVoiceJoinEvent) event).getChannelJoined()))
						{
							x.getFirst().createCopy().complete();
							x.add(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().get(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().size()-1));
						}
				}
		}
		else if(event instanceof GuildVoiceMoveEvent)
		{	
			
		}
		else if(event instanceof GuildVoiceLeaveEvent)
		{
			boolean Empty = false;
			for(ChannelList x:lists)
				if(x.contains(((GuildVoiceLeaveEvent) event).getChannelLeft()))
				{
					ChannelNode current = x.getFirstNode();
					Empty = false;
					while(current!=x.getFirstNode())
					{
						if(current.getChannel().getMembers().size()==0&&!current.getChannel().equals(((GuildVoiceLeaveEvent) event).getChannelLeft()))
							Empty = true;
					}
					if(Empty)
						if(((GuildVoiceLeaveEvent) event).getChannelLeft().getMembers().size()==0)
							x.remove(((GuildVoiceLeaveEvent) event).getChannelLeft());
				}	
		}
	}
	
	public void createLinkedChannels(Guild myGuild)
	{
		for(VoiceChannel x:myGuild.getVoiceChannels())
		{
			if(!Main.base.contains(x.getIdLong()))
			{
				lists.add(new ChannelList(x));
			}
		}
	}
}
