# b. Program to find only special characters from the user data

data = input("Enter data: ")

special = ""

for ch in data:
    if not ch.isalnum() and not ch.isspace():
        special += ch

print("Special characters:", special)
