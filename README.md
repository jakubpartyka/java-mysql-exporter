# java-mysql-exporter
universal java mysql exporter

## What is this?
It's a simple application written in Java that allows you to expose Prometheus metrics basing on defined SQL queries. 

## Why?
Because integrating Grafana with MySQL database was too time-consuming for me. Setting MySQL database as datasource is simple, but writting queries that will actually show what I needed was way more complex. Using java-mysql-exporter all you need to do to create new metric is to add a query line to config file. 
