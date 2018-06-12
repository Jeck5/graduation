# Voting system Test Task
## Task
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

## API and curl examples
### Get restaurants available for voting on some date:
curl "http://localhost:8090/votingsystem/rest/profile/menu?date=INTERESTED_DATE -u EMAIL:PASSWORD

examples:
* curl "http://localhost:8090/votingsystem/rest/profile/menu?date=2018-05-09" -u john@mail.com:john
* curl "http://localhost:8090/votingsystem/rest/profile/menu?date=2018-05-19" -u peter@mail.com:peter

### Vote for restaurant:
curl -H "Content-Type: application/json" -X POST http://localhost:8090/votingsystem/rest/profile/vote -d "RESTAURANT_ID" -u EMAIL:PASSWORD
examples:
* curl -H "Content-Type: application/json" -X POST http://localhost:8090/votingsystem/rest/profile/vote -d "1005" -u john@mail.com:john
* curl -H "Content-Type: application/json" -X POST http://localhost:8090/votingsystem/rest/profile/vote -d "1004" -u peter@mail.com:peter

API for first vote for some date and revotes is the same. If user revotes after 11-00, votingsystem won't 
change his vote. In all cases user's vote for date will be returned.