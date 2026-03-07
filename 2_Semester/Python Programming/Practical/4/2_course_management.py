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

c = input("Enter course to add: ")
cm.add_course(c)

d = input("Enter course to delete: ")
cm.delete_course(d)

cm.show_courses()
