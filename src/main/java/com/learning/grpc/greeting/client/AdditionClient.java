package com.learning.grpc.greeting.client;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.Numbers;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AdditionClient {
    public static void main(String[] args) {
       ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
               .usePlaintext()
               .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub additionClient = CalculatorServiceGrpc.newBlockingStub(channel);

        Numbers numbers = Numbers.newBuilder()
                .setFirstNumber(493298459)
                .setSecondNumber(985784576)
                .build();

        SumRequest sumRequest = SumRequest.newBuilder()
                .setNumbers(numbers)
                .build();

        SumResponse response = additionClient.add(sumRequest);
        System.out.println(response.toString());
        channel.shutdown();
    }
}
