# b. Program to perform searching, adding, updating content in a file

file = "records.txt"

# Add content
name = input("Enter name to add: ")
with open(file, "a") as f:
    f.write(name + "\n")

# Search content
search = input("Enter name to search: ")
found = False
with open(file, "r") as f:
    for line in f:
        if search in line.strip():
            found = True

if found:
    print("Record Found")
else:
    print("Record Not Found")

# Update content
old = input("Enter name to update: ")
new = input("Enter new name: ")

with open(file, "r") as f:
    data = f.read()

data = data.replace(old, new)

with open(file, "w") as f:
    f.write(data)

print("Record Updated")
