package com.bnk.ecomproj.controller;

import com.bnk.ecomproj.dto.PaymentRequest;
import com.bnk.ecomproj.dto.PaymentResponse;
import com.bnk.ecomproj.dto.PaymentSessionResponse;
import com.bnk.ecomproj.service.PaymentService;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

        @Mock
        private PaymentService paymentServices;

        @InjectMocks
        private PaymentController paymentController;

        private PaymentRequest paymentRequest;
        private String idempotencyKey;

        @Before
        public void setUp() {
            paymentRequest = new PaymentRequest();
            paymentRequest.setPaymentSessionId("ps-123");
            paymentRequest.setAmount(100.50);
            paymentRequest.setReceiver("merchant-001");
            paymentRequest.setPaymentType("CARD");

            idempotencyKey = "idem-key-123";
        }

        // ---------------- createSession() ----------------

        @Test
        public void createSession_shouldReturnResponse_fromService() {
            PaymentSessionResponse mockResponse = new PaymentSessionResponse("ps-abc-123");

            when(paymentServices.createSession()).thenReturn(mockResponse);

            PaymentSessionResponse result = paymentController.createSession();

            assertThat(result).isNotNull();
            assertThat(result.getPaymentSessionId()).isEqualTo("ps-abc-123");
            verify(paymentServices, times(1)).createSession();
        }

        @Test
        public void createSession_shouldReturnNull_whenServiceReturnsNull() {
            when(paymentServices.createSession()).thenReturn(null);

            PaymentSessionResponse result = paymentController.createSession();

            assertThat(result).isNull();
            verify(paymentServices, times(1)).createSession();
        }

        @Test
        public void createSession_shouldPropagateException_whenServiceThrows() {
            when(paymentServices.createSession())
                    .thenThrow(new RuntimeException("Session creation failed"));

            org.junit.Assert.assertThrows(
                    RuntimeException.class,
                    paymentController::createSession
            );
            verify(paymentServices, times(1)).createSession();
        }

        // ---------------- makePayment() ----------------

        @Test
        public void makePayment_shouldReturnSuccessResponse_whenProcessingSucceeds() {
            PaymentResponse expectedResponse = new PaymentResponse("txn-789", "PROCESSED");

            when(paymentServices.processPayment(paymentRequest, idempotencyKey))
                    .thenReturn(expectedResponse);

            PaymentResponse result = paymentController.makePayment(idempotencyKey, paymentRequest);

            assertThat(result).isNotNull();
            assertThat(result.getTransactionId()).isEqualTo("txn-789");
            assertThat(result.getStatus()).isEqualTo("PROCESSED");
            verify(paymentServices, times(1)).processPayment(paymentRequest, idempotencyKey);
        }

        @Test
        public void makePayment_shouldReturnErrorResponse_whenServiceThrowsException() {
            when(paymentServices.processPayment(paymentRequest, idempotencyKey))
                    .thenThrow(new RuntimeException("Gateway timeout"));

            PaymentResponse result = paymentController.makePayment(idempotencyKey, paymentRequest);

            assertThat(result).isNotNull();
            assertThat(result.getErrorMessage()).isEqualTo("Gateway timeout");
            assertThat(result.getTransactionId()).isNull();
            assertThat(result.getStatus()).isNull();
            verify(paymentServices, times(1)).processPayment(paymentRequest, idempotencyKey);
        }

        @Test
        public void makePayment_shouldReturnErrorResponse_withNullMessage_whenExceptionMessageIsNull() {
            when(paymentServices.processPayment(paymentRequest, idempotencyKey))
                    .thenThrow(new NullPointerException());

            PaymentResponse result = paymentController.makePayment(idempotencyKey, paymentRequest);

            assertThat(result).isNotNull();
            assertThat(result.getErrorMessage()).isNull();
        }

        @Test
        public void makePayment_shouldCallService_withCorrectArguments() {
            PaymentResponse expectedResponse = new PaymentResponse("txn-999", "PROCESSED");

            when(paymentServices.processPayment(any(PaymentRequest.class), eq(idempotencyKey)))
                    .thenReturn(expectedResponse);

            paymentController.makePayment(idempotencyKey, paymentRequest);

            verify(paymentServices).processPayment(paymentRequest, idempotencyKey);
        }

        @Test
        public void makePayment_shouldPassPaymentSessionIdCorrectly_toService() {
            PaymentResponse expectedResponse = new PaymentResponse("txn-321", "PROCESSED");

            when(paymentServices.processPayment(any(PaymentRequest.class), eq(idempotencyKey)))
                    .thenReturn(expectedResponse);

            paymentController.makePayment(idempotencyKey, paymentRequest);

            verify(paymentServices).processPayment(
                    argThat(req -> req.getPaymentSessionId().equals("ps-123")
                            && req.getAmount().equals(100.50)
                            && req.getReceiver().equals("merchant-001")
                            && req.getPaymentType().equals("CARD")),
                    eq(idempotencyKey)
            );
        }

        @Test
        public void makePayment_shouldReturnErrorResponse_whenIllegalStateExceptionThrown() {
            when(paymentServices.processPayment(paymentRequest, idempotencyKey))
                    .thenThrow(new IllegalStateException("Idempotency key already used"));

            PaymentResponse result = paymentController.makePayment(idempotencyKey, paymentRequest);

            assertThat(result.getErrorMessage()).isEqualTo("Idempotency key already used");
        }
    }

