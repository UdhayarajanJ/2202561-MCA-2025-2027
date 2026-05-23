# a) Importing user-defined module
import mymodule

# b) Importing regular expression module
import re

# Calling function from user-defined module
mymodule.display_message()

# Password Validation Rules:
# At least 8 characters
# At least one uppercase letter
# At least one lowercase letter
# At least one digit
# At least one special character (@, #, $, !)

password = input("Enter password: ")
password_pattern = r"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$!])[A-Za-z\d@#$!]{8,}$"

if re.match(password_pattern, password):
    print("Valid Password")
else:
    print("Invalid Password")

# Mobile Number Validation Rules:
# Must be exactly 10 digits
# Must start with 6, 7, 8, or 9

mobile = input("Enter mobile number: ")
mobile_pattern = r"^[6-9]\d{9}$"

if re.match(mobile_pattern, mobile):
    print("Valid Mobile Number")
else:
    print("Invalid Mobile Number")
