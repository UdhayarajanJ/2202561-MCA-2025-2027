def check_number_is_odd_or_even():
    number = int(input("Enter a number: "))

    if number % 2 == 0:
        print(f"{number} is an even number.")
    else:
        print(f"{number} is an odd number.")


check_number_is_odd_or_even()
