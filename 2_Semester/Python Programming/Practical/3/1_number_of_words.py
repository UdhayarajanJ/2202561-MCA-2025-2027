# a. Function to count number of words in a string


def count_words(text):
    words = text.split()
    return len(words)


s = input("Enter a string: ")
result = count_words(s)
print("Number of words:", result)
