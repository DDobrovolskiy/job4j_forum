create table IF NOT EXISTS posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now()
);

CREATE TABLE IF NOT EXISTS roles
(
    id serial primary key,
    title character varying(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id serial primary key,
    username character varying(50) UNIQUE NOT NULL,
    password character varying(100) NOT NULL,
    active boolean DEFAULT true,
    role_id integer NOT NULL,
    CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles (id)
);