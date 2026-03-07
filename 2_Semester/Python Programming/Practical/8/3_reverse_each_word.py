# c. Program to reverse each word in "mcainfo.txt"

with open("records.txt", "r") as f:
    content = f.read()

words = content.split()
reversed_words = []

for w in words:
    reversed_words.append(w[::-1])

result = " ".join(reversed_words)

print("Reversed Words:")
print(result)
