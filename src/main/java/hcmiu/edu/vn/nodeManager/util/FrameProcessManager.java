package hcmiu.edu.vn.nodeManager.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmiu.edu.vn.nodeManager.entiry.*;

public class FrameProcessManager {
	
	private static String getDate(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String todayWithZeroTime = null;
		todayWithZeroTime = dateFormat.format(date);
		
		return todayWithZeroTime.toString();
		
	}
	
	public List<Frame> processData(String data){
		List<Frame> frameList = new ArrayList<Frame>();
		
		String frameData[] = data.split(";");
		
		for(String dataf : frameData){
			String d[] = dataf.split(",");
			if(d.length == 6){
			Frame frame = new Frame();
			frame.setDateTime(this.getDate()+" "+ d[0]);
			frame.setNodeId(d[1]);
			frame.setLongTitude(Double.parseDouble(d[2]));
			frame.setLaTitude(Double.parseDouble(d[3]));
			frame.setData1(d[4]);
			frame.setData2(d[5]);
			
			frameList.add(frame);
			}
		}
		
		return frameList;
	}
	
}
