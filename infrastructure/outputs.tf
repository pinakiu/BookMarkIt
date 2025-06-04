output "ec2_public_ip" {
  value = aws_instance.bookmarkit-backend-ec2.public_ip
}

output "db_endpoint" {
  value = aws_db_instance.bookmarkitdb.address
}
