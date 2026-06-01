## PreRequsite:
Java 17


## Endpoints
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/events` | Submit a transaction event |
| `GET` | `/events/{id}` | Retrieve a single event by its ID |
| `GET` | `/events?account={accountId}` | List events for an account, ordered by event timestamp |
| `GET` | `/accounts/{accountId}/balance` | Get the current computed balance for an account |

e.g: http://localhost:8080/api/events
    http://localhost:8080/api/events/evt-001

## Sample JSON Payload
{
  "eventId": "evt-001",
  "accountId": "acct-123",
  "type": "CREDIT",
  "amount": 150.00,
  "currency": "USD",
  "eventTimestamp": "2026-05-15T14:02:11Z",
  "metadata": {
    "source": "mainframe-batch",
    "batchId": "B-9042"
  }
}


| Field | Type | Required | Notes |
|---|---|---|---|
| `eventId` | string | Yes | Unique identifier for the event |
| `accountId` | string | Yes | The account this event belongs to |
| `type` | string | Yes | Must be `"CREDIT"` or `"DEBIT"` |
| `amount` | number | Yes | Must be greater than 0 |
| `currency` | string | Yes | e.g., `"USD"` |
| `eventTimestamp` | string (ISO 8601) | Yes | When the event originally occurred |
| `metadata` | object | No | Optional additional context |



