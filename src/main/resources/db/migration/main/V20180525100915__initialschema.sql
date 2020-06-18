DROP TABLE IF EXISTS vehicle;

CREATE TABLE vehicle (
    ID varchar PRIMARY KEY NOT NULL,
    VEHICLENAME varchar NOT NULL,
    VEHICLENO varchar NOT NULL,
    VEHICLEOWNER varchar NOT NULL,
    VEHICLETYPE varchar NOT NULL
);

DROP SEQUENCE IF EXISTS seq_vehicle;
create sequence seq_vehicle;
