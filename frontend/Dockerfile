# Étape 1  Build du frontend
FROM node:18-alpine AS builder
WORKDIR /app

# Copier les fichiers de dépendances et installer
COPY package*.json ./
RUN npm install

# Copier tout le code et build
COPY . .
RUN npm run build

# Étape 2  Serveur NGINX pour servir le build
FROM nginx:1.25-alpine
COPY --from=builder /app/build /usr/share/nginx/html

# Exposer le port 80 pour Nginx
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
