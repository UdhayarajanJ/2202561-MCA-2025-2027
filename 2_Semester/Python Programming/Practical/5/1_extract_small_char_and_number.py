# a. Program to parse the input string for small letters and numbers

import re

text = input("Enter a string: ")

letters = re.findall("[a-z]", text)
numbers = re.findall("[0-9]", text)

print("Small letters:", str(letters))
print("Numbers:", numbers)
