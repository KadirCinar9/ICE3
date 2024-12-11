FROM gradle:8-jdk21-alpine AS builder

# Çalışma dizini oluştur
WORKDIR /app

# JAR dosyasını kopyala
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]

