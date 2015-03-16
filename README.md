# MonkeyboardServer
A standalone Java-Server for the Monkeyboard Java Wrapper

You need both the Monkeyboard (https://github.com/cunctus/Monkeyboard) and the MonkeyboardServer (this) projects. The server is in early development stage and not all commands are implemented yet. But so far it is working fine. Please report any errors to help me improving the Server.

If you use eclipse/maven all dependencies will be downloaded automatically. You can also use the precompiled .jar-files from the releases. It contains all dependencies.

Example for running the server on raspbian; this will start the server using the device /dev/ttyACM0, the lib keystonecomm and listen on port 23000 for TCP/IP connections. You can use e.g. telnet for connecting with the server on the port.

sudo java -Djava.library.path=/usr/lib/ -jar MonkeyboardServer-0.0.1.jar /dev/ttyACM0 keystonecomm 23000


See "commands.txt" for available command-list.
