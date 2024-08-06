#! /bin/bash
#stop containers : spring-boot application and mysql Database
echo "---------- List of images ----------"
docker images -a | grep c_bw*
echo
echo "---------- Stop containers ----------"
echo "Stop each containers before remove them"
echo
docker stop c_bw_services c_bw_db
echo
echo "---------- Remove containers ----------"
docker rm c_bw_services c_bw_db
echo
echo "---------- Remove images ----------"
docker rmi mehdimik/borrowers:1.0.0-SNAPSHOT mysql:9.0.0
echo
docker images -a | grep c_bw*
echo
echo "---------- Remove dangling images ----------"
docker rmi $(docker images -f dangling=true -q)
echo
echo "---------- List of remaining images ----------"
docker images -a | grep mmik*
echo
echo "---------- List of remaining containers ----------"
docker ps -a | grep c_bw*
echo
echo "---------- List of active containers ----------"
docker ps | grep c_bw*
