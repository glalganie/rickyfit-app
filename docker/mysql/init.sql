-- Questo script è opzionale se la creazione dello schema è gestita da JPA (hibernate.ddl-auto).
-- Può essere usato per creare tabelle o dati iniziali non gestiti dall'applicazione.
-- Esempio: Creazione di piani di abbonamento.
CREATE TABLE IF NOT EXISTS membership_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_in_months INT NOT NULL,
    description TEXT
);

INSERT INTO membership_plans (name, price, duration_in_months, description) VALUES
('Accesso Palestra', 29.00, 1, 'Perfetto per iniziare senza pressione.'),
('Corsi & Coach', 49.00, 1, 'Spinta vera, guida reale.'),
('Full Experience', 69.00, 1, 'Elite mode: nessun limite.')
ON DUPLICATE KEY UPDATE name=name; -- Non fare nulla se esistono già