# Creating a simple decorator
def start_decorator(func):
    def wrapper():
        print("Function started")
        func()

    return wrapper


# Applying decorator to a function
@start_decorator
def greet():
    print("Hello, World!")


# Calling the function
greet()
