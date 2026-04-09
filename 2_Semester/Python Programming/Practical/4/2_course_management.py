# b. Program for Course Management


class CourseManager:
    def __init__(self):
        self.courses = []

    def add_course(self, course):
        self.courses.append(course)
        print(course, "added")

    def delete_course(self, course):
        if course in self.courses:
            self.courses.remove(course)
            print(course, "deleted")
        else:
            print("Course not found")

    def show_courses(self):
        print("Available Courses:", self.courses)


cm = CourseManager()
continueAgain = True

while continueAgain:
    print(
        """
1. Add Course
2. Delete Cource
3. Show Courses
    """
    )
    choice = int(input("Enter your choice :"))
    match choice:
        case 1:
            c = input("Enter course to add: ")
            cm.add_course(c)
        case 2:
            d = input("Enter course to delete: ")
            cm.delete_course(d)
        case 3:
            cm.show_courses()
        case _:
            print("Invalid choice")

    doAgain = int(input("To you want to continue press 1 : "))
    continueAgain = doAgain == 1
