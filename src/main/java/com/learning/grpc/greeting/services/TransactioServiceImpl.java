package com.learning.grpc.greeting.services;

import com.proto.transaction.Transaction;
import com.proto.transaction.TransactionRequest;
import com.proto.transaction.TransactionResponse;
import com.proto.transaction.TransactionServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransactioServiceImpl extends TransactionServiceGrpc.TransactionServiceImplBase {
    @Override
    public void transact(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        //super.transact(request, responseObserver);
        Transaction transactionRequest = request.getTransaction();
        String name = transactionRequest.getName();
        String mobileNumber = transactionRequest.getMobileNumber();
        int amount = transactionRequest.getAmount();
        String email = transactionRequest.getEmail();
        TransactionResponse response = null;
        if(amount > 10000) {
            response = TransactionResponse.newBuilder()
                    .setStatus("SUCCESS")
                    .setBalanceAmount(12000)
                    .build();
        }
        else {
            response = TransactionResponse.newBuilder()
                    .setStatus("FAILED")
                    .setBalanceAmount(13000)
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
