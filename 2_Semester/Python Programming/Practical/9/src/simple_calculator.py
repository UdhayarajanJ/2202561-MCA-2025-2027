import tkinter as tk


# Define class
class CalculatorGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Simple Calculator")

        # Input fields
        self.num1_label = tk.Label(root, text="Number 1")
        self.num1_label.grid(row=0, column=0)
        self.num1_entry = tk.Entry(root)
        self.num1_entry.grid(row=0, column=1)

        self.num2_label = tk.Label(root, text="Number 2")
        self.num2_label.grid(row=1, column=0)
        self.num2_entry = tk.Entry(root)
        self.num2_entry.grid(row=1, column=1)

        # Result label
        self.result_label = tk.Label(root, text="Result: ")
        self.result_label.grid(row=2, column=0, columnspan=2)

        # Buttons
        tk.Button(root, text="Add", command=self.add).grid(row=3, column=0)
        tk.Button(root, text="Subtract", command=self.subtract).grid(row=3, column=1)
        tk.Button(root, text="Multiply", command=self.multiply).grid(row=4, column=0)
        tk.Button(root, text="Divide", command=self.divide).grid(row=4, column=1)

    # Methods for operations
    def get_values(self):
        try:
            a = float(self.num1_entry.get())
            b = float(self.num2_entry.get())
            return a, b
        except:
            self.result_label.config(text="Invalid Input")
            return None, None

    def add(self):
        a, b = self.get_values()
        if a is not None:
            self.result_label.config(text="Result: " + str(a + b))

    def subtract(self):
        a, b = self.get_values()
        if a is not None:
            self.result_label.config(text="Result: " + str(a - b))

    def multiply(self):
        a, b = self.get_values()
        if a is not None:
            self.result_label.config(text="Result: " + str(a * b))

    def divide(self):
        a, b = self.get_values()
        if a is not None:
            if b != 0:
                self.result_label.config(text="Result: " + str(a / b))
            else:
                self.result_label.config(text="Cannot divide by zero")


# Run application
root = tk.Tk()
app = CalculatorGUI(root)
root.mainloop()
