#!/bin/bash

set -e

EXIT_STATUS=0

echo "Running tests with geb.env chromeHeadless"

chmod 777 ./gradlew
./gradlew -Dgeb.env=chromeHeadless check || EXIT_STATUS=$? #

exit $EXIT_STATUS