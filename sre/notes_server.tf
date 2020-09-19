provider "aws" {
  profile = "default"
  region  = "us-east-1"
}

resource "aws_vpc" "main" {
  cidr_block = "10.1.0.0/16"
  enable_dns_hostnames = true
  tags = {
    Name = "Main vpc for notes api"
  }
}

resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id
}

resource "aws_subnet" "main" {
  cidr_block = "10.1.1.0/24"
  vpc_id = aws_vpc.main.id
  availability_zone = "us-east-1a"
}

resource "aws_route_table" "default" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main.id
  }
}

resource "aws_route_table_association" "main" {
  subnet_id = aws_subnet.main.id
  route_table_id = aws_route_table.default.id
}

resource "aws_security_group" "allow_ssh" {
  name = "Allow all ssh connections"
  vpc_id = aws_vpc.main.id

  ingress {
    from_port = 22
    protocol = "tcp"
    to_port = 22
    cidr_blocks = ["187.20.177.144/32"]
  }

  egress {
    from_port = 0
    protocol = "-1"
    to_port = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "webserver" {
  ami           = "ami-0817d428a6fb68645"
  instance_type = "t2.medium"
  key_name      = "root"
  subnet_id     = aws_subnet.main.id
  security_groups = [aws_security_group.allow_ssh.id]
  associate_public_ip_address = true

  tags = {
    Name = "webserver"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt update",
      "sudo apt --fix-broken install",
      "sudo apt install default-jre -y",
      "java -version"
    ]

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/Downloads/root.pem")
      host        = self.public_ip
    }
  }
}

resource "aws_eip" "webserver" {
  instance = aws_instance.webserver.id
  vpc = true
  depends_on = [aws_internet_gateway.main]
}

resource "aws_lb_target_group" "main" {
  name     = "notes-webservers"
  port     = 15000
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
}

resource "aws_lb_target_group_attachment" "main" {
  target_group_arn = aws_lb_target_group.main.arn
  target_id        = aws_instance.webserver.id
  port             = 15000
}