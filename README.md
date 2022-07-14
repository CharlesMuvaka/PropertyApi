## Property-Api
- This is an api built using the spark framework.It is able to post,retrieve,update, and delete records from a Postgres database.It is able to retrieve add new properties and show the relationships between the manager, tenants and the units. This prevents entry of non-corresponding values into tables throwing the corresponding relevant exception messages as retrieve data to help resolve errors. description

## Note:
- For easy testing of endpoints it is much easier to use the h2 persistent database because it is faster to delete and recreate rather than reset database completely if need be in postgres. Delete the 'newss-Api.db' file in the home directory to achieve this.
- Uncomment or Comment out the h2 or postgres Sql2o line depending on which database you want to use for testing.
- To use the postgres database run the create.sql script in the src/main/resources/sql folder in the project directory or run the following:

###   CREATE DATABASE  my_property;

    \c my_property ;

    CREATE TABLE defects
      (
         id serial PRIMARY KEY,
         description VARCHAR NOT NULL,
         tenant_id VARCHAR NOT NULL,
         string_uri VARCHAR NOT NULL,
         unit_name VARCHAR NOT NULL,
         manager_name VARCHAR NOT NULL,
         property_name VARCHAR NOT NULL,
         created_at timestamp
      );

    CREATE TABLE done_defects
      (
        id serial PRIMARY KEY,
        name VARCHAR NOT NULL,
        uri VARCHAR NOT NULL,
        contactor_name VARCHAR NOT NULL,
        contractor_phone VARCHAR NOT NULL,
        contractor_location VARCHAR NOT NULL,
        manager_name VARCHAR NOT NULL,
        tenant_id VARCHAR NOT NULL,
        created_at timestamp
      );


    CREATE TABLE property
    (
       id serial PRIMARY KEY,
       property_name VARCHAR NOT NULL,
       property_location VARCHAR NOT NULL,
       manager_name VARCHAR NOT NULL
    );


    CREATE TABLE property_managers
    (
       id serial PRIMARY KEY,
       manager_name VARCHAR NOT NULL,
       phone_number VARCHAR NOT NULL,
       email VARCHAR NOT NULL
    );


    CREATE TABLE tenant
    (
        id serial PRIMARY KEY,
        tenant_name VARCHAR NOT NULL,
        tenant_email VARCHAR NOT NULL,
        tenant_phone VARCHAR NOT NULL,
        tenant_id VARCHAR NOT NULL,
        property_name VARCHAR NOT NULL,
        unit_name VARCHAR NOT NULL,
        manager_name VARCHAR NOT NULL,
        joined TIMESTAMP
    );

                
    CREATE TABLE units
    (
         id serial PRIMARY KEY,
         unit_name VARCHAR NOT NULL,
         property_name VARCHAR NOT NULL,
         unit_rooms VARCHAR NOT NULL
    );

### Note the database name is my_property if using the postgres database

## Make sure you enter the correct username and password in the db class inside the dao package when using postgres
- Prerequisites
- Knowledge of On Git CLI
- Java 11
- Gradle as a built tool
- IntelliJ or any Java based IDE
- postgres SQL sever installed
- Postman desktop client installed
- Technologies
- Java 11
- Gradle
- Spark
- JUnit
- Postgres Sql
- Installation
- Clone or download repository as an archive
- If archive unzip the archive to get project folder
- Open the application's root folder in an IDE that can execute builds in java using gradle e.g. IntelliJ or Eclipse.
- Navigate to the App.java file and run the main class
- For testing use Postman desktop client to access the forked collection because their cloud client does not access local ports like localhost:4567 or 0.0.0.0:4567.
- In postman replace where indicated :id the number with the id you want to use in the path variables
- Make sure you recreate the database in your local psql by running the psql commands above, or you have switched to the h2 database
- If you do not fork the already created postman collection make sure to enter and label yours correctly to avoid errors because of extra spaces or missing characters
- You can use the sample data in the sample jsons folder to make testing faster
- Some sample endpoint responses using the Articles and Staff Objects

  ## add a property manager

    POST /property-api/property-managers
    {
        "manager_name": "John Doe",
        "phone_number": "0712345678",
        "email": "
Response

    {
        "manager_name": "John Doe",
        "phone_number": "0712345678",
        "email": "
    }

## Add a tenant

    POST /property-api/tenants
     }
