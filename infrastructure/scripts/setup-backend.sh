#!/bin/bash
sudo yum update -y
sudo amazon-linux-extras install java-openjdk11 -y

cd /home/ec2-user
git clone https://github.com/yourusername/yourrepo.git
cd yourrepo/backend
./mvnw clean package
java -jar target/your-app.jar --spring.datasource.url=jdbc:postgresql://<DB-ENDPOINT>:5432/myappdb --spring.datasource.username=mydbuser --spring.datasource.password=strongpassword123
