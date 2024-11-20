
/* INITIATE TABLE users */
CREATE TABLE db_identity.users
(
    id         UUID PRIMARY KEY,                    -- Kolom id sebagai Primary Key
    username   VARCHAR(255) NOT NULL,               -- Username tidak boleh kosong
    email      VARCHAR(255) NOT NULL,               -- Email tidak boleh kosong
    password   VARCHAR(255) NOT NULL,               -- Password tidak boleh kosong
    phone      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),                        -- Nama depan
    last_name  VARCHAR(255),                        -- Nama belakang
    role_id    INTEGER REFERENCES roles (id),          -- Foreign Key ke tabel roles
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Waktu dibuat
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Waktu diperbarui
    last_login TIMESTAMP,                           -- Waktu login terakhir
    updated_by VARCHAR(255),                        -- Diubah oleh
    UNIQUE (username),                              -- Constraint unik untuk username
    UNIQUE (email)                                  -- Constraint unik untuk email
);

/* INITIATE TABLE user_documents */
CREATE TABLE db_identity.user_documents
(
    user_id       UUID REFERENCES users (id) ON DELETE CASCADE, -- Foreign Key ke tabel Users
    uploaded_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- Waktu dokumen diunggah
    document_type VARCHAR(255),                                 -- Jenis dokumen (misal: KTP, SIM, dll.)
    is_verified   BOOLEAN   DEFAULT FALSE,                      -- Status verifikasi dokumen
    PRIMARY KEY (user_id, document_type)                        -- Composite Primary Key (User & Tipe Dokumen)
);

