

import heapq


class StudentManager:
    def __init__(self):
        # Dictionary: roll_number -> student information
        self.students = {}

        # Set to prevent duplicate roll numbers
        self.roll_numbers = set()

    def add_student(self, roll_number, name, marks):
        if roll_number in self.roll_numbers:
            print(f"Roll number {roll_number} already exists.")
            return

        if not marks:
            print("Marks list cannot be empty.")
            return

        self.students[roll_number] = {
            "name": name.strip().title(),
            "marks": marks
        }

        self.roll_numbers.add(roll_number)
        print(f"{name} added successfully.")

    def calculate_average(self, roll_number):
        student = self.students.get(roll_number)

        if student is None:
            return None

        marks = student["marks"]
        return sum(marks) / len(marks)

    def search_student(self, name):
        name = name.strip().lower()
        results = []

        for roll_number, student in self.students.items():
            if name in student["name"].lower():
                results.append(
                    (
                        roll_number,
                        student["name"],
                        self.calculate_average(roll_number)
                    )
                )

        return results

    def display_top_students(self, count=3):
        heap = []

        for roll_number, student in self.students.items():
            average = self.calculate_average(roll_number)

            # Negative average creates a max heap using heapq
            heapq.heappush(
                heap,
                (-average, roll_number, student["name"])
            )

        print(f"\nTop {min(count, len(heap))} Students")

        for position in range(1, min(count, len(heap)) + 1):
            negative_average, roll_number, name = heapq.heappop(heap)

            print(
                f"{position}. {name} | "
                f"Roll Number: {roll_number} | "
                f"Average: {-negative_average:.2f}"
            )

    def display_all_students(self):
        if not self.students:
            print("No student records available.")
            return

        print("\nAll Student Records")

        for roll_number, student in self.students.items():
            average = self.calculate_average(roll_number)

            print(
                f"Roll Number: {roll_number}, "
                f"Name: {student['name']}, "
                f"Marks: {student['marks']}, "
                f"Average: {average:.2f}"
            )


def main():
    manager = StudentManager()

    # List is used to store marks
    manager.add_student(101, "Aarav", [85, 90, 88])
    manager.add_student(102, "Diya", [92, 95, 91])
    manager.add_student(103, "Kabir", [78, 82, 80])
    manager.add_student(104, "Meera", [89, 87, 93])
    manager.add_student(102, "Duplicate", [100, 100, 100])

    manager.display_all_students()

    print("\nSearch Result")
    search_results = manager.search_student("mee")

    if search_results:
        for roll_number, name, average in search_results:
            print(
                f"{roll_number} - {name} - "
                f"Average: {average:.2f}"
            )
    else:
        print("Student not found.")

    manager.display_top_students(3)


if __name__ == "__main__":
    main()
