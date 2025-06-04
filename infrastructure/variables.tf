variable "region" { default = "us-east-1" }

variable "db_username" {}

variable "db_password" {}

variable "key_name" { description = "BMK-EC2-Key-Pair" }

variable "instance_type" { default = "t2.micro" }