Response

    {
        "tenant_name": "John Doe",
        "tenant_email": "
    }   
-Response if tenant does not exist

    {
        "message": "Tenant does not exist"
    }
-Response if tenant exists
       
         {
         "errorMessage": "The tenant you are trying to add already exists",
         "status": 400
         }

     
# get all property managers
Response

    {
    "property_managers": [
        {
            "id": 1,
            "manager_name": "John Doe",
            "phone_number": "0712345678",
            "email": "

        }
         {
            "id": 2,
            "manager_name": "ping pang",
            "phone_number": "0712345678",
            "email": "

        }
    ]

   

# get property manager by id

    Entry
    {
    "id": 1
    }
# Delete property manager by id

    Entry
    {
    "id": 1
    }
-If property manager does not Exist

    {
        "message": "Property manager does not exist"
    }
#If property manager Exists.
-Example at id=2,deletes entry and gets a list off all articles without deleted one to confirm deletion.

    [
        {
            "id": 1,
            "manager_name": "John Doe",
            "phone_number": "0712345678",
            "email": "
        },
        {
            "id": 3,
            "manager_name": "ping pang",
            "phone_number": "0712345678",
            "email": "
        }
    ]
# get all managers properties

    Entry
    {
    "id": 1
    }
-If managers property with id:4 doesn't exist

    {
    "errorMessage": "The mananger property with id:4 does not exist thus cant be retrieved",
    "status": 404
    }
-If managers property with id:4 exists

        {
            "id": 4,
            "property_name": "John Doe",
            "property_location": "
        }

     }

 ## Delete all managers properties
 
    Entry
    {
    "id": 1
    }
- If manager properties has entries, it deletes and return an empty array
[]
- If manager properties is already empty

    {
    "errorMessage": "Staff is already empty, cant delete all",
    "status": 404
    }

# updating a propertyManager

    Entry
    {
     "id": 1
    } and the new values
    {
        "manager_name": "John Doe",
        "phone_number": "0712345678",
        "email": "
    }


# get all tenants
Response

    {
    "tenants": [
        {
            "id": 1,
            "tenant_name": "John Doe",
            "tenant_email": "
        },
        {
            "id": 2,
            "tenant_name": "ping pang",
            "tenant_email": "
        }
    ]
    }

# get all tenants in the same Property
Response

    {
    "tenants": [
        {
            "id": 1,
            "tenant_name": "John Doe",
            "tenant_email": "
        },
        {
            "id": 2,
            "tenant_name": "ping pang",
            "tenant_email": "
        }
    ]
    }

 # get tenant by id
     
        Entry
        {
        "id": 1
        }
# Delete tenant by id
         
            Entry
            {
            "id": 1
            }

# get all units
Response

    { 
    "units": [
        {
            "id": 1,
            "unit_name": "John Doe",
            "unit_rooms": "
        },
        {
            "id": 2,
            "unit_name": "ping pang",
            "unit_rooms": "
        }
    ]
    }
# add a defect

    POST /property-api/defects
    {
        "defect_name": "John Doe",
        "defect_description": "
    Response
    {
        "defect_name": "John Doe",
        "defect_description": "
    }

# get defect by id
         
            Entry
            {
            "id": 1
            }
# Delete defect by id
             
                Entry
                {
                "id": 1
                }

# get all defects  
Response

    {
    "defects": [
        {
            "id": 1,
            "defect_name": "John Doe",
            "defect_description": "
        },
        {
            "id": 2,
            "defect_name": "ping pang",
            "defect_description": "
        }
    ]
    }


NOTE: Not all individual response behaviors are covered by the samples but all response types are covered by the samples to provide a correct idea of what responses will look like endpoints are hit
To Contribute or Fix bug
To fix a bug or enhance an existing module, follow these steps:

- Fork the repo
- Create a new branch (git checkout -b improve-feature)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (git commit -m 'Improve feature')
- Push to the branch (git push origin improve-feature)
- Create a Pull Request
- BUGS FOUND
If you come across any bug in the project kindly report using the link below

Link link
Licence
MIT License
Copyright (c) 2022 Charles Muvaka

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Support and contact details
