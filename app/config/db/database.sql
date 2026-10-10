CREATE TABLE IF NOT EXISTS TYPE (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS DATA (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    evento INTEGER NOT NULL DEFAULT 2,
    obj TEXT NOT NULL,
    quando TEXT,
    done BOOLEAN NOT NULL DEFAULT FALSE,
    creato TEXT,

    FOREIGN KEY (evento) REFERENCES TYPE(id)
);

CREATE TABLE IF NOT EXISTS MAIL_ACCOUNT (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    provider TEXT NOT NULL,
    service TEXT,
    address TEXT NOT NULL,
    account_identifier TEXT,
    username TEXT,
    auth_method TEXT NOT NULL,
    credential_reference TEXT,
    access_token TEXT,
    refresh_token TEXT,
    expires_at TEXT,
    scopes TEXT
);

CREATE TABLE IF NOT EXISTS VAULT (
    id INTEGER PRIMARY KEY CHECK (id = 1),
    salt BLOB NOT NULL,
    verifier BLOB NOT NULL,
    iterations INTEGER NOT NULL
);

INSERT OR IGNORE INTO TYPE (tipo) VALUES ('B-Day');
INSERT OR IGNORE INTO TYPE (tipo) VALUES ('appointment');
INSERT OR IGNORE INTO TYPE (tipo) VALUES ('remind');