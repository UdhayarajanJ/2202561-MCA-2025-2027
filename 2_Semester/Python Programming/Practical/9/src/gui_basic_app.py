from tkinter import *
from pymongo import MongoClient

# MongoDB connection
client = MongoClient("mongodb://localhost:27017/")
db = client["mca_db"]
collection = db["students"]


# Insert data
def add_record():
    name = name_entry.get()
    age = age_entry.get()
    collection.insert_one({"name": name, "age": age})
    result_label.config(text="Record Added")


# Search data
def search_record():
    name = name_entry.get()
    record = collection.find_one({"name": name})

    if record:
        result_label.config(text="Found: " + record["name"] + " " + str(record["age"]))
    else:
        result_label.config(text="Record Not Found")


# GUI Window
root = Tk()
root.title("Student Management System")

Label(root, text="Name").grid(row=0, column=0)
name_entry = Entry(root)
name_entry.grid(row=0, column=1)

Label(root, text="Age").grid(row=1, column=0)
age_entry = Entry(root)
age_entry.grid(row=1, column=1)

Button(root, text="Add", command=add_record).grid(row=2, column=0)
Button(root, text="Search", command=search_record).grid(row=2, column=1)

result_label = Label(root, text="")
result_label.grid(row=3, column=0, columnspan=2)

root.mainloop()
