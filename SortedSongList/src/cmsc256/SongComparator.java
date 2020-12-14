/** SongComparator
 * Tara Ram Mohan
 * Project 3
 * Purpose: Comparator method to sort song data into alphabetical order grouped by album name based on inputted artist name
 * CMSC 256 Spring 2020
*/

package cmsc256;

import bridges.data_src_dependent.Song;
import java.util.Comparator;

public class SongComparator implements Comparator<Song> {

    @Override

    /**
     * Compares the album names of two Song objects and if they are the same,
     * compares the song titles and returns a value that alphabetizes and sorts the objects.
     *
     * @param o1 first Song object
     * @param o2 second Song object
     */
    public int compare(Song o1, Song o2) {
        try {
            String o1Title = o1.getAlbumTitle();
            String o2Title = o2.getAlbumTitle();
            if ((o1Title != null) && (o2Title != null)) {
                if (o1.getAlbumTitle().compareTo(o2.getAlbumTitle()) == 0) {
                    return (o1.getSongTitle().compareTo(o2.getSongTitle()));
                }
                return o1.getAlbumTitle().compareTo(o2.getAlbumTitle());
            } else if (o1Title == o2Title) {
                return 0;
            } else if (o2Title == null) {
                return 1;
            } else if (o1Title == null) {
                return -1;
            }
            return 1;
        } catch (Exception ex) {
            System.out.print("Error in sorting dataset");
        } return 1;
    }
}
