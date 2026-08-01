# Step 4: JWT-Protected Benefits Payments

## Boundary

`POST /benefit-payments` is an internal endpoint. The manager-and-finance approval workflow is outside this service; callers must supply an approved benefit reference and authenticate with a JWT.

The endpoint requires the scope `benefits.payments.write`. Spring maps the JWT `scope` or `scp` claim to the authority `SCOPE_benefits.payments.write`.

## Resource server, not token issuer

This service validates JWTs. It does not handle user passwords, login, or token issuance. For local development it validates HS256 tokens using `BENEFITS_JWT_SECRET`. Do not use the fallback secret outside local development. In production, replace the symmetric-secret decoder with your identity provider's issuer/JWK configuration.

## Next boundary

The new controller intentionally delegates to the existing payment flow. The next step will add a separate gateway-selection strategy, selecting an available gateway by a quoted transaction cost before invoking a real gateway client.
