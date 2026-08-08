class Train {
    int trainNumber;
    String trainName;
    String destination;

    Train left, right;
    int height;

    Train(int trainNumber, String trainName,
          String destination) {

        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.destination = destination;
        this.height = 1;
    }
}

public class RailwayTrainAVL {

    Train root;

    int height(Train node) {
        if (node == null)
            return 0;

        return node.height;
    }

    Train rightRotate(Train y) {

        Train x = y.left;
        Train temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = Math.max(
                height(y.left), height(y.right)) + 1;

        x.height = Math.max(
                height(x.left), height(x.right)) + 1;

        return x;
    }

    Train leftRotate(Train x) {

        Train y = x.right;
        Train temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = Math.max(
                height(x.left), height(x.right)) + 1;

        y.height = Math.max(
                height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Train node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    Train insert(Train node, int number,
                 String name, String destination) {

        if (node == null)
            return new Train(number, name, destination);

        if (number < node.trainNumber)
            node.left = insert(
                    node.left, number, name, destination);

        else if (number > node.trainNumber)
            node.right = insert(
                    node.right, number, name, destination);

        else
            return node;

        node.height = 1 + Math.max(
                height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 &&
                number < node.left.trainNumber)
            return rightRotate(node);

        if (balance < -1 &&
                number > node.right.trainNumber)
            return leftRotate(node);

        if (balance > 1 &&
                number > node.left.trainNumber) {

            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 &&
                number < node.right.trainNumber) {

            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Train search(Train node, int number) {

        if (node == null ||
                node.trainNumber == number)
            return node;

        if (number < node.trainNumber)
            return search(node.left, number);

        return search(node.right, number);
    }

    void inorder(Train node) {

        if (node != null) {

            inorder(node.left);

            System.out.println(
                    node.trainNumber + " - " +
                    node.trainName + " - " +
                    node.destination);

            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        RailwayTrainAVL railway =
                new RailwayTrainAVL();

        railway.root = railway.insert(
                railway.root, 12627,
                "Karnataka Express", "New Delhi");

        railway.root = railway.insert(
                railway.root, 12622,
                "Tamil Nadu Express", "Chennai");

        railway.root = railway.insert(
                railway.root, 12723,
                "Telangana Express", "Hyderabad");

        System.out.println("Train List:");
        railway.inorder(railway.root);

        Train result =
                railway.search(railway.root, 12622);

        if (result != null)
            System.out.println(
                    "Train Found: " + result.trainName);
        else
            System.out.println("Train Not Found");
    }
}
