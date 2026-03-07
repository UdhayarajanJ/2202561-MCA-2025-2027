# c. Function to replace comma-separated words with hyphens


def replace_words(text):
    return text.replace(",", "-")


s = input("Enter comma separated words: ")
result = replace_words(s)

print("Result:", result)
