# incremental-migration
Tests on migrating a database in an incremental way, without downtime.

The aim is to try 3 operations on a database on table `person` without requirering a downtime:
* Add a `NOT NULL` column with a `DEFAULT` value
* Drop a column
* Rename a column

At each step of the migration process, the following endpoints will be available:
* `GET` - `/person/jpa`: Get a list of `Person` using JPA to retrieve the data from the database
* `GET` - `/person/jdbc`: Get a list of `Person` using JDBC to retrieve the data from the database
* `POST` - `/person/jpa`: Create a new `Person` using JPA to save the data to the database
* `POST` - `/person/jdbc`: Get a list of `Person` using JDBC to save the data to the database

All tags starting by `v` corresponds to version of the database schema, with the corresponding application code that manage this schema. At each version, the database schema can run with the current version (`n`), as well as the previous version (`n-1`). This enables to upgrade the version incrementally on multiple webservers, without having to shut them down all at the same time, and thus prevent downtime.

There is a commit by step. To simulate the migration, the application should be run as described below. At each step, the endpoints can be called to ensure that the application is still working properly in the given version.

## Initial version
Checkout tag `v1` and run the application. A H2 database will be created with the initial schema and some data in it.

## Add a `NOT NULL` column with a `DEFAULT` value
The aim is to create a new column, `country` to table `person`. This column should be `NOT NULL` and existing rows should be filled with value 'US'.

### Step 1
Checkout tag `v2-nullable-country-column` and run the application. A new nullable column named `country` will be added to the database.

### Step 2
Checkout tag `fill-country` and run the application.
A new `GET` endpoint is available: `/fill/country`. Call this endpoint. It will fill the newly created `country` column with a default value.

### Step 3
Checkout tag `v3-non-nullable-country-column` and run the application. Column `country` will be set as `NOT NULL`.

## Drop a column
The aim is to drop column `phone_number` from table `person`.
### Step 1
Checkout tag `v4-nullable-phone-number-column` and run the application. Column `phone_number` will be set to nullable.

### Step 2
Checkout tag `v5-drop-phone-number-column` and run the application. Column `phone_number` will be dropped.

## Rename a column
The aim is to rename column `last_name` of table `person` to `family_name`.
### Step 1
Checkout tag `v6-nullable-family-name-column` and run the application. A new nullable column named `family_name` will be added to the database.

### Step 2
Checkout tag `fill-family-name` and run the application.
A new `GET` endpoint is available: `/fill/family-name`. Call this endpoint. It will fill the newly created `family-name` column with a default value.

### Step 3
Checkout tag `v7-nullable-last-name-column` and run the application. Column `last_name` will be set to nullable.

### Step 4
Checkout tag `v8-drop-last-name-column` and run the application. Column `last_name` will be dropped.
