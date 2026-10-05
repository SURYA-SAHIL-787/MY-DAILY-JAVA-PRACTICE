class ProcessNode {
    String processName;
    ProcessNode next;
    ProcessNode(String name) { this.processName = name; }
}

public class RoundRobinScheduler {
    ProcessNode tail;

    public void addProcess(String name) {
        ProcessNode newNode = new ProcessNode(name);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public ProcessNode executeNext(ProcessNode current) {
        if (current == null && tail != null) {
            return tail.next; 
        } else if (current != null) {
            return current.next;
        }
        return null;
    }
}
