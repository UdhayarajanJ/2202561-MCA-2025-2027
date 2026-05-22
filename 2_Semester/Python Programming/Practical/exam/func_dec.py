def dec_func_code(func):
    def wrapper(*args, **kwargs):
        print("Function is being called")
        result = func(*args, **kwargs)
        print("Function has been called")
        return result

    return wrapper


@dec_func_code
def sample_function():
    print("This is a sample function.")


def outer_func():
    x = 10

    def inner_func():
        nonlocal x
        x = 20
        print(x)

    inner_func()
    print(x)


outer_func()
sample_function()
