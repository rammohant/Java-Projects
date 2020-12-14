/** SongList
 * Tara Ram Mohan
 * Project 3
 * Purpose: Read inputted artist name, access database, and print out sorted list of all songs by that artist
 * CMSC 256 Spring 2020
*/

package cmsc256;

import java.util.*;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;

public class SongList {

    /**
     * Default constructor for SongList class
     */
    public SongList() {
        List songData = null;
    }

    /**
     * Read song data from the BRIDGES song dataset and return a formatted list
     * of all the songs by that artist that appear on the
     * playlist, grouped by album and in alphabetical order by song title.
     * @param  artist String to determine which artist to sort dataset by
     * @return String containing all songs by specified artist organized in alphabetiacal order
     * and by album name in the specified format
     */
    public static String getSongsByArtist(String artist) {
        Bridges bridges = new Bridges(5, "rammohant", "247983952629");
        DataSource ds = bridges.getDataSource();
        List songData = null;

        try {
            songData = ds.getSongData();
        } catch (Exception var6) {
            System.out.println("Unable to connect to Bridges.");
        }

        Song entry;
        String song;
        String album;
        String print = "";

        Collections.sort(songData, (new SongComparator()));

        for(int i = 0; i < songData.size(); i++) {
            entry = (Song)songData.get(i);
            if (entry.getArtist().equals(artist)) {
                song = entry.getSongTitle();
                artist = entry.getArtist();
                album = entry.getAlbumTitle();
                print += "Title: " + song + " Artist: " + artist + " Album: " + album + "\n";
            }
        } if (print.equals("")) {
            return "There are no songs by this artist in this database.";
        } return print;
    }

    /**
     * Read an artist name as a command line argument. If a command line argument is not provided,
     * prompt the user to enter an artist name. Print all songs in correct format by calling getSongsByArtist method.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String artistInput;
        Song entry;

        if (args.length == 0) {
            System.out.println("Enter an artist name");
            artistInput = input.next();
        } else {
            artistInput = args[0];
        } System.out.println(getSongsByArtist(artistInput));
    }
}

