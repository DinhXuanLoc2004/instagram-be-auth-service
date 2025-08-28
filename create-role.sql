GRANT ALL PRIVILEGES ON `auth-db`.* TO 'auth-vault-username'@'%';

GRANT CREATE USER, RELOAD, SELECT, SHOW DATABASES, GRANT OPTION
ON *.* TO 'auth-vault-username'@'%';

FLUSH PRIVILEGES;
