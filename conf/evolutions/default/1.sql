# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                        bigint not null,
  name                      varchar(255),
  homephone                 varchar(255),
  mobilephone               varchar(255),
  constraint pk_contact primary key (id))
;

create table user (
  email                     varchar(255) not null,
  hash                      varchar(255) not null,
  constraint pk_user primary key (email))
;

create sequence contact_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists contact;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists contact_seq;

drop sequence if exists user_seq;

