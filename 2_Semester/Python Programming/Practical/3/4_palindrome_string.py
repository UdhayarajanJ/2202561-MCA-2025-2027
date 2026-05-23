# d. Function to check whether a string is palindrome or not


def check_palindrome(text):
    if text == text[::-1]:
        return "Palindrome"
    else:
        return "Not Palindrome"


s = input("Enter a string: ")
print(check_palindrome(s))
