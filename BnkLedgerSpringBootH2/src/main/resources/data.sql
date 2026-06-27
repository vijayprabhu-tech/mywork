INSERT INTO amount_current (event_id, account_id, type, amount, currency, event_timestamp, event_timestamp_str, metadata)
 VALUES ('evt-001', 'acct-123', 'CREDIT', 750000.00, 'USD', '2024-01-15', '2024-01-15', 'source:mainframe-batch, batchId:B-9042'),
        ('evt-002', 'acct-123', 'CREDIT', 250010.00, 'USD', '2024-01-16', '2024-01-16', 'source:mainframe-batch, batchId:B-9042'),
        ('evt-003', 'acct-124', 'CREDIT', 600000.00, 'USD', '2024-01-16', '2024-01-16', 'source:mainframe-batch, batchId:B-9043'),
        ('evt-005', 'acct-123', 'DEBIT', 500000.00, 'USD', '2024-01-16', '2024-01-16', 'source:mainframe-batch, batchId:B-9043'),
        ('evt-006', 'acct-125', 'CREDIT', 400000.00, 'USD', '2024-01-17', '2024-01-17', 'source:mainframe-batch, batchId:B-9044');

