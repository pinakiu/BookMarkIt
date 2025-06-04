provider "aws" {
  region = var.region
}

# Template to inject DB values into user_data
data "template_file" "setup_backend" {
  template = file("${path.module}/scripts/setup-backend-template.sh")

  vars = {
    db_endpoint = aws_db_instance.bookmarkitdb.address
    db_name     = aws_db_instance.bookmarkitdb.db_name
    db_username = var.db_username
    db_password = var.db_password
  }
}

# Security Group
resource "aws_security_group" "allow-ec2" {
  name = "allow-ec2"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["YOUR_PUBLIC_IP/32"] # Replace with your IP
  }

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Dev only
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# RDS PostgreSQL
resource "aws_db_instance" "bookmarkitdb" {
  identifier             = "bookmarkitdb"
  engine                 = "postgres"
  engine_version         = "15.4"
  instance_class         = "db.t3.micro"
  allocated_storage      = 20
  db_name                = "bookmarkitdb1"
  username               = var.db_username
  password               = var.db_password
  publicly_accessible    = true
  skip_final_snapshot    = true
  vpc_security_group_ids = [aws_security_group.allow-ec2.id]
}

# EC2 Instance
resource "aws_instance" "bookmarkit-backend-ec2" {
  ami                    = "ami-0c02fb55956c7d316" # Amazon Linux 2
  instance_type          = var.instance_type
  key_name               = var.key_name
  vpc_security_group_ids = [aws_security_group.allow-ec2.id]

  user_data = data.template_file.setup_backend.rendered

  tags = {
    Name = "SpringBootBackend"
  }

  depends_on = [aws_db_instance.bookmarkitdb]
}
