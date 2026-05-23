# b. Program to calculate area and perimeter of a circle using functions

import math


def area(radius):
    return math.pi * radius * radius


def perimeter(radius):
    return 2 * math.pi * radius


r = float(input("Enter radius of circle: "))

print("Area of circle:", area(r))
print("Perimeter of circle:", perimeter(r))
