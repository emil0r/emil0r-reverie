CREATE TABLE objects_jumbatron (
       id BIGSERIAL PRIMARY KEY,
       object_id BIGINT NOT NULL REFERENCES reverie_object(id),
       period text not null default '',
       title text not null default '',
       client text not null default '',
       css text not null default '',
       tech text not null default '',
       text text not null default ''
);
