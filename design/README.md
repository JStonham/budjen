The budjen appliction needs to print a list of transactions in chronological order.

Each class in the budjen application should one have one purpose (shown as =) and possess certain attributes (shown as bullet points).

Budjen class = run the application.
- No fields.
- One method called main, which will call the start method from the Application class.

Application class = print a list of transactions in chronological order.
- Four dependency fields called transactionData, orderer, transactionFormatter and logger.
- One method called start that prints a list of transactions in chronological order.

TransactionData class = provide the transaction data for the Application class.
- One public method called getData.
- Three private methods called makeTransaction, credit and debit.

Orderer class = order a given array by its dates in chronological order.
- One public method called orderTheTransactions that will order a given array by its dates in chronological order.

TransactionFormatter class = define the format of the transaction.
- One dependency field called currency.
- One public method called format to provide the correct format: Transaction Type (credit/debit), Description as a String, Money in pounds, Date as a String. e.g. DEBIT, Blue Audi, £40000.00, 2018-04-03
- Two private methods called formatPounds and formatDate.

Logger class = provide a method for printing type, description, money and date of transactions.
- One public method called print to print a given String.