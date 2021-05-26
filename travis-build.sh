#!/bin/bash

set -e

EXIT_STATUS=0

chmod 777 ./gradlew

echo "Running tests with geb.env chromeHeadless"
./gradlew -Dgeb.env=chromeHeadless check || EXIT_STATUS=$?

echo "Running tests with geb.env firefoxHeadless"
./gradlew -Dgeb.env=firefoxHeadless iT || EXIT_STATUS=$?

exit $EXIT_STATUS