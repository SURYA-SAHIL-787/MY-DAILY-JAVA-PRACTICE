class PlayerNode {
    String playerName;
    PlayerNode next;
    PlayerNode(String name) { this.playerName = name; }
}

public class BoardGameTurns {
    PlayerNode head = null;
    PlayerNode tail = null;

    public void addPlayer(String name) {
        PlayerNode newPlayer = new PlayerNode(name);
        if (head == null) {
            head = newPlayer;
            tail = newPlayer;
            newPlayer.next = head;
        } else {
            tail.next = newPlayer;
            tail = newPlayer;
            tail.next = head;
        }
    }

    public PlayerNode passTurn(PlayerNode currentPlayer) {
        if (currentPlayer != null) {
            return currentPlayer.next;
        }
        return head; 
    }
}
