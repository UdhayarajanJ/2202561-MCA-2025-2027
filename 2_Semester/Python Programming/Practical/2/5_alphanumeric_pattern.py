# Program to print various alphanumeric patterns

# Pattern 1: Alphabet Triangle
print("Alphabet Pattern:")
for i in range(65, 70):  # ASCII A=65
    for j in range(65, i + 1):
        print(chr(j), end=" ")
    print()

print()

# Pattern 2: Number Triangle
print("Number Pattern:")
for i in range(1, 6):
    for j in range(1, i + 1):
        print(j, end=" ")
    print()

print()

# Pattern 3: Alphanumeric Pattern
print("Alphanumeric Pattern:")
for i in range(1, 6):
    for j in range(1, i + 1):
        print(j, chr(64 + j), end=" ")
    print()
