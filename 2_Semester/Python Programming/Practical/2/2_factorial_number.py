# Program to print the factorial of a given number


def recursion_factorial_number(n):
    for i in range(1, n):
        n *= i
    return n


num = int(input("Enter a number: "))

print("Factorial =", recursion_factorial_number(num))
