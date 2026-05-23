import tkinter as tk
from tkinter import messagebox
from pymongo import MongoClient

# MongoDB Connection
client = MongoClient("mongodb://localhost:27017/")
db = client["college"]
collection = db["students"]

# Functions


def add_student():
    student = {
        "roll": roll_entry.get(),
        "name": name_entry.get(),
        "marks": marks_entry.get(),
    }
    collection.insert_one(student)
    messagebox.showinfo("Success", "Student Added")


def view_students():
    text.delete(1.0, tk.END)
    for s in collection.find():
        text.insert(tk.END, f"{s['roll']} - {s['name']} - {s['marks']}\n")


def update_student():
    collection.update_one(
        {"roll": roll_entry.get()},
        {"$set": {"name": name_entry.get(), "marks": marks_entry.get()}},
    )
    messagebox.showinfo("Success", "Student Updated")


def delete_student():
    collection.delete_one({"roll": roll_entry.get()})
    messagebox.showinfo("Success", "Student Deleted")


# Extra Function 1: Search
def search_student():
    text.delete(1.0, tk.END)
    for s in collection.find({"name": name_entry.get()}):
        text.insert(tk.END, f"{s['roll']} - {s['name']} - {s['marks']}\n")


# Extra Function 2: Count
def count_students():
    count = collection.count_documents({})
    messagebox.showinfo("Total Students", f"{count}")


# UI Design
root = tk.Tk()
root.title("Student Management System")

tk.Label(root, text="Roll No").grid(row=0, column=0)
roll_entry = tk.Entry(root)
roll_entry.grid(row=0, column=1)

tk.Label(root, text="Name").grid(row=1, column=0)
name_entry = tk.Entry(root)
name_entry.grid(row=1, column=1)

tk.Label(root, text="Marks").grid(row=2, column=0)
marks_entry = tk.Entry(root)
marks_entry.grid(row=2, column=1)

tk.Button(root, text="Add", command=add_student).grid(row=3, column=0)
tk.Button(root, text="View", command=view_students).grid(row=3, column=1)
tk.Button(root, text="Update", command=update_student).grid(row=4, column=0)
tk.Button(root, text="Delete", command=delete_student).grid(row=4, column=1)

tk.Button(root, text="Search by Name", command=search_student).grid(row=5, column=0)
tk.Button(root, text="Count Students", command=count_students).grid(row=5, column=1)

text = tk.Text(root, height=10, width=40)
text.grid(row=6, column=0, columnspan=2)

root.mainloop()
