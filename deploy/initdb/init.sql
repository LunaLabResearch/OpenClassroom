-- Create user and database for Spring application
CREATE USER spring_user WITH PASSWORD 'spring_password';
CREATE DATABASE openclassroom OWNER spring_user;
GRANT ALL PRIVILEGES ON DATABASE openclassroom TO spring_user;

-- Create user and database for Keycloak
CREATE USER keycloak_user WITH PASSWORD 'keycloak_password';
CREATE DATABASE keycloak OWNER keycloak_user;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak_user;
