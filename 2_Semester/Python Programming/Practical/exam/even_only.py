import tkinter as tk

# Create main window
window = tk.Tk()
window.title("User Info Form")
window.geometry("350x250")


# Function to display message
def show_info():
    name = entry_name.get()
    age = entry_age.get()
    label_result.config(text=f"Name: {name}, Age: {age}")


# Name label and entry
tk.Label(window, text="Enter Name:").pack()
entry_name = tk.Entry(window)
entry_name.pack()

# Age label and entry
tk.Label(window, text="Enter Age:").pack()
entry_age = tk.Entry(window)
entry_age.pack()

# Button
tk.Button(window, text="Submit", command=show_info).pack(pady=10)

# Result label
label_result = tk.Label(window, text="")
label_result.pack(pady=10)

# Run GUI
window.mainloop()
