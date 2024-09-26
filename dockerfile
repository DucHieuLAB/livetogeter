# Sử dụng một image cơ bản của JDK để chạy ứng dụng
FROM openjdk:17-jdk-alpine

# Thiết lập thư mục làm việc bên trong container
WORKDIR /app

# Copy file JAR của ứng dụng vào container
COPY target/livetogeter.jar app.jar

# Expose cổng ứng dụng
EXPOSE 8080

# Lệnh để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
