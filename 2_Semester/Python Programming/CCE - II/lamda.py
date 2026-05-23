# Lambda function to find square of a number
square = lambda x: x * x

# Taking input from user
num = int(input("Enter a number: "))

# Calling lambda function
result = square(num)

# Display result
print("Square of", num, "is:", result)
