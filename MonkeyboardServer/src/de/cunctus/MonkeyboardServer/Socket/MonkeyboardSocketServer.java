/**
 * @project MonkeyboardServer
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.MonkeyboardServer.Socket
 * @version 
 * @since   14.03.2015
 *
 */
package de.cunctus.MonkeyboardServer.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import de.cunctus.Monkeyboard.Monkeyboard;
import de.cunctus.Monkeyboard.Enums.PlayMode;
import de.cunctus.Monkeyboard.Enums.StereoMode;
import de.cunctus.MonkeyboardServer.MonkeyboardStatusThread;

public class MonkeyboardSocketServer extends Thread {

	protected int port;
	protected Monkeyboard board;
	protected MonkeyboardStatusThread statusThread;
	protected ServerSocket socket;

	public MonkeyboardSocketServer(int port, Monkeyboard board)
			throws Exception {
		this.port = port;
		this.board = board;
		this.statusThread = new MonkeyboardStatusThread(board);
		this.socket = new ServerSocket(port);
	}

	@Override
	public void run() {
		board.PlayStream(PlayMode.FM, 100600);
		board.SetStereoMode(StereoMode.AUTO_STEREO);
		board.SetVolume(12);
		statusThread.start();

		System.out.println("Listening on " + port);
		while (!this.isInterrupted()) {
			try {
				socket.setSoTimeout(5000);
				Socket client = socket.accept();
				ClientHandler handler = new ClientHandler(client, board, this);
				handler.start();
			} catch (SocketTimeoutException e) {
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		statusThread.interrupt();
	}

}
