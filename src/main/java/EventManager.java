
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
	public void onEvent(Event event) {//does not have a empty channel, and channel they joined has more than 1
		if(event instanceof GuildVoiceJoinEvent)
		{
			for(ChannelList x: lists)
			{
				System.out.println("join for-loop");
				if(x.contains(((GuildVoiceJoinEvent) event).getChannelJoined()))
				{
					if(((GuildVoiceJoinEvent) event).getChannelJoined().getMembers().size()>1)
					{
						ChannelNode current = x.getFirstNode();
						boolean notEmpty = true;
						do
						{
							if(current.getChannel().getMembers().size()==0)
								notEmpty = false;
							current = current.getNext();
							System.out.println("in do-while");
						}while(current!=x.getFirstNode());
						if(notEmpty)
						{
							x.getFirst().createCopy().complete();
							//System.out.println(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().get(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().size()-1).getName());
							x.add(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().get(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().size()-1));
						}
					}
						
				}
					
			}
		}
		else if(event instanceof GuildVoiceMoveEvent)
		{	
			/*//Join section
			for(ChannelList x: lists)
			{
				if(x.contains(((GuildVoiceMoveEvent) event).getChannelJoined()))
				{
					if(((GuildVoiceMoveEvent) event).getChannelJoined().getMembers().size()>1)
					{
						ChannelNode current = x.getFirstNode();
						boolean notEmpty = true;
						do
						{
							if(current.getChannel().getMembers().size()==0)
								notEmpty = false;
							current = current.getNext();
						}while(current!=x.getFirstNode());
						if(notEmpty)
						{
							x.getFirst().createCopy().complete();
							//System.out.println(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().get(((GuildVoiceJoinEvent) event).getGuild().getVoiceChannels().size()-1).getName());
							x.add(((GuildVoiceMoveEvent) event).getGuild().getVoiceChannels().get(((GuildVoiceMoveEvent) event).getGuild().getVoiceChannels().size()-1));
						}
					}	
				}	
			}
			
			//Leave section
			boolean Empty = false;
			for(ChannelList x:lists)
				if(x.contains(((GuildVoiceMoveEvent) event).getChannelLeft()))
				{
					ChannelNode current = x.getFirstNode();
					Empty = false;
					while(current!=x.getFirstNode())
					{
						if(current.getChannel().getMembers().size()==0&&!current.getChannel().equals(((GuildVoiceMoveEvent) event).getChannelLeft()))
							Empty = true;
					}
					if(Empty)
						if(((GuildVoiceMoveEvent) event).getChannelLeft().getMembers().size()==0)
							x.remove(((GuildVoiceMoveEvent) event).getChannelLeft());
				}*/
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
	
	public void clearAll()
	{
		for(ChannelList x: lists)
			x.clear();
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
