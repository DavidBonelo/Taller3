package logica;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MusicLibrary {
    private final ArrayList<Song> songs = new ArrayList<>(1);
    private final ArrayList<Playlist> playlists = new ArrayList<>(1);

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<Song> filterSongs(FilterType filterType, String filter) {
        switch (filterType) {
            case GENDER -> {
                return new ArrayList<>(getSongs().stream().filter(s -> s.getGender().equals(filter)).collect(Collectors.toList()));
            }
            case YEAR -> {
                return new ArrayList<>(getSongs().stream().filter(s -> s.date.getYear() == Integer.parseInt(filter)).collect(Collectors.toList()));
            }
            default -> {
                return getSongs();
            }
        }
    }

    public ArrayList<Song> sortSongs(SortType sortType, boolean ascending) {
        switch (sortType) {
            case DATE -> {
                getSongs().sort((s1, s2) -> {
                    if (s1.date == s2.date) return 0;
                    return (s1.date.isBefore(s2.date) ? -1 : 1);
                });
                return getSongs();
            }
            case DURATION -> {
                getSongs().sort((s1, s2) -> {
                    if (s1.duration == s2.duration) return 0;
                    if (ascending) {
                        return (s1.duration.isBefore(s2.duration) ? -1 : 1);
                    } else {
                        return (s1.duration.isBefore(s2.duration) ? 1 : -1);
                    }
                });
                return getSongs();
            }
            default -> {
                return getSongs();
            }
        }
    }
}
