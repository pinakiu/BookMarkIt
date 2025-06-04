output "ec2_public_ip" {
  value = aws_instance.bookmarkit-backend-ec2.public_ip
}

output "rds_endpoint" {
  value = aws_db_instance.bookmarkitdb.endpoint
}
