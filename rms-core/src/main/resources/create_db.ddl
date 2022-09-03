create table users(
	id bigint AUTO_INCREMENT,
	username varchar(50) not null,
	version smallint,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

create table recipes (
	id bigint AUTO_INCREMENT,
	name varchar(50) not null,
	description varchar(700),
	veg tinyint not null,
	serves tinyint not null,
	instructions varchar(2000) not null,
	ingredients varchar(2000) not null,
	version smallint,
	CONSTRAINT recipe_pk PRIMARY KEY (id)
);

create table fav_recipes (
	user_id bigint,
	recipe_id bigint,
    CONSTRAINT fav_recipes_user_fk FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fav_recipes_recipe_fk FOREIGN KEY(recipe_id) REFERENCES recipes(id)
);

