## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Bugs / Issues

Update and delete functionality not implemented for any class on the front end.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE viewers (id serial PRIMARY KEY, ranger boolean, name varchar, phone varchar, ranger_number int);`
* `CREATE TABLE wildlife_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, color varchar, description varchar, gender varchar, endangered boolean);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, location varchar, time Timestamp, animal_id int, viewer_id int);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

### License

Copyright (c) 2017 **_MIT License_**
