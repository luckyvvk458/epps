# Step 2: Controller Validation and API Errors

## Goal

Validate input at the HTTP boundary and return useful errors instead of calling the service with invalid data.

## Request path

`POST /payments` is handled in this order:

1. Jackson converts JSON into `PaymentRequest`.
2. `@Valid` checks the request's validation annotations.
3. A valid request reaches `PaymentService`.
4. An invalid request raises `MethodArgumentNotValidException`.
5. `ApiExceptionHandler` converts that exception into a consistent `400` JSON response.

## Tests

`PaymentControllerTest` is a `@WebMvcTest`. It starts the MVC web layer, but replaces `PaymentService` with a mock. This isolates controller behavior from the payment strategies and keeps the test fast.

## Exercise

Add a test for an amount of `0`. Verify that the response is `400` and the `fieldErrors` object contains `amount`.
