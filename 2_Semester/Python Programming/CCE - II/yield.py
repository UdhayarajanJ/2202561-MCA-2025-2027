# Generator function
def first_five_even():
    for i in range(1, 6):
        yield i * 2


# Using the generator
for num in first_five_even():
    print(num)
