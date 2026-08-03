# File Name: library_catalogue_bst.py

from collections import deque


class BookNode:
    def __init__(self, book_id, title, author):
        self.book_id = book_id
        self.title = title
        self.author = author

        self.left = None
        self.right = None


class LibraryCatalogue:
    def __init__(self):
        self.root = None

        # Hash table for direct title searching
        self.title_index = {}

    def insert_book(self, book_id, title, author):
        title = title.strip().title()
        author = author.strip().title()

        new_node = BookNode(book_id, title, author)

        if self.root is None:
            self.root = new_node
            self.title_index[title.lower()] = book_id
            print(f"Book added: {title}")
            return

        current = self.root

        while True:
            if book_id < current.book_id:
                if current.left is None:
                    current.left = new_node
                    break

                current = current.left

            elif book_id > current.book_id:
                if current.right is None:
                    current.right = new_node
                    break

                current = current.right

            else:
                print(f"Book ID {book_id} already exists.")
                return

        self.title_index[title.lower()] = book_id
        print(f"Book added: {title}")

    def search_by_id(self, book_id):
        current = self.root

        while current is not None:
            if book_id == current.book_id:
                return current

            if book_id < current.book_id:
                current = current.left
            else:
                current = current.right

        return None

    def search_by_title(self, title):
        title = title.strip().lower()
        book_id = self.title_index.get(title)

        if book_id is None:
            return None

        return self.search_by_id(book_id)

    def inorder_traversal(self):
        if self.root is None:
            print("Library catalogue is empty.")
            return

        # Stack is used for iterative inorder traversal
        stack = []
        current = self.root

        print("\nBooks in Increasing Book-ID Order")

        while current is not None or stack:
            while current is not None:
                stack.append(current)
                current = current.left

            current = stack.pop()

            print(
                f"ID: {current.book_id}, "
                f"Title: {current.title}, "
                f"Author: {current.author}"
            )

            current = current.right

    def level_order_traversal(self):
        if self.root is None:
            print("Library catalogue is empty.")
            return

        queue = deque([self.root])

        print("\nLevel-Order Traversal")

        while queue:
            current = queue.popleft()

            print(
                f"{current.book_id}: {current.title}"
            )

            if current.left is not None:
                queue.append(current.left)

            if current.right is not None:
                queue.append(current.right)

    def display_book(self, book):
        if book is None:
            print("Book not found.")
            return

        print("\nBook Details")
        print(f"Book ID: {book.book_id}")
        print(f"Title  : {book.title}")
        print(f"Author : {book.author}")


def main():
    library = LibraryCatalogue()

    library.insert_book(
        50,
        "Data Structures",
        "Seymour Lipschutz"
    )

    library.insert_book(
        30,
        "Python Programming",
        "John Zelle"
    )

    library.insert_book(
        70,
        "Artificial Intelligence",
        "Stuart Russell"
    )

    library.insert_book(
        20,
        "Computer Networks",
        "Andrew Tanenbaum"
    )

    library.insert_book(
        40,
        "Database Systems",
        "Ramez Elmasri"
    )

    library.insert_book(
        60,
        "Operating Systems",
        "Abraham Silberschatz"
    )

    library.insert_book(
        80,
        "Machine Learning",
        "Tom Mitchell"
    )

    library.inorder_traversal()
    library.level_order_traversal()

    searched_book = library.search_by_id(60)
    library.display_book(searched_book)

    searched_book = library.search_by_title(
        "Machine Learning"
    )
    library.display_book(searched_book)


if __name__ == "__main__":
    main()
