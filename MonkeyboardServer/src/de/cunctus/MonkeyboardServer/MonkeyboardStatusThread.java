/**
 * @project MonkeyboardServer
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.MonkeyboardServer
 * @version 
 * @since   14.03.2015
 *
 */
package de.cunctus.MonkeyboardServer;

import de.cunctus.Monkeyboard.Monkeyboard;
import de.cunctus.Monkeyboard.Enums.PlayStatus;

public class MonkeyboardStatusThread extends Thread {

	protected Monkeyboard board;
	protected volatile PlayStatus status;
	
	public MonkeyboardStatusThread(Monkeyboard board) {
		this.board = board;
	}
	
	@Override
	public void run() {
		while (!this.isInterrupted()) {
			this.status = board.GetPlayStatus();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public PlayStatus getPlayStatus() {
		return status;
	}
}
