 //Table: users
Create Table: CREATE TABLE `users` (
  `id` char(36) NOT NULL,
  `name` varchar(64) NOT NULL,
  `socialid` char(70) DEFAULT NULL,
  `registrationdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastlogindate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(64) DEFAULT NULL,
  `logintype` enum('social','password') DEFAULT NULL,
  PRIMARY KEY (`id`)
  UNIQUE KEY `email` (`email`)
);


    //   Table: books
Create Table: CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `language` varchar(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
);
      // Table: buyer_requests
Create Table: CREATE TABLE `buyer_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid_price` int(11) DEFAULT '0',
  `bookid` int(11) NOT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_fullfilled` tinyint(1) DEFAULT '0',
  `userid` char(36) NOT NULL,
  `bid_currency` varchar(255) DEFAULT NULL,
  `delivery_address` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

      // Table: buyer_seller_request_mapping
Create Table: CREATE TABLE `buyer_seller_request_mapping` (
  `transid` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_request_id` int(11) NOT NULL,
  `seller_request_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('rejected_by_buyer','rejected_by_seller','pending','deliverd') NOT NULL DEFAULT 'pending',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rejection_code` int(11) DEFAULT NULL,
  `reason` text,
  PRIMARY KEY (`transid`)
);

 //      Table: seller_requests
Create Table: CREATE TABLE `seller_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_price` int(11) NOT NULL DEFAULT '0',
  `seller_currency` varchar(50) DEFAULT 'RS.',
  `actual_price` int(11) NOT NULL DEFAULT '0',
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` char(36) NOT NULL,
  `bookid` int(11) NOT NULL,
  `dispatch_address` int(11) NOT NULL,
  `weight` decimal(2,2) NOT NULL,
  PRIMARY KEY (`id`)
);


  //     Table: store
Create Table: CREATE TABLE `store` (
  `bookid` int(11) NOT NULL,
  `bookcount` int(11) NOT NULL DEFAULT '0',
  `isdeleted` tinyint(1) DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookid` (`bookid`),
);

  //     Table: addresses
Create Table: CREATE TABLE `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `address_line_1` varchar(100) NOT NULL,
  `address_line_2` varchar(100) DEFAULT NULL,
  `userid` char(36) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isdefault` tinyint(1) DEFAULT '1',
  `zip` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
 );

Create Table: CREATE TABLE `rejection_reasons` (
  `id` int(11) NOT NULL,
  `reason` varchar(150) NOT NULL,
  `type` enum('buyer','seller') NOT NULL,
  PRIMARY KEY (`id`)
);





