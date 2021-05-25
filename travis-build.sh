#!/bin/bash

EXIT_STATUS=0

echo "Running tests with geb.env chromeHeadless"
./gradlew -Dgeb.env=dev check || EXIT_STATUS=$? > Travis-Build_Log.txt

exit $EXIT_STATUS