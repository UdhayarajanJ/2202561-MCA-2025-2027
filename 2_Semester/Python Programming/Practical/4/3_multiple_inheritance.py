# Python Program to Demonstrate Multiple Inheritance with Method Overriding
# Calling parent class methods without passing parameters


class Vehicle:
    def show_vehicle(self):
        print("This is a Vehicle")


class Engine:
    def show_engine(self):
        print("This vehicle has an Engine")


class Car(Vehicle, Engine):  # Multiple Inheritance
    def show(self):  # Method Overriding
        print("This is a Car")
        Vehicle.show_vehicle(self)  # calling Vehicle method
        Engine.show_engine(self)  # calling Engine method


c = Car()
c.show()
