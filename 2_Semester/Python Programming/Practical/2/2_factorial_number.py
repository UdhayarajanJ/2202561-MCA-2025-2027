# Program to print the factorial of a given number


def recursion_factorial_number(n):
    if n == 0 or n == 1:
        return n
    return n * recursion_factorial_number(n - 1)


num = int(input("Enter a number: "))

print("Factorial =", recursion_factorial_number(num))
