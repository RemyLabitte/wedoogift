# Wedoogift
Wedoogift API allowing companies to do gift and meal deposits and also calculate user's balance.

## Installation Guide
Requires:

* MongoDB : Local Database named **_wedoogift_** with collections: 
  * _users_
  * _companies_
> Init database collections using json files in /resources/filesToInitDatabase/

---

```shell scrip  t
# Retrieve the dependencies and check the tests
mvn clean install

# Launch the application in local
mvn spring-boot:run
```
---

* Once application is started and database too, you can access the swagger documentation using this url in local : [http://localhost:8080/swagger-ui.html]()

---

## Resources

1. **User API**
> This resource allows companies to calculate user's balance passing userId in GET request.
2. **GiftDeposit API**
> This resource allows companies to do gift deposits and verify that there is enough on balance to do it.
3. **MealDeposit API**
> This resource allows companies to do meal deposits and verify that there is enough on balance to do it.


## Maintainers
*   **Developper:** Rémy LABITTE [remy.labitte@viacesi.fr]()

