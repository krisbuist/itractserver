# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table trip_offer (
  id                        integer auto_increment not null,
  profile_id                integer,
  origin_long               double,
  origin_lat                double,
  destination_long          double,
  destination_lat           double,
  start_time_min            bigint,
  start_time_max            bigint,
  end_time_min              bigint,
  end_time_max              bigint,
  number_of_seats           integer,
  constraint pk_trip_offer primary key (id))
;

create table trip_request (
  id                        integer auto_increment not null,
  profile_id                integer,
  origin_long               double,
  origin_lat                double,
  destination_long          double,
  destination_lat           double,
  start_time_min            bigint,
  start_time_max            bigint,
  end_time_min              bigint,
  end_time_max              bigint,
  number_of_seats           integer,
  constraint pk_trip_request primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table trip_offer;

drop table trip_request;

SET FOREIGN_KEY_CHECKS=1;

