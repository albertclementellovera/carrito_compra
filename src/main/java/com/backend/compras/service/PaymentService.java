package com.backend.compras.service;

import com.backend.compras.config.BraintreeConfig;
import com.backend.compras.dto.ClientTokenDto;
import com.backend.compras.dto.PurchaseDto;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {
    
    @Autowired
    BraintreeConfig config;

    public BraintreeGateway getGateway() {
        return new BraintreeGateway(
                Environment.SANDBOX,
                config.getMerchantId(),
                config.getPublicKey(),
                config.getPrivateKey()
        );
    }
    
    public ClientTokenDto getToken() {
        ClientTokenRequest request = new ClientTokenRequest();
        String token = getGateway().clientToken().generate(request);
        return new ClientTokenDto(token);
    }
    
    public Result<Transaction> checkout(PurchaseDto dto){
        TransactionRequest request = new TransactionRequest()
            .amount(dto.getAmount()) 
            .paymentMethodNonce(dto.getNonce())
            .options()
              .submitForSettlement(true)
              .done();
        return getGateway().transaction().sale(request);
    }
}
    