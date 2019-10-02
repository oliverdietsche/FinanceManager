insert into project (id, title)
values  (1, 'Schule');

insert into category (id, title)
values  (1, 'Material'),
        (2, 'Ausfluege');

insert into payment (id, amount, title, `date`, description, project_id, category_id)
values  (1, 49.95, 'Formelsammlung', TO_DATE('02/10/2019', 'DD/MM/YYYY'), 'Eine Formelsammlung fuer Mathematik und Physik', 1, 1);

insert into user (id, `name`, password)
values  (1, 'user', '$2a$10$jgK/iszM3xKjkzcu0qH4wO.miIZ/drwybwVyp.3xSjyO5BdtKepLy');
-- password: password

insert into role (id, `name`)
values  (1, 'ADMIN');

insert into user_roles (users_id, roles_id)
values (1, 1);