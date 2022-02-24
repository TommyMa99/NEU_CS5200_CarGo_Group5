# Group 5 - CarGo
# Bingfan Tian, Bo Chen, 
# Jianqing Ma, Kaiwen Zhou, 
# Qihui Liu, Sen Yan
# CS5200 - PM2
# 2/24/2022

USE CarGoApplication;
DROP TABLE IF EXISTS Imports;

# Create a temp Imports table to hold raw data
create table Imports(
	ImportId 		int 	AUTO_INCREMENT,
    Year 			int,
    Make 			varchar(255),
    Model 			varchar(255),
    Trim 			varchar(255),
    Body 			varchar(255),
    Transmission 	varchar(255),
    Vin 			varchar(255),
    State 			varchar(255),
    Odometer 		int,
    CarCondition 	double,
    Color 			varchar(255),
    Interior 		varchar(255),
    Seller 			varchar(255),
    Mmr 			int,
    SellingPrice 	double,
    SaleDate 		varchar(255),
    constraint pk_RecordsId primary key (ImportId)
);

# Import raw data into Imports table
LOAD DATA INFILE '/usr/local/tmp/car_prices.csv' INTO TABLE Imports
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@Year, @Make, @Model, @Trim, @Body, @Transmission, @Vin, @State, @CarCondition, @Odometer, @Color, @Interior, @Seller,
	@Mmr, @SellingPrice, @SaleDate)
	SET
	Year = nullif(@Year,''),
	Make = nullif(@Make,''),
	Model = nullif(@Model,''),
	Trim = nullif(@Trim,''),
    Body = nullif(@Body,''),
	Transmission = nullif(@Transmission,''),
	Vin = nullif(@Vin,''),
	State = nullif(@State,''),
    Odometer = nullif(@Odometer,''),
	CarCondition = nullif(@CarCondition,''),
	Color = nullif(@Color,''),
	Interior = nullif(@Interior,''),
	Seller = nullif(@Seller,''),
	Mmr = nullif(@Mmr,''),
	SellingPrice = nullif(@SellingPrice,''),
	SaleDate = nullif(@SaleDate,'')
;

# Since the original data is a transaction record, 
# there is a case that a vehicle is traded multiple times. 
# It is necessary to clean up the raw data and 
# remove the repeated vehicle vin numbers.
DROP TABLE IF EXISTS Imports_temp;

create table Imports_temp like Imports;

insert into Imports_temp
select *
from Imports
where Vin not in (
      SELECT Vin
		FROM Imports
		GROUP BY Vin
		HAVING COUNT(Vin) > 1
     );

drop table Imports;

alter table Imports_temp rename to Imports;

# remove the all vehicle before 2010.
DROP TABLE IF EXISTS Imports_temp;

create table Imports_temp like Imports;

insert into Imports_temp
select *
from Imports
where Year > 2010;

drop table Imports;

alter table Imports_temp rename to Imports;

# insert data into Cars tables after cleaning the data
insert into Cars(
	Vin, Year, Make, Model, 
    Trim, Body, Transmission, 
    State, Odometer, CarCondition, 
    Color, Interior, Mmr)
select Vin, Year, Make, Model, 
		Trim, Body, Transmission, 
        State, Odometer, CarCondition, 
        Color, Interior, Mmr
from Imports;

drop table Imports;