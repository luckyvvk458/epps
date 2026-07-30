# Step 3: Persisting a Payment Transaction

## Goal

Keep a payment record after the HTTP request ends. The record starts as `PENDING` and is marked `SUCCESS` only after the selected processor finishes.

## Why `Payment` and `PaymentTransaction` are different

`Payment` is the immutable input to business processing. `PaymentTransaction` is a mutable JPA entity that records the payment lifecycle in the database. They have different responsibilities and should not be merged merely because they contain some of the same data.

## `@Transactional`

`PaymentService.paymentResponse` is marked `@Transactional`. Spring opens one database transaction for the method. The inserted transaction and its status update are committed together when the method returns normally; a runtime exception causes the database work to roll back.

This is a database transaction, not a distributed payment-gateway transaction. A real external charge cannot be undone simply by rolling back a database row. We will address that reliability boundary later with idempotency and asynchronous processing.

## Exercise

Add a repository test that creates a `PaymentTransaction`, calls `markSuccessful()`, and verifies that the persisted status is `SUCCESS`.
