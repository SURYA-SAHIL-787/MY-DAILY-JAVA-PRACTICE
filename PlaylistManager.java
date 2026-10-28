class Song {
    String title;
    String artist;
    Song prev;
    Song next;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
}

class MusicPlaylist {
    private Song head = null;
    private Song tail = null;
    private Song current = null;

    public void addSong(String title, String artist) {
        Song newSong = new Song(title, artist);
        if (head == null) {
            head = tail = current = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
    }

    public void nextSong() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Playing now: " + current.title);
        }
    }

    public void prevSong() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Playing now: " + current.title);
        }
    }

    public static void main(String[] args) {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("Kesariya", "Arijit Singh");
        playlist.addSong("Apna Bana Le", "Arijit Singh");
        playlist.addSong("Tum Se Hi", "Pritam");

        playlist.nextSong();
        playlist.nextSong();
        playlist.prevSong();
    }
}
