# Defining outer function
def outer():
    x = 10
    print("Before modification:", x)

    # Nested function
    def inner():
        nonlocal x
        x = 20  # Modifying outer variable

    inner()  # Calling inner function
    print("After modification:", x)


# Calling outer function
outer()
