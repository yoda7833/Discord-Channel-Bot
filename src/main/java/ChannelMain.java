import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.voice.*;

public class ChannelMain {
	private VoiceChannel channel;
	private VoiceChannel[] temps = new VoiceChannel[10];
	public ChannelMain(VoiceChannel mychannel)
	{
		channel = mychannel;
	}
	
	public void channelEvent(Event event)
	{
		boolean hasEmpty = false;
		if(channel.getMembers().size()==0)
			hasEmpty=true;
		for(VoiceChannel x:temps)
			if(x.getMembers().size()==0)
				hasEmpty=true;
				
		if(event instanceof GuildVoiceJoinEvent)
		{
			boolean joined = false;
			if(channel.equals(((GuildVoiceJoinEvent) event).getChannelJoined()))
				if(channel.getMembers().size()>1&&!hasEmpty)
					for(int x=0;x<10;x++)
						if(temps[x]==null)
							
			for(VoiceChannel x: temps)
				if(x!=null&&x.equals(((GuildVoiceJoinEvent) event).getChannelJoined()))
				{
					
				}
		}
		else if(event instanceof GuildVoiceLeaveEvent)
		{
			
		}
		else if(event instanceof GuildVoiceMoveEvent)
		{
			
		}
	}
	
}
