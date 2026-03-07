# d. Program to count words with length greater than 5 in "myfile.txt"

count = 0

with open("records.txt", "r") as f:
    words = f.read().split()

for w in words:
    if len(w) > 5:
        count += 1

print("Count of words with length > 5:", count)
