# Program to convert last character of each word to uppercase

text = input("Enter a string: ")

words = text.split()
result = []

for w in words:
    if len(w) > 1:
        new_word = w[:-1] + w[-1].upper()
    else:
        new_word = w.upper()
    result.append(new_word)

output = " ".join(result)
print("Result:", output)
