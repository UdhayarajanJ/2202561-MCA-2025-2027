# a) Lambda function to find cube of a number
cube = lambda x: x**3

num = int(input("Enter a number: "))
print("Cube of", num, "is:", cube(num))


# b) Decorator that prints "Executing Function"
def my_decorator(func):
    def wrapper(*args, **kwargs):
        print("Executing Function")
        return func(*args, **kwargs)

    return wrapper


@my_decorator
def greet():
    print("Hello from decorated function!")


greet()


# c) Generator to generate first 5 odd numbers
def first_five_odd():
    count = 0
    num = 1
    while count < 5:
        yield num
        num += 2
        count += 1


print("First 5 odd numbers:")
for i in first_five_odd():
    print(i)
