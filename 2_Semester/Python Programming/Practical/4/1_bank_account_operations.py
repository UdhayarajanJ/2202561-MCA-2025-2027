# a. Program for Bank Account operations


class BankAccount:
    def __init__(self, balance):
        self.balance = balance

    def depositMoney(self, amount):
        self.balance += amount
        print("Amount Deposited:", amount)

    def withdrawMoney(self, amount):
        if amount <= self.balance:
            self.balance -= amount
            print("Amount Withdrawn:", amount)
        else:
            print("Insufficient Balance")

    def showBalance(self):
        print("Current Balance:", self.balance)


bal = float(input("Enter initial balance: "))
acc = BankAccount(bal)

d = float(input("Enter amount to deposit: "))
acc.depositMoney(d)

w = float(input("Enter amount to withdraw: "))
acc.withdrawMoney(w)

acc.showBalance()
