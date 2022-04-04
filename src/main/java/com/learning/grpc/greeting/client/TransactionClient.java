package com.learning.grpc.greeting.client;

import com.proto.transaction.Transaction;
import com.proto.transaction.TransactionRequest;
import com.proto.transaction.TransactionResponse;
import com.proto.transaction.TransactionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TransactionClient {
    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        TransactionServiceGrpc.TransactionServiceBlockingStub transctionClient = TransactionServiceGrpc.newBlockingStub(channel);

        Transaction transaction = Transaction.newBuilder()
                .setName("Siddharth Kumar")
                .setMobileNumber("7763866262")
                .setAmount(9000)
                .setEmail("siddharth.curious@gmail.com")
                .build();

        TransactionRequest transactionRequest = TransactionRequest.newBuilder()
                .setTransaction(transaction)
                .build();

        TransactionResponse response = transctionClient.transact(transactionRequest);
        System.out.println(response.toString());
        channel.shutdown();
    }
}
