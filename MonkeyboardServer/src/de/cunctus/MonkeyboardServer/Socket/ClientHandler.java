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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import de.cunctus.Monkeyboard.Monkeyboard;
import de.cunctus.Monkeyboard.Enums.PlayMode;
import de.cunctus.Monkeyboard.Enums.StereoMode;

public class ClientHandler extends Thread {

	protected Socket clientSocket;
	protected Monkeyboard board;
	protected MonkeyboardSocketServer server;
	protected BufferedWriter out;
	protected String result;

	public ClientHandler(Socket clientSocket, Monkeyboard board,
			MonkeyboardSocketServer server) {
		this.clientSocket = clientSocket;
		this.board = board;
		this.server = server;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream()));

			String in = null;
			while (!clientSocket.isClosed()) {
				in = reader.readLine();
				this.handleCommand(in);
			}
			reader.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void handleCommand(String command) {
		String[] c = command.split(" ");
		result = "FAILED";

		try {
			switch (c[0]) {
			case "shutdown":
				board.StopStream();
				board.CloseRadioPort();
				clientSocket.close();
				server.interrupt();
				break;
			case "setvolume":
				if (c.length < 2) {
					result = "setvolume [0-16]";
					break;
				}

				if (board.SetVolume(Integer.valueOf(c[1])))
					result = "OK";
				break;
			case "volumeup":
				result = "New Volume: " + board.VolumePlus();
				break;
			case "volumedown":
				result = "New Volume: " + board.VolumeMinus();
				break;
			case "mute":
				board.VolumeMute();
				result = "OK";
				break;
			case "playstream":
				if (c.length < 3) {
					result = "playstream [0,1] [channel]";
					break;
				}
				if (board.PlayStream(
						PlayMode.getByValue(Integer.valueOf(c[1])),
						Long.valueOf(c[2])))
					result = "OK";
				break;
			case "stopstream":
				if (board.StopStream())
					result = "OK";
				break;
			case "setstereomode":
				if (c.length < 2) {
					result = "setstereomode [0,1]";
					if (board.SetStereoMode(StereoMode.getByValue(Integer.valueOf(c[1]))))
						result = "OK";
					break;
				}
				break;
			default:
				result = "UNKNOWN COMMAND";
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!clientSocket.isClosed()) {
					out.write(result + "\r\n");
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
