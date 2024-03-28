
# Remmittance Service API Documentation

This document outlines how to test the various endpoints of our Spring Boot application, including User Management, Fund Transfers, Withdrawal Requests, and Authentication (Login). Each section includes a table with endpoint details, HTTP methods, request formats, and example bodies for testing purposes.

## Prerequisites

- Ensure the Spring Boot application is running, typically accessible at `http://localhost:8080`.
- Use Postman or `curl` for testing these endpoints.
- Adjust the request details (e.g., IDs, email, password) as per your application's data.

## User Management API

### Endpoints

| Endpoint               | HTTP Method | Request Format | Body Example |
|------------------------|-------------|----------------|--------------|
| `/users/`              | POST        | JSON           | `{ "userName": "MakerOne", "email": "makerone@example.com", "password": "securePassword123", "bankAccountNumber": "111122223333", "phoneNumber": "1234567890", "role": "MAKER" }` |
| `/users/makers`        | GET         | N/A            | N/A          |
| `/users/checkers`      | GET         | N/A            | N/A          |

## Fund Transfer API

### Endpoints

| Endpoint                                    | HTTP Method | Request Format | Body Example                                      |
|---------------------------------------------|-------------|----------------|---------------------------------------------------|
| `/api/fund-transfers`                       | POST        | JSON           | `{ "senderId": 1, "receiverId": 2, "amount": 500.00, "fromCountry": "CountryA" }` |
| `/api/fund-transfers/received/{receiverId}` | GET         | N/A            | N/A                                               |

## Withdrawal Request API

### Endpoints

| Operation                              | Endpoint                                        | HTTP Method | Request Format | Example Body or Parameters                  |
|----------------------------------------|-------------------------------------------------|-------------|----------------|---------------------------------------------|
| Create a Withdrawal Request            | `/api/withdrawals?fundTransferId={fundTransferId}` | POST        | JSON           | `{ "swiftNumber": "SWIFT123456789", "claimAmount": 1000, "currency": "USD" }` |
| Find Pending Withdrawals by Receiver ID| `/api/withdrawals/pending/{receiverId}`         | GET         | N/A            | N/A                                         |
| Approve a Withdrawal Request           | `/api/withdrawals/{withdrawalRequestId}/approve`| PUT         | N/A            | N/A (Path variable: `{withdrawalRequestId}`) |

## Login Endpoint

### Testing Steps

| Aspect            | Details                                                    |
|-------------------|------------------------------------------------------------|
| **Endpoint**      | `/auth/login`                                              |
| **HTTP Method**   | POST                                                       |
| **Request Format**| JSON                                                       |
| **Body Example**  | `{ "email": "user@example.com", "password": "password123" }` |

## Additional Considerations

- **Headers**: Ensure to include appropriate headers where required, such as `Content-Type: application/json` for JSON request bodies.
- **Environment and Security**: Adjust the request URL based on your deployment environment. Use HTTPS for secure communication, especially for authentication requests.
- **Authentication**: Include necessary authentication tokens in request headers for endpoints that require authenticated access.
