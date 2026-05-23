def palindrome(data: str) -> None:
    orginal_data = data
    reverse = data[::-1]
    print(reverse)
    if orginal_data == reverse:
        print("Is palindrome")
    else:
        print("Not palindrome")


palindrome("madam")
