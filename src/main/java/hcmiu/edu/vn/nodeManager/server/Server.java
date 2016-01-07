package hcmiu.edu.vn.nodeManager.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class Server {
	
	private final int port;
	
	
	
	public Server(int port){
		this.port = port;		
	}
	
	public static void main(String[] args) throws InterruptedException{
		if(args.length != 1){
			System.out.println("Give me the port number!");
		}else{
			int portnumber = Integer.valueOf(args[0]);
			System.out.println("Server is running on port " + portnumber);
			new Server(portnumber).run();
		}
	}
	
	public void run() throws InterruptedException{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap bootstrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new serverInnitializer());
			
			bootstrap.bind(port).sync().channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
}
