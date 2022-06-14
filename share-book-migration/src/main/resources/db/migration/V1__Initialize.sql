-- 利用者
CREATE TABLE user_account (
    id VARCHAR(22) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    ${commonColumns},
    CONSTRAINT user_account_pk PRIMARY KEY (id)
);

CREATE TABLE user_detail (
    user_account_id CHAR(22) PRIMARY KEY REFERENCES user_account,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nick_name VARCHAR(100) NOT NULL,
    birth_day VARCHAR(10),
    profile VARCHAR(1000) NOT NULL,
    address VARCHAR(100) NOT NULL,
    ${commonColumns}
);


-- 書籍
CREATE TABLE book (
    id VARCHAR(22) NOT NULL,
    name VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    date_of_issue VARCHAR(10),
    ${commonColumns},
    CONSTRAINT book_pk PRIMARY KEY (id)
);

CREATE TABLE product (
    id VARCHAR(22) NOT NULL,
    user_account_id VARCHAR(22) NOT NULL,
    book_id VARCHAR(22) NOT NULL,
    description VARCHAR(100) NOT NULL,
    return_date VARCHAR(10),
    rental_period INT NOT NULL,
    ${commonColumns},
    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_user_account_id_fk FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT product_book_id_fk FOREIGN KEY (book_id) REFERENCES book(id) ON UPDATE CASCADE ON DELETE CASCADE
);

--　レンタル
CREATE TABLE rental (
    id VARCHAR(22) NOT NULL,
    product_id VARCHAR(22) NOT NULL,
    user_account_id VARCHAR(22) NOT NULL,
    rental_date VARCHAR(10),
    rental_states VARCHAR(100) NOT NULL,
    ${commonColumns},
    CONSTRAINT rental_pk PRIMARY KEY (id),
    CONSTRAINT rental_user_account_id_fk FOREIGN KEY (user_account_id) REFERENCES user_account(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT rental_product_id_fk FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- 評価
CREATE TABLE review (
    id VARCHAR(22) NOT NULL,
    product_id VARCHAR(22) NOT NULL,
    rental_id VARCHAR(22) NOT NULL,
    evaluation INT NOT NULL,
    comment VARCHAR(100) NOT NULL,
    ${commonColumns},
    CONSTRAINT review_pk PRIMARY KEY (id),
    CONSTRAINT review_product_id_fk FOREIGN KEY (product_id) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT review_rental_id_fk FOREIGN KEY (rental_id) REFERENCES rental(id) ON UPDATE CASCADE ON DELETE CASCADE
);
