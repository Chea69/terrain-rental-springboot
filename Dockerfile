FROM eclipse-temurin:23-jdk

RUN apt-get update && apt-get install -y nginx openssh-server git maven php-cli

WORKDIR /app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

RUN mkdir -p /run/sshd

RUN echo 'server { \
listen 80; \
location / { \
proxy_pass http://localhost:8080; \
} \
}' > /etc/nginx/sites-available/default

EXPOSE 80 22 8080

CMD service ssh start && service nginx start && java -jar target/*.jar