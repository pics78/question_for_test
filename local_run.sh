#!/bin/bash
# ローカルホストで実行する用

echo "> building..."
./gradlew shadowJar && echo "> OK."

port="8080"
echo "> running on localhost:$port"
java -jar build/libs/*.jar --server.port=$port