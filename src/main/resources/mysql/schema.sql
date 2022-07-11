create table review_event
(
    id             BIGINT                              NOT NULL AUTO_INCREMENT
        primary key,
    user_id       varchar(255)                        not null,
    review_id     varchar(255)                        not null,
    place_id      varchar(255)                        not null,
    content_point int                                 not null,
    image_point   int                                 not null,
    first_point   int                                 not null,
    action        enum ('ADD', 'MOD', 'DELETE')   not null,
    created_at    timestamp default CURRENT_TIMESTAMP not null
);

create index IDX_place_first_point
    on review_event (place_id, first_point);

create index IDX_user
    on review_event (user_id);

create index IDX_created
    on review_event (created_at);

