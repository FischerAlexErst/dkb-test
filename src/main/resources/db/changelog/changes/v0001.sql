create schema if not exists test;

create table if not exists  test.t_url_info (
  id bigserial not null,
  url TEXT not null,
  hash varchar(50) not null,
  primary key (id)
);