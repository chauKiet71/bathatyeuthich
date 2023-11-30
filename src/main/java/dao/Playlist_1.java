package dao;

import entity.BaiHatEntity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

//public class Playlist {
//
//    private List<BaiHatEntity> songs;
//    private int currentIndex;
//
//    public Playlist() {
//        songs = new ArrayList<>();
//        currentIndex = -1;
//    }
//
//    public void  addSong(BaiHatEntity song) {
//        songs.add(song);
//    }
//
//    public BaiHatEntity getCurrentSong() {
//        if (currentIndex >= 0 && currentIndex < songs.size()) {
//            return songs.get(currentIndex);
//        }
//        return null;
//    }
//
//    public void selectSong(int index) {
//        if (index >= 0 && index < songs.size()) {
//            currentIndex = index;
//        }
//    }
//
//    public void playNext() {
//        currentIndex++;
//        if (currentIndex >= songs.size()) {
//            currentIndex = 0;
//        }
//    }
//
//    public void playPrevious() {
//        currentIndex--;
//        if (currentIndex < 0) {
//            currentIndex = songs.size() - 1;
//        }
//    }
//
//    public boolean isEmpty() {
//        return songs.isEmpty();
//    }
//
//    public int getTotalSongs() {
//        return songs.size();
//    }
//}
import java.util.ArrayList;
import java.util.List;

public class Playlist_1 {

    private List<BaiHatEntity> songs;
    private int currentSongIndex;

    public Playlist_1() {
        songs = new ArrayList<>();
        currentSongIndex = -1;
    }

    public void addSong(BaiHatEntity song) {
        songs.add(song);
        if (currentSongIndex == -1) {
            currentSongIndex = 0;
        }
    }

    public BaiHatEntity getCurrentSong() {
        if (currentSongIndex >= 0 && currentSongIndex < songs.size()) {
            return songs.get(currentSongIndex);
        }
        return null;
    }

    public void playNext() {
        currentSongIndex++;
        if (currentSongIndex >= songs.size()) {
            currentSongIndex = 0;
        }
    }

    public void playPrevious() {
        currentSongIndex--;
        if (currentSongIndex < 0) {
            currentSongIndex = songs.size() - 1;
        }
    }

    public void printSongs() {
        for (BaiHatEntity song : songs) {
            System.out.println(song); // In thông tin của bài hát
        }
    }
}
