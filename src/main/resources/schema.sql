	create table article (
        id bigint not null auto_increment,
        created_by varchar(255),
        created_date datetime,
        last_modified_by varchar(255),
        last_modified_date datetime,
        description varchar(255),
        published bit,
        text longtext not null,
        title varchar(255) not null,
        author_id bigint,
        primary key (id)
    )

    create table `payment_card` (
        `id` bigint not null auto_increment,
        `created_by` varchar(255),
        `created_date` datetime,
        `last_modified_by` varchar(255),
        `last_modified_date` datetime,
        `card_number` integer not null,
        `cvv` integer not null,
        owner_id bigint,
        primary key (`id`)
    )

    create table `user` (
        `id` bigint not null auto_increment,
        `created_by` varchar(255),
        `created_date` datetime,
        `last_modified_by` varchar(255),
        `last_modified_date` datetime,
        `email` varchar(255) not null,
        `firstName` varchar(255) not null,
        `gender` varchar(255),
        `lastName` varchar(255) not null,
        `password` varchar(255),
        `phone` varchar(255) not null,
        primary key (`id`)
    )

    	alter table `article` add constraint UK_571gx7oqo5xpmgocegaidlcu9  unique (`title`)

        alter table `payment_card` add constraint UK_4a8ue50ap4v73sdjetrx6u2jp  unique (`card_number`)

        alter table `user` add constraint UK_ob8kqyqqgmefl0aco34akdtpe  unique (`email`)

        alter table `article` add constraint FK_ebjpyd2e71hvfsuaiflecjl6a foreign key (author_id) references `user` (`id`)

        alter table `payment_card` add constraint FK_oov0epva8hlwiqltfvb7ss0w1 foreign key (owner_id) references `user` (`id`)
        
        
        
        
        