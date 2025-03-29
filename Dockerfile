# 1. Amazon Corretto 17 사용 (JDK 17)
FROM amazoncorretto:17

# 2. JAR 파일 변수 설정(Gradle 사용시 경로)
ARG JAR_FILE=build/libs/*.jar

# 3. JAR 파일 복사
COPY ${JAR_FILE} load_balancing_back.jar

# 4. 실행 명령어 (spring.profiles.active=prod 설정)
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/load_balancing_back.jar"]

# 실행환경을 나누지않고, 현재 프로젝트 build
#ENTRYPOINT ["java", "-jar", "/load_balancing_back.jar"]
