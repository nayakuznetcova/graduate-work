--liquibase formatted sql

--changeset nayakuznetcova:create_images
create table images
(
    id         serial primary key,
    file_size  bigint,
    media_type text,
    path       text
);

--changeset nayakuznetcova:create_users
create table users
(
    id         serial primary key,
    email      varchar(255),
    username   varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    phone      varchar(255),
    password   varchar(255),
    role       varchar(255),
    image_id   int,
    foreign key (image_id) references images (id)
);

--changeset nayakuznetcova:create_ads
create table ads
(
    id          serial primary key,
    price       int,
    title       varchar(255),
    description text,
    user_id     int,
    foreign key (user_id) references users (id),
    image_id    int,
    foreign key (image_id) references images (id)
);

--changeset nayakuznetcova:create_comments
create table comments
(
    id         serial primary key,
    created_at date,
    text       text,
    user_id    int,
    foreign key (user_id) references users (id)
);

--changeset smakrushin:alter_table_comments
ALTER TABLE comments ADD COLUMN ad_id int

--changeset smakrushin:alter_table_comments2
ALTER TABLE comments ADD CONSTRAINT fk_comments_ads
                  FOREIGN KEY (ad_id)
                  REFERENCES ads(id);

--changeset smakrushin:alter_table_comments3
ALTER TABLE comments DROP COLUMN created_at

--changeset smakrushin:alter_table_comments4
ALTER TABLE comments add  created_at bigint





