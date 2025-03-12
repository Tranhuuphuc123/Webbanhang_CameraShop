/* dự án khởi tạo cơ sở dữ liệu cho website web bán hàng camera24h*/

-- 1/ tạo database 24h chuẩn utf-8 hỗ trợ viết tiếng việt có dấu luôn 
create database camera24h
character set utf8mb4
collate utf8mb4_unicode_ci;

use camera24h;


/*=============================create table alls=================================*/
-- 2/ tiến hành tạo các table liên quan 
-- a/ table user
  create table acl_users(
	-- unsigned la so khong am
    id int unsigned not null auto_increment primary key,
    userName varchar(200) not null,
    passWord varchar(600) not null,
    last_name varchar(255) not null,
    first_name varchar(255) not null,
    email varchar(255) not null,
    avatar mediumtext,
    -- job_titile la chuc vu
    job_title varchar(255),
    -- department la phong ban
    department varchar(255),
    -- manager_id la khoa ngoai tu than cho chinh bang user voi id
    manager_id int(11) unsigned,
    phone varchar(50) not null,
    address1 varchar(500) not null,
    address2 varchar(500),
    city varchar(255),
    -- state tieu bang
    state varchar(255),
    -- postal_code ma buu chinh dung lam ma don van chuyen
    postal_code varchar(255),
    country varchar(255),
    -- remember_token luu nho mat khau cho lan dang nhap sau
    remember_token varchar(255),
    -- activer_code ma gui xac nhan qua mail khi dang ky tai khoan
    active_code varchar(255),
    status tinyint(4) unsigned not null,
    created_at timestamp,
    updated_at timestamp
  );
  
  
  -- b/ create table acl_roles
  create table acl_roles(
    id int unsigned not null auto_increment primary key,
    name varchar(200) not null,
    -- display_name: ten hien thi tren trinh duyet
     display_name varchar(500),
     -- guard_name la ten quy chuan cho nguoi dung de hieu khong phai ten nghiep vu
     guard_name varchar(200) not null,
    created_at timestamp,
    updated_at timestamp
  );
  
  
  -- create table acle_user_has_roles
  create table acl_user_has_roles(
	id int unsigned not null auto_increment primary key,
    user_id int  unsigned not null,
    role_id int  unsigned not null
  );
  
  
  -- c/ create table acl_permissions
  create table acl_permissions(
     id int unsigned not null auto_increment primary key,
     name varchar(200) not null,
     -- display_name: ten hien thi tren trinh duyet
     display_name varchar(500),
     -- guard_name la ten quy chuan cho nguoi dung de hieu khong phai ten nghiep vu
     guard_name varchar(200) not null,
     created_at timestamp,
     updated_at timestamp
  );
  
  
  -- d/ create ban phu phat sinh cua hai ban acl_role va acl_permissions
  create table acl_rol_has_permissions(
    id int unsigned not null auto_increment primary key,
    permission_id int unsigned not null,
    role_id int unsigned not null
  );
  
  -- e/ create table shop_customers
  create table shop_customers(
    id int unsigned not null auto_increment primary key,
    userName varchar(255) not null,
    passWord varchar(500) not null,
    last_name varchar(255) not null,
    first_name varchar(255) not null,
    email varchar(200) not null,
    avatar varchar(500),
    company varchar(255),
    phone varchar(50) not null,
    billing_address varchar(500),
    shipping_address varchar(500),
    city varchar(255),
    state varchar(255),
    postal_code varchar(20),
    country varchar(255),
    remember_token varchar(255),
    active_code varchar(255),
    status tinyint(4) not null,
    created_at timestamp,
    updated_at timestamp
  );
  
  -- f/ created table shop_payments_types
  create table shop_payment_types(
     id bigint(20) unsigned not null auto_increment primary key,
     payment_code varchar(50) not null,
     payment_name varchar(500) not null,
     description text,
     image text,
     created_at timestamp,
     updated_at timestamp
  );
  
  -- g/ create table shop_order_details
create table shop_order_details(
  order_id int unsigned not null,
  product_id int unsigned not null,
  quantity decimal(18,4) not null,
  unit_price decimal(19,4) not null,
  discount decimal(19,4) not null,  -- nếu bạn muốn dùng decimal thay cho double
  order_detail_status varchar(50),
  date_allocated datetime,
  
  primary key(order_id, product_id)
);

