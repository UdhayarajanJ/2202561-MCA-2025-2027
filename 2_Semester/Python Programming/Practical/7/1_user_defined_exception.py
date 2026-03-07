# a. Program based on Exception Handling with User Defined Exception


class NegativeNumberError(Exception):
    pass


try:
    num = int(input("Enter a positive number: "))

    if num < 0:
        raise NegativeNumberError("Negative numbers are not allowed")

    print("You entered:", num)

except NegativeNumberError as e:
    print("Error:", e)

except ValueError:
    print("Invalid input. Please enter a number.")
