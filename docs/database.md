# Database Setup

## Create the Database

```bash
$ psql postgres
postgres=# create role store_admin with login password 'letmeintothestore';
CREATE ROLE
postgres=# create database store_db with owner store_admin;
CREATE DATABASE

```
