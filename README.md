| AWS Service | Purpose                                                     | Notes                                                                  |
| ----------- | ----------------------------------------------------------- | ---------------------------------------------------------------------- |
| **EC2**     | Host Java backend (Spring Boot jar) or frontend (optional)  | ✅ Use for backend unless you switch to ECS or Elastic Beanstalk later |
| **RDS**     | Managed PostgreSQL database                                 | ✅ Replace local Docker PostgreSQL                                     |
| **S3**      | Store frontend static files (React build) + optional assets | ✅ Best way to host frontend statically                                |
| **VPC**     | Custom networking setup (subnets, routing, security groups) | ✅ Needed for EC2 + RDS setup                                          |
| **IAM**     | Secure access between services + GitHub Actions permissions | ✅ Use IAM roles and least-privilege policies                          |
