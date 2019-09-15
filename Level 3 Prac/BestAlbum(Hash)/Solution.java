import java.util.*;

class Album implements Comparable<Album>{
    int idx;
    String genre;
    int play;
    int genrePlay;

    public Album(int idx, String genre, int play) {
        this.idx = idx;
        this.genre = genre;
        this.play = play;
    }

    public void setGenrePlay(int genrePlay) {
        this.genrePlay = genrePlay;
    }

    @Override
    public int compareTo(Album album) {
        if (this.play > album.play)
            return -1;
        else if (this.play == album.play)
            return this.idx - album.idx;
        else
            return 1;
    }
}

class Genre implements Comparable<Genre> {
    String genre;
    int play;

    public Genre(String genre, int play) {
        this.genre = genre;
        this.play = play;
    }

    @Override
    public int compareTo(Genre genre) {
        return genre.play - this.play;
    }
}

class Solution {
    public static void main(String[] args) {
        int[] answer = solution(new String[]{"classic", "pop", "classic", "classic"}, new int[]{ 500, 2500, 150, 800  });
        for (int i : answer)  {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Album> list = new ArrayList<>();
        List<Genre> gList = new ArrayList<>();
        List<Integer> idx = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            list.add(new Album(i, genres[i], plays[i]));
        }

        for (Album a : list) {
            if (map.get(a.genre) != null) {
                int value = map.get(a.genre) + a.play;
                map.put(a.genre, value);
            } else {
                map.put(a.genre, a.play);
            }
        }

        Set<String> ks = map.keySet();

        for (String s : ks) {
            gList.add(new Genre(s, map.get(s)));
        }

        Collections.sort(gList);
        Collections.sort(list);

        int j = 0;
        for (Genre g : gList) {
            for (Album l : list) {
                if (j < 2) {
                    if (g.genre.equals(l.genre)) {
                        idx.add(l.idx);
                        ++j;
                    }
                } else {
                    break;
                }
            }
            j = 0;
        }

        int[] answer = new int[idx.size()];
        for (int i = 0; i < idx.size(); i++) {
            answer[i] = idx.get(i);
        }
        return answer;
    }
}