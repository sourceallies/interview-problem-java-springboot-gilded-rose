CREATE TABLE items (
    id uuid not null primary key
    , sell_in integer not null
    , quality integer not null
    , name text not null
    , description text default null
);

INSERT INTO items
        (id, sell_in, quality, name, description)
values
        ('0ba05fc8-8c57-40c5-8d1a-307557315cd7', 100000, 50, 'TEST_ITEM', 'TEST_ITEM_DESCRIPTION')
;
