For this ATM application I used
-Java
-SpringBoot
-Maven
-IntelliJ
-H2 database
-Thymeleaf, HTML and Bootstrap
I created a memory database with H2 in order to save some accounts (card number, pin, current balance). I saved the pin
in plain view because it is a mock dataBase used to simulate an atm machine. As login credentials you can use the ones
from data.sql for example(cardNumber: 1311222233334444, pin: 1234).
The basic functions are check balance, withdraw a sum of money, as well as the "login" with the credit card and pin.
I also used Thymeleaf, html and Bootstrap for the frontend part.