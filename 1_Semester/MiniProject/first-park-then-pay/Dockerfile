# Stage 1: Build Angular App
FROM node:24-alpine AS build
LABEL APP="FIRST-PAY-THEN-PARK-CLIENT-APP"

WORKDIR /app

COPY package*.json ./

RUN npm cache clean --force && npm install
RUN npm install -g @angular/cli@20.3.5

COPY . .

RUN ng build --configuration=production

# Stage 2: Serve with Nginx
FROM nginx:latest

COPY --from=build /app/dist/first-park-then-pay/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
