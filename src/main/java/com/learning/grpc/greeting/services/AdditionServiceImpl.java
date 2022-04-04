package com.learning.grpc.greeting.services;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.Numbers;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.stub.StreamObserver;

public class AdditionServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void add(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        //super.add(request, responseObserver);
        Numbers numbers = request.getNumbers();
        long firstNumber = numbers.getFirstNumber();
        long secondNumber = numbers.getSecondNumber();

        long sum = firstNumber + secondNumber;

        SumResponse response = SumResponse.newBuilder()
                .setSum(sum)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
