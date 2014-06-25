drop database test_web;
create database test_web;
use  test_web;


create table user (				
	userID varchar(30) primary key unique,
	password varchar(30),
	email varchar(60),
	admin boolean,
	points int
);
create table post (		
	postID int auto_increment primary key,
	authorID varchar(30),
	likeCount int,
	dislikeCount int,
	commentCount int,
	timestamp timestamp,
	title varchar(30),
	status varchar(500),
	type varchar(30),
	attachment varchar(200),
	active boolean,
	foreign key (AuthorID) references user(userID)
);

create table liked_post (
	postID int,
	userID varchar(30),
	foreign key (postID) references post(postID),
	foreign key (userID) references user(userID)
);

create table disliked_post (
	postID int,
	userID varchar(30),
	foreign key (postID) references post(postID),
	foreign key (userID) references user(userID)
);

create table comment_post (
	postID int,
	commenterID varchar(30),
	text varchar(1000),
	timestamp timestamp,
	foreign key (postID) references post(postID),
	foreign key (CommenterID) references user(userID)
);

create table product (
	productID int auto_increment primary key,
	title varchar(40),
	price double,
	image varchar(300),
	description varchar (1000)
);

create table orders (
	orderID int auto_increment primary key,
	userID varchar(40),
	timestamp timestamp,
	address varchar(100),
	foreign key (userID) references user(userID)
);

create table order_product (
	orderID int,
	productID int,
	foreign key (orderID) references orders(orderID),
	foreign key (productID) references product(productID)
);

create table cart (
	cartID int auto_increment primary key,
	userID varchar(40),
	price int,
	foreign key (userID) references user(userID)
);

create table cart_product (
	cartID int,
	productID int,
	foreign key(cartID) references cart(cartID),
	foreign key(productID) references product(productID)
);

create table user_product (
	userID varchar(20),
	productID int,
	foreign key(userID) references user(userID),
	foreign key(productID) references product(productID)
);
