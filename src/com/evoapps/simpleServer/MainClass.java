package com.evoapps.simpleServer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class MainClass {

	/**
	 * @param args
	 */
	 public static void main(String[] list) throws Exception {
	      //Container container = new Server();
		 Container container = new ServerSearchXML();
	      ContainerServer server = new ContainerServer(container);
	      Connection connection = new SocketConnection(server);
	      SocketAddress address = new InetSocketAddress(8001);

	      connection.connect(address);
	   }

}
