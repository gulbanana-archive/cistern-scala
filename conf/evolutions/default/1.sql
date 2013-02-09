# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "User" ("id" VARCHAR NOT NULL PRIMARY KEY,"username" VARCHAR NOT NULL,"registered" TIMESTAMP NOT NULL);
create table "Post" ("id" VARCHAR NOT NULL PRIMARY KEY,"content" VARCHAR NOT NULL,"posted" TIMESTAMP NOT NULL,"userID" VARCHAR NOT NULL,"threadID" VARCHAR NOT NULL);
create table "Thread" ("id" VARCHAR NOT NULL PRIMARY KEY,"subject" VARCHAR NOT NULL,"posted" TIMESTAMP NOT NULL,"userID" VARCHAR NOT NULL,"boardID" VARCHAR NOT NULL);
create table "Board" ("id" VARCHAR NOT NULL PRIMARY KEY,"title" VARCHAR NOT NULL);
alter table "Post" add constraint "post_thread" foreign key("threadID") references "Thread"("id") on update NO ACTION on delete NO ACTION;
alter table "Post" add constraint "post_user" foreign key("userID") references "User"("id") on update NO ACTION on delete NO ACTION;
alter table "Thread" add constraint "thread_user" foreign key("userID") references "User"("id") on update NO ACTION on delete NO ACTION;
alter table "Thread" add constraint "thread_board" foreign key("boardID") references "Board"("id") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "Post" drop constraint "post_thread";
alter table "Post" drop constraint "post_user";
alter table "Thread" drop constraint "thread_user";
alter table "Thread" drop constraint "thread_board";
drop table "User";
drop table "Post";
drop table "Thread";
drop table "Board";

