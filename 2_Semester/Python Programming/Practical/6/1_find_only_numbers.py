# a. Program to find only numbers from the user data

data = input("Enter data: ")

numbers = ""

for ch in data:
    if ch.isdigit():
        numbers += ch

print("Numbers:", numbers)
