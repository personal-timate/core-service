# core-service


## Setup

````shell
# Run MariaDB container
docker run --detach --name timate-mariadb --env MARIADB_USER=timate --env MARIADB_PASSWORD=timate --env MARIADB_ROOT_PASSWORD=timate -p "3306:3306" --env MARIADB_DATABASE=timate  mariadb:latest
````