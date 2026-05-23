# b. Program based on Multithreading

import threading


def task1():
    for i in range(5):
        print("Task 1:", i)


def task2():
    for i in range(5):
        print("Task 2:", i)


t1 = threading.Thread(target=task1)
t2 = threading.Thread(target=task2)

t1.start()
t2.start()

t1.join()
t2.join()
