package com.learning.grpc.greeting.server;

import com.learning.grpc.greeting.services.AdditionServiceImpl;
import com.learning.grpc.greeting.services.GreetServiceImpl;
import com.learning.grpc.greeting.services.TransactioServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello gRPC!");

        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .addService(new TransactioServiceImpl())
                .addService(new AdditionServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        } ));

        server.awaitTermination();

    }
}
