/**
 * @project MonkeyboardServer
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.MonkeyboardClient.RMI
 * @version 
 * @since   13.03.2015
 *
 */
package de.cunctus.MonkeyboardClient.RMI;

import java.rmi.Naming;

import de.cunctus.MonkeyboardServer.RMI.MonkeyboardRMIServerInterface;

public class MonkeyboardRMIClient {
	
	protected MonkeyboardRMIServerInterface serverInterface = null;

	public MonkeyboardRMIClient(String remoteAddress, String nameSpace) throws Exception {
		serverInterface = (MonkeyboardRMIServerInterface) Naming
				.lookup("//" + remoteAddress + "/" + nameSpace);
	}
	
	public MonkeyboardRMIServerInterface getRMIServerInterface() {
		return serverInterface;
	}
}
