# a. Program demonstrating File IO operations (text file and binary file)

# Writing to text file
with open("data.txt", "w") as f:
    f.write("Hello Python\n")
    f.write("File IO Example")

# Reading from text file
with open("data.txt", "r") as f:
    print("Text File Content:")
    print(f.read())

# Writing to binary file
data = b"Python Binary Data"
with open("data.bin", "wb") as f:
    f.write(data)

# Reading from binary file
with open("data.bin", "rb") as f:
    print("Binary File Content:", f.read())
