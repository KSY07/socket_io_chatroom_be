package com.bizpeer.Chatroom;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;

@SpringBootApplication
public class ChatroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
		URI uri = URI.create("http://localhost:1234/socketApi");
		IO.Options options = IO.Options.builder().build();

		Socket socket = IO.socket(uri, options);
		socket.connect();
	}

}
