package com.senpure.io;


import io.netty.channel.Channel;

public interface OffLineListener {

	public void executeOffLine(Channel channel);
	
	public  String getOffLineListenerName();
	
}
