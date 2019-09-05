import java.util.*;

class Page {
    int idx;
    int basic, link;
    double score;
    
    Page (int idx, int basic, int link, double score) {
        this.idx = idx;
        this.basic = basic;
        this.link = link;
        this.score = score;
    }
}

class Solution {
    public static void main(String[] args) {
        int result1 = solution("blind", new String[] { "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" });
        int result2 = solution("Muzi", new String[] { "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" });

        System.out.println("예1 : " + result1);
        System.out.println("예2 : " + result2);
    }

    public static int solution(String word, String[] pages) {
        int wsize = word.length();
        Map<String, Integer> pageMap = new HashMap<String, Integer>();
        List<Page> pageList = new ArrayList<Page>();
        word = word.toLowerCase();
        for (int i = 0; i < pages.length; ++i) {
            String page = pages[i].toLowerCase();
            // 해당 페이지 url을 찾는 과정
            int mid = 0, posl = 0, posr = 0;
            while (mid <= posl) {
                posl = page.indexOf("<meta", posl + 1);
                posr = page.indexOf(">", posl);
                mid = page.lastIndexOf("https://", posr);
            }

            posr = page.indexOf("\"", mid);
            String url = page.substring(mid, posr);

            // 기본 점수 구하는 과정
            posl = page.indexOf("<body>", posr);
            int basic = 0;
            for (int start = posl; ; ) {
                start = page.indexOf(word, start + 1);
                if (start == -1) break;
                // 해당 문자 앞 뒤로 다른 문자가 붙어서는 안된다
                if (!Character.isLetter(page.charAt(start - 1)) && !Character.isLetter(page.charAt(start + wsize))) {
                    basic++;
                    start += wsize;
                }
            }

            // 외부 링크 수 구하는 과정
            int link = 0;
            for (int start = posl; ; ) {
                start = page.indexOf("<a href", start + 1);
                if (start == -1) break;
                link++;
            }

            pageMap.put(url, i);
            pageList.add(new Page(i, basic, link, (double)basic));
        }

        // 링크 점수 후 매칭 점수 구하는 과정
        for (int i = 0; i < pages.length; ++i) {
            String page = pages[i];
            for (int posl = 0, posr = 0; ; ) {
                posl = page.indexOf("<a href", posr);
                if (posl == -1) break;

                posl = page.indexOf("https://", posl);
                posr = page.indexOf("\"", posl);
                String linkurl = page.substring(posl, posr);

                Integer linkidx = pageMap.get(linkurl);
                if (linkidx != null) {
                    pageList.get(linkidx).score += (double)pageList.get(i).basic / pageList.get(i).link;
                }
            }
        }
        pageList.sort(new Comparator<Page>() {
            @Override
            public int compare(Page a, Page b) {
                if (a.score == b.score) 
                    return a.idx - b.idx;
                else if (a.score < b.score)
                    return 1;
                else
                    return -1;
            }
        });

        return pageList.get(0).idx;
    }
}