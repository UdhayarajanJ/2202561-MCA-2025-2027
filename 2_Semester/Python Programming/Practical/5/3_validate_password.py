# c. Program to validate password using regex

import re

password = input("Enter password: ")

pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=]{6,}$"

if re.match(pattern, password):
    print("Valid Password")
else:
    print("Invalid Password")
