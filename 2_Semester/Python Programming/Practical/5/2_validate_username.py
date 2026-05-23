# b. Program to validate username using regex

import re

username = input("Enter username: ")

pattern = "^[a-zA-Z0-9_]{5,15}$"

if re.match(pattern, username):
    print("Valid Username")
else:
    print("Invalid Username")
