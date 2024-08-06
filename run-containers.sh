#! /bin/bash
echo "------------------------------------------------"
echo "|                                               |"
echo "|        Docker containers run Script           |"
echo "|                                               |"
echo "| Description:                                  |"
echo "| Used after launch reset.sh script to be sure  |"
echo "| everything is clean before run the containers |"
echo "------------------------------------------------"
echo
echo "---------- Clean project ----------"
./gradlew clean
echo
echo
echo "---------- Run Mysql DB container : c_bw_db ----------"
echo " Mysql DB Container name : c_bw_db                    "
echo " Expose 3306 : port of the DB availability            "
echo "------------------------------------------------------"
docker run --name c_bw_db -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mysql_db_borrowers -p 3306:3306 mysql:latest
echo
docker ps | grep c_bw*
echo
echo "---------- Build docker project by skipping test task ----------"
./gradlew docker
echo
docker ps -a | grep c_bw*
echo
echo "---------- Run Spring Boot Borrowers services container ----------"
echo " API Container name : c_bw_services "
echo " Expose 8090 : port of the API availability"
echo " Expose 8091 : port for the WS client swagger-ui.html "
echo "------------------------------------------------------------------"
docker run --name c_bw_services -d -p 8090:8090 -p 8091:8091 mehdimik/borrowers:1.0.0-SNAPSHOT
echo
echo
docker ps -a | grep c_bw*
echo
echo
docker ps | grep c_bw*
echo
echo
