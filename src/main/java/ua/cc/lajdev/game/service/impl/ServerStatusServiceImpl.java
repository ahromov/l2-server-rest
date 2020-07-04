package ua.cc.lajdev.game.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.game.model.ServerSettings;
import ua.cc.lajdev.game.service.ServerStatusService;

@Service
public class ServerStatusServiceImpl implements ServerStatusService {

	private static Logger logger = LoggerFactory.getLogger(ServerStatusServiceImpl.class);

	@Autowired
	private ServerSettings settings;

	@Override
	public boolean checkStatus() {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(settings.getIp(), settings.getPort()), 3000);

			return true;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

}