#!/bin/bash

set -e

EXIT_STATUS=0

#chmod 777 ./gradlew
#
#echo "Running tests with gradle geb.env chromeHeadless"
#./gradlew -Dgeb.env=chromeHeadless check || EXIT_STATUS=$?
#
#echo "Running tests with gradle geb.env firefoxHeadless"
#./gradlew -Dgeb.env=firefoxHeadless iT || EXIT_STATUS=$?

chmod 777 ./grailsw

echo "Running tests with grailsw unit"
./grailsw test-app -unit

echo "Running tests with grailsw integration"
./grailsw test-app -integration

exit $EXIT_STATUS