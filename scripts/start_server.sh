#!/usr/bin/env bash

java -jar -Dspring.profiles.active=dev /tmp/notes-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &