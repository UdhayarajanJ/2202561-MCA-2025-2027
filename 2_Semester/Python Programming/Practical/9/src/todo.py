import tkinter as tk


# Define class
class TodoApp:
    def __init__(self, root):
        self.root = root
        self.root.title("To-Do App")

        # List to store tasks
        self.tasks = []

        # Entry for task input
        self.task_entry = tk.Entry(root, width=30)
        self.task_entry.grid(row=0, column=0, columnspan=2)

        # Buttons
        tk.Button(root, text="Add Task", command=self.add_task).grid(row=1, column=0)
        tk.Button(root, text="Remove Task", command=self.remove_task).grid(
            row=1, column=1
        )

        # Listbox to display tasks
        self.task_listbox = tk.Listbox(root, width=40)
        self.task_listbox.grid(row=2, column=0, columnspan=2)

    # Method to add task
    def add_task(self):
        task = self.task_entry.get()
        if task:
            self.tasks.append(task)
            self.update_listbox()
            self.task_entry.delete(0, tk.END)

    # Method to remove selected task
    def remove_task(self):
        try:
            index = self.task_listbox.curselection()[0]
            self.tasks.pop(index)
            self.update_listbox()
        except:
            pass

    # Method to display tasks
    def update_listbox(self):
        self.task_listbox.delete(0, tk.END)
        for task in self.tasks:
            self.task_listbox.insert(tk.END, task)


# Run application
root = tk.Tk()
app = TodoApp(root)
root.mainloop()
