# Chatop - API REST

API back-end pour l'application Chatop, une plateforme de mise en relation entre locataires et propriétaires pour la location de biens immobiliers.

## 📋 Description

Chatop est une API RESTful développée avec Spring Boot qui permet de gérer :
- L'authentification et l'autorisation des utilisateurs (JWT)
- La gestion des locations immobilières (création, consultation, modification)
- L'envoi de messages entre utilisateurs

## 🚀 Technologies utilisées

- **Java 25**
- **Spring Boot 4.0.1**
  - Spring Security
  - Spring Data JPA
  - Spring Web MVC
  - OAuth2 Resource Server
- **MySQL** - Base de données
- **SpringDoc OpenAPI** - Documentation API (Swagger)
- **JWT** - Authentification par token

## 📁 Architecture

Le projet suit une architecture hexagonal (en couches) :

```
src/main/java/com/getsu/chatop/
├── application/         # Couche présentation (Endpoints)
│   ├── auth/            
│   ├── message/         
│   ├── rental/          
│   └── user/            
├── config/              # Configuration Spring Security
├── domain/              # Logique métier
│   └── service/         
└── infrastructure/      # Accès aux données
    ├── models/          
    └── repository/      
```

## 🔧 Prérequis

- Java JDK 25 ou supérieur
- Maven 3.6+
- MySQL 8.0+

## ⚙️ Installation

### 1. Cloner le projet

```bash
git clone <url-du-repo>
cd chatop
```

### 2. Configurer la base de données

Créez une base de données MySQL :

```sql
CREATE DATABASE chatop;
```

Exécutez le script SQL fourni pour créer les tables :

```bash
mysql -u votre_utilisateur -p chatop < src/main/resources/script.sql
```

### 3. Configuration des variables d'environnement

Créez un fichier `.env` à la racine du projet avec les variables suivantes :

```properties
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/chatop
DB_USERNAME=votre_utilisateur
DB_PASSWORD=votre_mot_de_passe

# JWT Configuration
JWT_SECRET_KEY=votre_clé_secrète_jwt_très_longue_et_sécurisée
```

### 4. Installer les dépendances

```bash
./mvnw clean install
```

## 🏃 Démarrage de l'application

### Mode développement

```bash
./mvnw spring-boot:run
```

L'application démarre sur `http://localhost:8080`

## 📚 Documentation API

Une fois l'application démarrée, la documentation Swagger est accessible à :

- **Swagger UI** : http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON** : http://localhost:8080/api-docs

## 🔒 Authentification

L'API utilise JWT (JSON Web Token) pour l'authentification. 

1. Utilisez `/api/auth/register` ou `/api/auth/login` pour obtenir un token
2. Incluez le token dans l'en-tête de vos requêtes :
   ```
   Authorization: Bearer <votre_token>
   ```

## 📝 Build pour la production

```bash
./mvnw clean package
```

Le fichier JAR sera généré dans le dossier `target/`

Pour lancer l'application en production :

```bash
java -jar target/chatop-0.0.1-SNAPSHOT.jar
```
---
