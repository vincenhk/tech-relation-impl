CREATE TABLE db_identity.trs_email_task
(
    id         SERIAL PRIMARY KEY,                              -- Unique identifier for the task
    subject    VARCHAR(255) NOT NULL,                           -- Subject of the email
    recipient  VARCHAR(255) NOT NULL,                           -- Email address of the recipient
    body       TEXT         NOT NULL,                           -- Body content of the email
    status     VARCHAR(50)  NOT NULL DEFAULT 'PENDING',         -- Status of the task (e.g., PENDING, SENT, FAILED)
    created_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP, -- Timestamp for when the task was created
    updated_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP  -- Timestamp for when the task was last updated
);