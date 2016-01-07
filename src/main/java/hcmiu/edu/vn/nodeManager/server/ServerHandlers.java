package hcmiu.edu.vn.nodeManager.server;

import java.util.List;

import hcmiu.edu.vn.nodeManager.entiry.Frame;
import hcmiu.edu.vn.nodeManager.util.FrameProcessManager;
import hcmiu.edu.vn.nodeManager.util.dataFrameFacade;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandlers extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String message = msg.toString();
		
		System.out.println("Receive message: "+ message);
		
		FrameProcessManager manager = new FrameProcessManager();
		List<Frame> frameList  = manager.processData(message);
		dataFrameFacade facade = new dataFrameFacade();
		
		facade.saveData(frameList);
	}
	
	

	
	
}
