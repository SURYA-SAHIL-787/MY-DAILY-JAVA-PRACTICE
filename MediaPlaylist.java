class SongNode {
    String songTitle;
    SongNode next;
    SongNode(String title) { this.songTitle = title; }
}

public class MediaPlaylist {
    SongNode head = null;
    SongNode tail = null;
    SongNode currentSong = null;

    public void addSong(String title) {
        SongNode newSong = new SongNode(title);
        if (head == null) {
            head = newSong;
            tail = newSong;
            newSong.next = head;
            currentSong = head;
        } else {
            tail.next = newSong;
            tail = newSong;
            tail.next = head;
        }
    }

    public void playNext() {
        if (currentSong != null) {
            currentSong = currentSong.next;
        }
    }
}
