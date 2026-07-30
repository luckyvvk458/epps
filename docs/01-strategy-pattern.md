# Step 1: Strategy Pattern in a Spring Payment API

## Goal

Choose the correct payment implementation without filling `PaymentService` with `if/else` or `switch` statements.

## What changed

`PaymentProcessor` is the **strategy** interface. Card, UPI, and wallet processors are concrete strategies. Spring discovers each `@Component`; `PaymentProcessorRegistry` receives the list and maps every strategy to its supported `PaymentMode`.

The service only asks the registry for a processor and delegates the work. To add a new mode, add an enum value and a processor—do not change the service's decision logic.

## Exercise

Add `NET_BANKING` to `PaymentMode`, create a `NetBankingPaymentProcessor`, and add a test that verifies its payment ID prefix.

## Why this comes first

This is an in-memory, deliberately small step. Database transactions, authentication, caches, and distributed services become much easier to reason about after the business behavior is isolated and covered by tests.

## Builder invariant

The HTTP DTO validates `paymentMode`, but the `Payment.Builder` validates it too. DTO validation protects controller input; builder validation protects domain objects created by any other code path.
