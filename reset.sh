#! /bin/bash
#stop containers : spring-boot application and mysql Database
echo "---------- List of images ----------"
docker images -a
echo
echo "---------- Stop containers ----------"
echo "Stop each containers before remove them"
echo
docker stop c_bw-services c_mysql_db_borrowers
echo
echo "---------- Remove containers ----------"
docker rm c_bw-services c_mysql_db_borrowers
echo
echo "---------- Remove images ----------"
docker rmi mmik/borrowers-services mysql
echo
docker images -a
echo
echo "---------- Remove dangling images ----------"
docker rmi $(docker images -f dangling=true -q)
echo
echo "---------- List of remaining images ----------"
docker images -a
echo
echo "---------- List of remaining containers ----------"
docker ps -a
echo
echo "---------- List of active containers ----------"
docker ps
