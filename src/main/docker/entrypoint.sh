#!/bin/bash

# Check if LOG_RESET is set to true
if [ "$LOG_RESET" = "true" ]; then
  # Remove log file if it exists
  rm -f /app/logs/borrowers-services.log
fi

# Start your application
exec java -jar /app/app.jar