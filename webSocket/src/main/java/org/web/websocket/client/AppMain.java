package org.web.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class AppMain {

	public static void main(String[] args) throws URISyntaxException, IOException, DeploymentException, InterruptedException {
		// Auto-generated method stub
		WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
		WebSocketClient client = new WebSocketClient();
		conmtainer.connectToServer(client, 
				new URI("ws://10.3.6.5:8080/websocket_war/chat?query=Picasso"));
		
		int turn = 0;
		while(turn++ < 10){
			client.send("send text: " + (char)(65 + turn));
			Thread.sleep(1000);
		}
		client.close();
	}

}
