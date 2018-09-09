# budjen

# Design

## User Journey

1. User runs application.
1. Application prints report.

## Requirements

1. The application should show the user how much money they make every month.
1. The application should show the user how much money they spend every month.
1. The application should show the user how much money they have saved every month and for the year.
1. The application should show the user what they are spending their money on.
1. The application should deal with money in pounds sterling.
1. The application should deal with time using the absolute calendar month and year.
1. The application should use the English language for input and output.
1. The application should be built in the Java 8 programming language.
1. The user will be the person who invokes the application.
1. A purse is a representation of a number of money in pounds sterling.
1. How much money is made per month will be represented in terms of a credit to the purse.
1. How much money is spent per month will be represented in terms of an expenditure from the purse.
1. How much money is saved per month will be represented in terms of the difference between the credit and the expenditure.
1. How much money is saved per year will be a cumulative count of the difference between the credit and the expenditure in a calendar year.
1. The user will interact with the application and enter particular details for each transaction.
1. Each transaction will include a transaction type, a transaction description and a number of money in pounds sterling.
1. The transaction type will be in the form of a credit or an expenditure.
1. The transaction description will be formed of a list of credit and expenditure options.
1. All money entered by the user into the purse will be in non-negative values.
1. The application will display credit and expenditure in non-negative values.

## 1st Sprint - created 23/08/2018
1. The application should show the user what they are spending their money on.
1. The application should deal with money in pounds sterling.
1. The application should deal with time using the absolute calendar month and year.
1. The application should use the English language for input and output.
1. The application should be built in the Java 8 programming language.

## 2nd Sprint - created 06/09/2018
transaction type field with enum for credit and debit and write tests
rename bank to purse
generate seed data - make lots of transactions with type, description and money

## 3rd Sprint
make seed data have different dates (TEST!!)
create a main Application class that when run will print a list of all the transactions in chronological order (TEST!)

This is entire Budjen class with main method. Cannot change this!
```java
public class Budjen {
    public static void main(String[] args) {
        new Application().start();
    }
}
```