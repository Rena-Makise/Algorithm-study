import java.util.*;

class Solution {
    public static void main(String[] args) {
        String[] answer1 = solution(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        for (String answer : answer1) {
            System.out.print(answer + " ");
        }

        System.out.println();
        String[] answer2 = solution(new String[] {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
        for (String answer : answer2) {
            System.out.print(answer + " ");
        }
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
               String head1 = s1.split("[0-9]")[0];
               s1 = s1.replace(head1, "");
               head1 = head1.toUpperCase();

               String strNum = "";
               for (char c : s1.toCharArray()) {
                   if (Character.isDigit(c) && strNum.length() < 5) {
                       strNum += c;
                   } else {
                       break;
                   }
               }
               int num1 = Integer.parseInt(strNum);

               String head2 = s2.split("[0-9]")[0];
               s2 = s2.replace(head2, "");
               head2 = head2.toUpperCase();

               strNum = "";
               for (char c : s2.toCharArray()) {
                   if(Character.isDigit(c) && strNum.length() < 5) {
                       strNum += c;
                   } else {
                       break;
                   }
               }
               int num2 = Integer.parseInt(strNum);

               return head1.equals(head2) ? num1 - num2 : head1.compareTo(head2);
            }
        });
        return files;
    }
  }