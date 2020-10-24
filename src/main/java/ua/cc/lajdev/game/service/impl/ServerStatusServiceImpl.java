package ua.cc.lajdev.game.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.ServerSettings;
import ua.cc.lajdev.game.service.ServerStatusService;

@Service
public class ServerStatusServiceImpl implements ServerStatusService {

	private final ServerSettings settings;

	@Autowired
	public ServerStatusServiceImpl(ServerSettings settings) {
		this.settings = settings;
	}

	@Override
	public void checkStatus() throws IOException {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(settings.getIp(), settings.getPort()), 3000);
		socket.close();
	}

}
