# File Name: browser_navigation_system.py

from collections import deque


class WebPageNode:
    def __init__(self, url):
        self.url = url
        self.previous = None
        self.next = None


class Browser:
    def __init__(self):
        self.current_page = None

        # Stacks for browser history
        self.back_stack = []
        self.forward_stack = []

        # Queue for download requests
        self.download_queue = deque()

        # Linked-list references
        self.history_head = None
        self.history_tail = None

    def _add_to_linked_history(self, url):
        new_node = WebPageNode(url)

        if self.history_head is None:
            self.history_head = new_node
            self.history_tail = new_node
        else:
            new_node.previous = self.history_tail
            self.history_tail.next = new_node
            self.history_tail = new_node

    def visit_page(self, url):
        url = url.strip()

        if not url:
            print("URL cannot be empty.")
            return

        if self.current_page is not None:
            self.back_stack.append(self.current_page)

        self.current_page = url

        # Forward history is deleted after visiting a new page
        self.forward_stack.clear()

        self._add_to_linked_history(url)

        print(f"Opened: {url}")

    def go_back(self):
        if not self.back_stack:
            print("No previous page available.")
            return

        self.forward_stack.append(self.current_page)
        self.current_page = self.back_stack.pop()

        print(f"Back to: {self.current_page}")

    def go_forward(self):
        if not self.forward_stack:
            print("No forward page available.")
            return

        self.back_stack.append(self.current_page)
        self.current_page = self.forward_stack.pop()

        print(f"Forward to: {self.current_page}")

    def add_download(self, file_name):
        file_name = file_name.strip()

        if not file_name:
            print("File name cannot be empty.")
            return

        self.download_queue.append(file_name)
        print(f"Added to download queue: {file_name}")

    def process_download(self):
        if not self.download_queue:
            print("Download queue is empty.")
            return

        file_name = self.download_queue.popleft()
        print(f"Downloading: {file_name}")

    def display_download_queue(self):
        if not self.download_queue:
            print("Download queue is empty.")
            return

        print("\nPending Downloads")

        for position, file_name in enumerate(
            self.download_queue,
            start=1
        ):
            print(f"{position}. {file_name}")

    def display_complete_history(self):
        if self.history_head is None:
            print("Browsing history is empty.")
            return

        print("\nComplete Browsing History")

        current = self.history_head
        position = 1

        while current is not None:
            print(f"{position}. {current.url}")
            current = current.next
            position += 1

    def show_current_page(self):
        if self.current_page is None:
            print("No page is currently open.")
        else:
            print(f"Current page: {self.current_page}")


def main():
    browser = Browser()

    browser.visit_page("www.google.com")
    browser.visit_page("www.github.com")
    browser.visit_page("www.python.org")
    browser.visit_page("www.openai.com")

    print()
    browser.go_back()
    browser.go_back()
    browser.go_forward()
    browser.show_current_page()

    browser.add_download("python_notes.pdf")
    browser.add_download("data_structures.zip")
    browser.add_download("assignment.docx")

    browser.display_download_queue()

    print()
    browser.process_download()
    browser.process_download()

    browser.display_download_queue()
    browser.display_complete_history()


if __name__ == "__main__":
    main()
