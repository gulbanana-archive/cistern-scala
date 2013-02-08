# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "Thread" ("id" VARCHAR NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL);
create table "Board" ("id" VARCHAR NOT NULL PRIMARY KEY,"name" VARCHAR NOT NULL);

# --- !Downs

drop table "Thread";
drop table "Board";

