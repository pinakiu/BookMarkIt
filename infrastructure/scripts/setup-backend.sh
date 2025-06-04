#!/bin/bash
sudo yum update -y
sudo amazon-linux-extras install java-openjdk11 -y
sudo yum install git -y

cd /home/ec2-user
git clone --branch develop_05_do_infra https://github.com/pinakiu/BookMarkIt.git
cd BookMarkIt/backend

./mvnw clean package

java -jar target/*.jar \
  --spring.datasource.url=jdbc:postgresql://${db_endpoint}:5432/${db_name} \
  --spring.datasource.username=${db_username} \
  --spring.datasource.password=${db_password}
