# ============================
# Stage 1: Build Maven project
# ============================
FROM maven:3.8.1-ibmjava-8 AS builder

# Đặt thư mục làm việc trong container
WORKDIR /Project_01_SendEmail

# Copy toàn bộ project vào container
COPY src .

# Build project Maven (tạo file WAR)
RUN mvn clean package -DskipTests

# ============================
# Stage 2: Deploy với Tomcat
# ============================
FROM tomcat:9.0.85-jdk17-corretto

# Xóa webapp mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*  # xóa mặc định của tomcat

# Copy file WAR từ giai đoạn build sang Tomcat
COPY --from=builder /Project_CD_download/target/Project_CD_download.war /usr/local/tomcat/webapps/ROOT.war

# Mở port 8080 (Render sẽ dùng port này)
EXPOSE 8080

# Lệnh khởi chạy Tomcat
CMD ["catalina.sh", "run"]
