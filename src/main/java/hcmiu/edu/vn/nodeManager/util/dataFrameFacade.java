package hcmiu.edu.vn.nodeManager.util;

import hcmiu.edu.vn.nodeManager.entiry.Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataFrameFacade {
	
	private MySqlConnectionManager manager;
	
	public dataFrameFacade(){
		manager = new MySqlConnectionManager();
	}
	
	public boolean saveData(List<Frame> frames){
		Connection connection = null;
        
        try {
        	connection = this.manager.getDBConnection();
        	
        	for(Frame frame: frames){
            String sql = "insert into data values ( ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, frame.getDateTime());
            stmt.setString(2, frame.getNodeId());
            stmt.setDouble(3, frame.getLongTitude());
            stmt.setDouble(4, frame.getLaTitude());
            stmt.setString(5, frame.getData1());
            stmt.setString(6, frame.getData2());
            
         
            stmt.executeUpdate();
            stmt.close();
        	}
            
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(MySqlConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
	}
	
}
