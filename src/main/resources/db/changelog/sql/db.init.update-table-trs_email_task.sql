ALTER TABLE db_identity.trs_email_task
    ADD COLUMN is_verified BOOLEAN NOT NULL DEFAULT FALSE,            -- Whether the recipient's email is verified
    ADD COLUMN verification_token VARCHAR(255),                       -- Token for email verification
    ADD COLUMN verification_sent_at TIMESTAMP,                        -- Timestamp when the verification email was sent
    ADD COLUMN verification_confirmed_at TIMESTAMP,                   -- Timestamp when the email was confirmed as verified
    ADD COLUMN token_expires_at TIMESTAMP;