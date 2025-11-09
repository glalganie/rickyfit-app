
 <img width="100" height="100" alt="RickyFit_Logo" src="https://github.com/user-attachments/assets/7e81bd75-7ba7-4d9a-99ea-003861894ad6" />
Applicazione Gestionale per Palestre

**RickyFit** è un'applicazione web full-stack progettata per digitalizzare e ottimizzare la gestione di una moderna palestra. Fornisce strumenti intuitivi per l'amministrazione (gestione abbonamenti, corsi) e un'esperienza coinvolgente per gli iscritti (prenotazioni, monitoraggio progressi).

---

## 🚀 Stack Tecnologico

L'applicazione è costruita su uno stack tecnologico moderno, robusto e scalabile.

| Area | Tecnologia |
| :--- | :--- |
| **Backend** | Java 17, Spring Boot 3 (Web, Data JPA, Security, Validation) |
| **Frontend** | Thymeleaf (Server-Side Rendering), HTML5, CSS3, Vanilla JS |
| **Database** | MySQL 8.0 (Produzione), H2 Database (Sviluppo & Test) |
| **Sicurezza** | Spring Security (Autenticazione Form-Based, Hashing BCrypt, Autorizzazione per Ruoli) |
| **DevOps** | Docker, Docker Compose (pronto per Azure e altri Cloud) |
| **Testing** | JUnit 5, Mockito, MockMvc |

---

## 🛠️ Setup e Avvio

Ci sono due modi per avviare l'applicazione: localmente per lo sviluppo rapido (con H2) o tramite Docker per un ambiente di produzione simulato (con MySQL).

### 1. Avvio Locale (per Sviluppo)

1.  **Prerequisiti:**
    *   Java Development Kit (JDK) 17 o superiore.
    *   Apache Maven 3.8 o superiore.

2.  **Compilazione:** Dalla root del progetto, esegui il comando:
    ```bash
    mvn clean install
    ```

3.  **Esecuzione:** Avvia l'applicazione con il seguente comando:
    ```bash
    java -jar target/rickyfit-gym-app-*.jar
    ```

4.  **Accesso:** L'applicazione sarà disponibile su `http://localhost:8080`. Il database H2 in-memory sarà accessibile su `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:rickyfitdb`, User: `sa`, Password: `password`).

### 2. Avvio con Docker (Ambiente di Produzione)

Questa è la modalità consigliata per testare l'applicazione in un ambiente identico a quello di produzione.

1.  **Prerequisiti:**
    *   Docker e Docker Compose installati e in esecuzione.

2.  **Esecuzione:** Dalla root del progetto, lanciare il comando:
    ```bash
    docker-compose up --build -d
    ```
    *   Il comando costruirà l'immagine Docker dell'applicazione e avvierà i due container: `rickyfit-app` (il backend) e `rickyfit-db` (il database MySQL).

3.  **Accesso:** L'applicazione sarà disponibile su `http://localhost:8080`.

4.  **Per fermare e rimuovere i contenitori:**
    ```bash
    docker-compose down -v
    ```

---

## 🌐 Credenziali Utenti Demo

L'applicazione viene inizializzata con i seguenti utenti demo per testare i diversi ruoli:

| Ruolo | Email | Password | Accesso Principale |
| :--- | :--- | :--- | :--- |
| **ADMIN** | `ricky@fit.it` | `Admin123` | Tutte le pagine + `/admin/**` |
| **TRAINER** | `martina@fit.it` | `Trainer123` | Pagine utente + Gestione Corsi |
| **MEMBER** | `glalganie@fit.it`| `Member123` | Pagine utente (`progressi`, `corsi`) |

---

## 📐 Architettura e Funzionalità

L'applicazione segue un pattern **MVC (Model-View-Controller)** con una chiara separazione delle responsabilità (Controller, Service, Repository) per garantire manutenibilità e testabilità.

### Funzionalità Principali (MVP):
*   **Gestione Abbonamenti:** L'Admin può creare, modificare ed eliminare Piani di Abbonamento.
*   **Prenotazione Corsi:** Gli utenti possono visualizzare un calendario dinamico e prenotare posti per le sessioni, con controllo della capienza in tempo reale.
*   **Dashboard Progressi:** Gli utenti possono visualizzare i propri KPI di allenamento, lo stato dell'abbonamento e ricevere suggerimenti da un **AI Coach Mock**.
*   **Pannello Admin:** Un'area protetta per la gestione di Piani, Corsi, Sessioni e Ruoli Utente.

### Endpoint Principali (REST API):
*   `GET /api/v1/courses`: Recupera la lista di tutti i corsi (JSON). Accessibile a tutti gli utenti autenticati.
*   `POST /api/v1/courses`: Crea un nuovo corso. Accessibile solo agli ADMIN.

