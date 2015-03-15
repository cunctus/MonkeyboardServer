/**
 * @project MonkeyboardServer
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.MonkeyboardServer.RMI
 * @version 
 * @since   08.03.2015
 *
 */
package de.cunctus.MonkeyboardServer.RMI;

import java.rmi.Remote;

import de.cunctus.Monkeyboard.Interfaces.IMonkeyboard;

public interface MonkeyboardRMIServerInterface extends IMonkeyboard, Remote {

}
