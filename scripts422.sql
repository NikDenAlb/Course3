CREATE TABLE cars
(
    car_id  INT PRIMARY KEY,
    brand   VARCHAR(50),
    model   VARCHAR(50),
    cost    NUMERIC(10, 2)
);

CREATE TABLE citizens
(
    citizen_id   INT PRIMARY KEY,
    name         VARCHAR(100),
    age          INT,
    is_driver    BOOLEAN,
    car_id       INTEGER NOT NULL REFERENCES cars(car_id)
);