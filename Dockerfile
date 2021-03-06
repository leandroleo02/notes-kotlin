FROM adoptopenjdk/openjdk11

ARG DEPENDENCY=build/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "-Dspring.profiles.active=${SPRING_PROFILE}", "com.example.notes.ApplicationKt"]