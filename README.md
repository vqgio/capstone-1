# Capstone-1: Accounting Ledger CLI 🏦
This is a command-line interface (CLI) application used to manage and track financial transactions for business or personal use. The application reads and saves all transactions to a file named transactions.csv . Each transaction is recorded as a single line in the CSV file.
## 💡 Features!
- Add deposits and make payments
- Ability to display all transactions
- display only deposits or payments
- allow to filter reports by month or year
- filter to find transactions by vendor

## ⚙️ How it works!
All transactions are saved and stored in a file named transactions.csv in a format that is easy to read and nicely organized
- Date | Time | Description | Vendor | Amount

## Different menus in Accounting Ledger: 
🏠 Home Screen Menu:
- Gives the user 4 clear options to start using the application, when selecting make deposit/payment, the appliccation will prompt you to enter description, vendor name, and amount.

![image](https://github.com/user-attachments/assets/d0a61d31-9cb8-41e4-940a-51edd9b63ef4)

📒 Ledger Menu:

The Ledger Menu gives the user 5 clear options 
- You can select "All" and it will give you all transactions saved in the transactions.csv file, no matter if they are Deposits or Payments.
- The options below that show transactions filtered based on payment or deposit category
- Reports takes you to a different menu
- Home takes you back to the home screen

![image](https://github.com/user-attachments/assets/c9102017-5bea-4b77-a261-1746b9fa2d0c)

📃 Reports Menu:
- In the reports menu the user can easily sort throught all transactions saved on the transactions.csv file based on time or filter with vendor name
- You also have the ability to return back to the home screen in this menu

![image](https://github.com/user-attachments/assets/d8b779f7-d65c-4304-b871-1c59c004a291)

🤯 Interesting code!

By creating a string named "vendorSearch" and combining it with a scanner class you can easily search by vendor.
This code works by using the scanner class to temporarily save the user input and plug it into an "if" statement focusing on if parts[3] contains the user input saved by "vendorSeach" 
parts[3] is the vendor category in the application

![image](https://github.com/user-attachments/assets/5b07ea2f-3f57-4f64-a397-0915791f0839)



