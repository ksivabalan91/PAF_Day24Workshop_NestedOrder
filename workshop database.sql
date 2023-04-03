-- day 24 workshop
create database workshop;
use workshop;

drop table orders;
drop table order_details;

create table order_details(
	id 				int not null auto_increment,
    order_id		int,
    product 		varchar(64),
    unit_price		decimal(10,2),
    discount		decimal(3,2) default (1.0),
    quantity		int,
    constraint		order_details_pk	primary key (id),
    constraint 		orders_fk	foreign key (order_id)	references orders(id)
);
insert into order_details(order_id, product, unit_price, discount, quantity) values(1,'knife',500.0,0.11,6);
create table orders(
	id				int not null auto_increment,
    order_date		date,
    customer_name	varchar(128),
    ship_address	varchar(128),
    notes			text,
    tax				decimal(2,2) default (0.05),
    constraint	orders_pk	primary key (id)
);
select * from orders;
select * from order_details order by order_id;

insert into order_details(order_id, product, unit_price, discount, quantity) values(1,'RTX 4090',9.99,0.1,1);

INSERT INTO orders(order_date, customer_name, ship_address, notes) VALUES ('2023-04-03', 'John Doe', '123 Main St', 'Test Order');
INSERT INTO order_details(order_id, product, unit_price, discount, quantity) VALUES (1, 'RTX 4090', 999.99, 0.1, 1);