-- h/ create table shop_categories(
 create table shop_categories(
    id int unsigned not null auto_increment primary key,
    category_code varchar(50) not null,
    category_name varchar(500) not null,
    desription text,
    image text,
    created_at timestamp,
    updated_at timestamp
);

-- i/ create table shop_suppliers
create table shop_suppliers(
   id int unsigned not null auto_increment primary key,
   supplier_code varchar(50) not null,
   supplier_name varchar(500) not null,
   description text,
   image text,
   created_at timestamp,
   updated_at timestamp
);

  
-- j/ create table shop_orders
create table shop_orders(
  id int unsigned not null auto_increment primary key,
  employee_id int unsigned not null,
  customer_id int unsigned not null,
  order_date datetime not null,
  shipped_date datetime,
  ship_name varchar(50) not null,
  ship_address1 varchar(500) not null,
  ship_address2 varchar(500),
  ship_city varchar(255),
  ship_state varchar(255),
  ship_postal_code varchar(50),
  ship_country varchar(255) not null,
  shipping_fee decimal(19,4) not null,
  payment_type bigint(20) unsigned not null,
  paid_date datetime,
  order_status varchar(50) not null,
  created_at timestamp,
  updated_at timestamp,
  shop_payment_type_id bigint(20) not null
);

-- k/ create table shop_products
create table shop_products(
	 id int unsigned not null auto_increment primary key,
	 product_code varchar(50) not null,
	 product_name varchar(50) not null,
	 image text,
	 short_description varchar(250),
	 description text,
	 standard_cost decimal(19,4) not null,
	 list_price decimal(19,4) not null,
	 quantity_per_unit varchar(50),
	 discontinued tinyint(4) not null,
	 is_featured bit(1) not null,
	 is_new bit(1) not null,
	 category_id int unsigned not null,
	 supplier_id int unsigned not null,
	 created_at timestamp,
	 updated_at timestamp
   );

-- l/ create table shop_product_reviews
create table shop_products_reviews(
  id int unsigned not null auto_increment primary key,
  product_id int unsigned not null,
  rating float not null,
  comment text not null,
  created_at timestamp,
  updated_at timestamp
);
 
-- m/ create table shop_products_images
create table shop_product_images(
   id int unsigned not null auto_increment primary key,
   product_id int unsigned not null,
   image varchar(500) not null
);
  
  
  
  /*=============================khoi tao khoa ngoai=========================================*/
  /********3/ khoi tao khoa ngoai******************/
  -- khoa ngoai cho bang acl_users
  alter table acl_users add constraint fk_acl_user foreign key(manager_id) references acl_users(id);
  
  -- khoa ngoai cho bang acl_user_has_roles
  alter table acl_user_has_roles add constraint fk_acl_user_has_roles foreign key(user_id) references acl_users(id);
  alter table acl_user_has_roles add constraint fk_acl_user_has_roles1 foreign key(role_id) references acl_roles(id);
  
  -- khoa ngoai cho bang acl_rol_has_permissions
  alter table acl_rol_has_permissions add constraint fk_arhp_aclp foreign key( permission_id) references  acl_permissions(id); 
  alter table acl_rol_has_permissions add constraint fk_arhp_aclr foreign key( role_id ) references  acl_roles(id); 
  
  -- khoa ngoai cua bang shop_orders
  alter table shop_orders add constraint fk_shopOders_aclUser foreign key(employee_id) references acl_users(id);
  alter table shop_orders add constraint fk_shopOders_shopCustomers foreign key(customer_id) references shop_customers(id);
  alter table shop_orders add constraint k_shopOders_shopPaymentTypes foreign key(payment_type) references shop_payment_types(id);
  
  
  -- khoa ngaoi cho bang shop_products
  alter table shop_products add constraint fk_sProducts_sCategories foreign key(category_id) references shop_categories(id);
  alter table shop_products add constraint fk_sProducts_sSupplier foreign key(supplier_id) references shop_suppliers(id);
  
  -- khoa ngoai cho bang shop_order_details
  alter table shop_order_details add constraint fk_orderD_sOder foreign key(order_id) references shop_orders(id);
  alter table shop_order_details add constraint fk_orderD_sProducts foreign key(product_id) references shop_products(id);
  
  -- khoa ngoai cua bang shop_product_revieư
  alter table shop_products_reviews add constraint fk_sProductRv_sProdcuts foreign key(product_id) references shop_products(id);

  
  -- khoa ngoai cho b ang shop_product_images
  alter table shop_product_images add constraint fk_sProductImg_sProduct foreign key(product_id) references shop_products(id);
  
  
  
  
  -- ****************** cau lệnh thực thi truy vấn ******************************
  -- doi ten cot discount trong table shop_products thanh is_featured kieur du lieu la bit
  alter table shop_products change discount is_featured bit(1) not null;
  
  -- them cot moi ten is_new dung sau cot is_featured cuar bang shop_products
  alter table shop_products add column is_new bit(1) not null after is_featured;


use camera24h;
select * from acl_roles;
select * from shop_products;
select * from shop_categories;
select * from shop_suppliers;

-- xóa value trong table
delete from shop_products where id in (22, 23);