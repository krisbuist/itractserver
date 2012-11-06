# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table trip_match (
  match_id                  integer auto_increment not null,
  trip_offer_id             integer,
  match_state               varchar(7),
  constraint ck_trip_match_match_state check (match_state in ('OPEN','MATCHED')),
  constraint pk_trip_match primary key (match_id))
;

create table trip_meta_data (
  id                        bigint auto_increment not null,
  crow_flies_distance       bigint,
  calculated_duration       bigint,
  directions_distance       bigint,
  constraint pk_trip_meta_data primary key (id))
;

create table trip_offer (
  id                        integer auto_increment not null,
  user_id                   integer,
  origin_long               double,
  origin_lat                double,
  destination_long          double,
  destination_lat           double,
  start_time_min            bigint,
  start_time_max            bigint,
  end_time_min              bigint,
  end_time_max              bigint,
  number_of_seats           integer,
  meta_data_id              bigint,
  origin_address            varchar(255),
  destination_address       varchar(255),
  constraint pk_trip_offer primary key (id))
;

create table trip_request (
  id                        integer auto_increment not null,
  user_id                   integer,
  origin_long               double,
  origin_lat                double,
  destination_long          double,
  destination_lat           double,
  start_time_min            bigint,
  start_time_max            bigint,
  end_time_min              bigint,
  end_time_max              bigint,
  number_of_seats           integer,
  meta_data_id              bigint,
  origin_address            varchar(255),
  destination_address       varchar(255),
  constraint pk_trip_request primary key (id))
;

create table user (
  id                        integer auto_increment not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  address                   varchar(255),
  postal_code               varchar(255),
  city                      varchar(255),
  country_code              varchar(255),
  date_of_birth             datetime,
  gender                    varchar(255),
  profile_picture           varchar(255),
  constraint pk_user primary key (id))
;

alter table trip_match add constraint fk_trip_match_tripOffer_1 foreign key (trip_offer_id) references trip_offer (id) on delete restrict on update restrict;
create index ix_trip_match_tripOffer_1 on trip_match (trip_offer_id);
alter table trip_offer add constraint fk_trip_offer_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_trip_offer_user_2 on trip_offer (user_id);
alter table trip_offer add constraint fk_trip_offer_metaData_3 foreign key (meta_data_id) references trip_meta_data (id) on delete restrict on update restrict;
create index ix_trip_offer_metaData_3 on trip_offer (meta_data_id);
alter table trip_request add constraint fk_trip_request_user_4 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_trip_request_user_4 on trip_request (user_id);
alter table trip_request add constraint fk_trip_request_metaData_5 foreign key (meta_data_id) references trip_meta_data (id) on delete restrict on update restrict;
create index ix_trip_request_metaData_5 on trip_request (meta_data_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table trip_match;

drop table trip_meta_data;

drop table trip_offer;

drop table trip_request;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

