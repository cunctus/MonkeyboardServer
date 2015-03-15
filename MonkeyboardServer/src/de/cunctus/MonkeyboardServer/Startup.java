/**
 * @project MonkeyboardServer
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.MonkeyboardServer
 * @version 
 * @since   13.03.2015
 *
 */
package de.cunctus.MonkeyboardServer;

import de.cunctus.Monkeyboard.Monkeyboard;
import de.cunctus.MonkeyboardServer.Socket.MonkeyboardSocketServer;

public class Startup {

	public static void main(String[] args) {

		String device = args[0];
		String library = args[1];
		int port = Integer.valueOf(args[2]);

		try {
			Monkeyboard board = Monkeyboard.getInstance(device, library);
			MonkeyboardSocketServer server = new MonkeyboardSocketServer(port,
					board);
			
			server.setDaemon(false);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
