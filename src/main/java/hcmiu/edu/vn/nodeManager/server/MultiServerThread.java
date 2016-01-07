package hcmiu.edu.vn.nodeManager.server;

import hcmiu.edu.vn.nodeManager.entiry.Frame;
import hcmiu.edu.vn.nodeManager.util.FrameProcessManager;
import hcmiu.edu.vn.nodeManager.util.dataFrameFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class MultiServerThread extends Thread {
	private Socket socket = null;

	public MultiServerThread(Socket socket) {
		super("MultiServerThread");
		this.socket = socket;
	}

	public void run() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String inputLine, outputLine;
			/*
			 * KnockKnockProtocol kkp = new KnockKnockProtocol(); outputLine =
			 * kkp.processInput(null); out.println(outputLine);
			 */

			while ((inputLine = in.readLine()) != null) {
				try {
					System.out.println("Received: " + inputLine);
					FrameProcessManager manager = new FrameProcessManager();
					List<Frame> frameList = manager.processData(inputLine);
					dataFrameFacade facade = new dataFrameFacade();

					facade.saveData(frameList);
				} catch (Exception e) {
					System.out.println(e);

				}
			}
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}