def fibo(num):
    a = 0
    b = 1
    for i in range(num):
        print(a, end=" ")
        c = a + b
        a = b
        b = c


fibo(1)

name = "John"
age = 25

print("Name: %s Age: %d" % (name, age))
