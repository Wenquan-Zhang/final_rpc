package com.xtwy.client.core;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;

import com.alibaba.fastjson.JSONObject;
import com.xtwy.client.constant.Constants;
import com.xtwy.client.handler.SimpleClientHandler;
import com.xtwy.client.param.ClientRequest;
import com.xtwy.client.param.Response;
import com.xtwy.client.zk.ZookeeperFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TcpClient {
	
	
	static final Bootstrap b = new Bootstrap();
	static ChannelFuture f=null;
	static {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		b.group(workerGroup);
		b.channel(NioSocketChannel.class);
		b.option(ChannelOption.SO_KEEPALIVE, true);
		b.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws  Exception{
				ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new SimpleClientHandler());
				ch.pipeline().addLast(new StringEncoder());
			}
		});
		CuratorFramework client = ZookeeperFactory.create();
		String host = "localhost";
		int port = 8080;
		try {
			List<String> serverPaths=client.getChildren().forPath(Constants.SERVER_PATH);
			
			//加上zk监听服务器的变化
			CuratorWatcher watcher=new ServerWatcher();
			client.getChildren().usingWatcher(watcher).forPath(Constants.SERVER_PATH);
			
			
			for(String serverPath:serverPaths) {
				String[] str = serverPath.split("#");
				int weight=Integer.valueOf(str[2]);
				if(weight>0) {
					for(int w=0;w<weight;w++) {
						ChannelManager.realServerPath.add(str[0]+"#"+str[1]);
						ChannelFuture channelFuture=TcpClient.b.connect(str[0],Integer.valueOf(str[1]));
						ChannelManager.add(channelFuture);
					}
				}
			}
			if(ChannelManager.realServerPath.size()>0) {
				String[] hostAndPort = ChannelManager.realServerPath.toArray()[0].toString().split("#");
				host=hostAndPort[0];
				port=Integer.parseInt(hostAndPort[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try {
//			f=b.connect(host,port).sync();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	
	public static Response send(ClientRequest request) {
		f=ChannelManager.get(ChannelManager.position);
		f.channel().writeAndFlush(JSONObject.toJSONString(request));
		f.channel().writeAndFlush("\r\n");
		DefaultFuture df=new DefaultFuture(request);
		return df.get();
	}
	
	
	}


