#!/bin/bash

set -e

EXIT_STATUS=0

# Permissões
chmod 777 ./gradlew
chmod 777 ./grailsw

#echo "Running tests with gradle geb.env chromeHeadless"
#./gradlew -Dgeb.env=chromeHeadless check || EXIT_STATUS=$?
#
#echo "Running tests with gradle geb.env firefoxHeadless"
#./gradlew -Dgeb.env=firefoxHeadless iT || EXIT_STATUS=$?

echo "Running tests with grailsw tests"
./grailsw test-app

#echo "Running tests with grailsw integration"
#./grailsw test-app -integration

exit $EXIT_STATUS