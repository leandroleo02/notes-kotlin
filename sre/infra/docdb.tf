//resource "aws_security_group" "mongodb_allowed" {
//  name = "Mongodb cluster"
//  vpc_id = aws_vpc.main.id
//
//  ingress {
//    from_port = 27017
//    protocol = "tcp"
//    to_port = 27017
//    cidr_blocks = ["187.20.177.144/32"]
//  }
//
//  egress {
//    from_port = 0
//    protocol = "-1"
//    to_port = 0
//    cidr_blocks = ["0.0.0.0/0"]
//  }
//}
//
//resource "aws_docdb_cluster_instance" "cluster_instances" {
//  count              = 1
//  identifier         = "docdb-cluster-notes-api-${count.index}"
//  cluster_identifier = aws_docdb_cluster.default.id
//  instance_class     = "db.t3.medium"
//}
//
//resource "aws_docdb_cluster" "default" {
//  cluster_identifier = "docdb-cluster-notes-api"
//  availability_zones = ["us-east-1a", "us-east-1b", "us-east-1c"]
//  master_username    = "notesapi"
//  master_password    = "g48SoATJAiSA7HVsQwJz"
//  vpc_security_group_ids = [aws_security_group.mongodb_allowed.id]
//}