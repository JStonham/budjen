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
1. The application should deal with time using the absolute calendar month.
1. The application should use the English language for input and output.
1. The application should be built in the Java 8 programming language.
1. The user will be the person who invokes the application.
1. A purse is a representation of a number of money in pounds sterling.
1. How much money is made per month will be represented in terms of a credit to the purse.
1. How much money is spent per month will be represented in terms of an expenditure from the purse.
1. How much money is saved per month will be respresented in terms of the difference between the credit and the expenditure.
1. How much money is saved per year will be a cumulative count of the difference between the credit and the expenditure. 
1. The user will interact with the application and enter particular details for each transaction.
1. Each transaction will include a transaction type, a transation reason and a number of money in pounds sterling.
1. The transaction type will be in the form of a credit or an expenditure.
1. The transaction reason will be formed of the following list of options for a credit: Work, money owed.
1. The transaction reason will be formed of the following list of options for an expenditure: Gifts, rent, phone bill, electricity bill, water bill, council tax, money lent, clothes, toiletries, groceries, food & drink, commuting travel, holiday travel, gym.
1. All money entered by the user into the purse will be in positive or non-negative values.
1. The application will display credit and expenditure in positive or non-negative values.
