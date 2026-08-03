# File Name: social_network_connection_finder.py

from collections import deque


class SocialNetwork:
    def __init__(self):
        # Dictionary containing adjacency lists
        self.graph = {}

    def add_user(self, user):
        user = user.strip().title()

        if user not in self.graph:
            self.graph[user] = []
            print(f"User added: {user}")

    def add_friendship(self, first_user, second_user):
        first_user = first_user.strip().title()
        second_user = second_user.strip().title()

        self.add_user(first_user)
        self.add_user(second_user)

        if second_user not in self.graph[first_user]:
            self.graph[first_user].append(second_user)
            self.graph[second_user].append(first_user)

            print(
                f"Friendship created between "
                f"{first_user} and {second_user}."
            )
        else:
            print("Friendship already exists.")

    def shortest_connection(self, start_user, target_user):
        start_user = start_user.strip().title()
        target_user = target_user.strip().title()

        if (
            start_user not in self.graph
            or target_user not in self.graph
        ):
            return None

        # Queue stores complete paths
        queue = deque([[start_user]])
        visited = {start_user}

        while queue:
            current_path = queue.popleft()
            current_user = current_path[-1]

            if current_user == target_user:
                return current_path

            for friend in self.graph[current_user]:
                if friend not in visited:
                    visited.add(friend)
                    new_path = current_path + [friend]
                    queue.append(new_path)

        return None

    def depth_first_traversal(self, start_user):
        start_user = start_user.strip().title()

        if start_user not in self.graph:
            return []

        stack = [start_user]
        visited = set()
        traversal = []

        while stack:
            current_user = stack.pop()

            if current_user in visited:
                continue

            visited.add(current_user)
            traversal.append(current_user)

            # Reverse maintains readable traversal order
            for friend in reversed(self.graph[current_user]):
                if friend not in visited:
                    stack.append(friend)

        return traversal

    def mutual_friends(self, first_user, second_user):
        first_user = first_user.strip().title()
        second_user = second_user.strip().title()

        if (
            first_user not in self.graph
            or second_user not in self.graph
        ):
            return set()

        first_friends = set(self.graph[first_user])
        second_friends = set(self.graph[second_user])

        return first_friends.intersection(second_friends)

    def display_network(self):
        print("\nSocial Network")

        for user, friends in self.graph.items():
            friend_text = ", ".join(friends)

            if not friend_text:
                friend_text = "No connections"

            print(f"{user}: {friend_text}")


def main():
    network = SocialNetwork()

    network.add_friendship("Aarav", "Diya")
    network.add_friendship("Aarav", "Kabir")
    network.add_friendship("Diya", "Meera")
    network.add_friendship("Kabir", "Rohan")
    network.add_friendship("Meera", "Ishita")
    network.add_friendship("Rohan", "Ishita")
    network.add_friendship("Ishita", "Nikhil")

    network.display_network()

    path = network.shortest_connection(
        "Aarav",
        "Nikhil"
    )

    print("\nShortest Connection")

    if path:
        print(" -> ".join(path))
        print(f"Number of connections: {len(path) - 1}")
    else:
        print("No connection found.")

    traversal = network.depth_first_traversal("Aarav")

    print("\nDFS Traversal")
    print(" -> ".join(traversal))

    mutual = network.mutual_friends("Meera", "Rohan")

    print("\nMutual Friends")

    if mutual:
        print(", ".join(sorted(mutual)))
    else:
        print("No mutual friends.")


if __name__ == "__main__":
    main()
