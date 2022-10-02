package com.myAlgorithms;
import com.myAlgorithms.array.algo;
import com.myAlgorithms.array.quickSort;


public class Main {

    public static void main(String[] args) throws Exception {
        algo test = new algo();
        test.findFirstChararcter("aabbcded");
        test.findFirstChararcter("abbbc"); // print (a, 0)
        test.findFirstChararcter("aauiui");
        quickSort s = new quickSort();
        int[] in = new int[] {3, 2, 1};
        s.sort(in, 0, 2);
        for (int el: in) {
            System.out.println(el);
        }
    }
}

























        //pdd
        //Queue<Integer> q = new PriorityQueue<>((a, b) -> (a - b));
        /*
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i ++) {
            int[] first = new int[6];
            int[] second = new int[6];
            for (int j = 0; j < 6; j ++) {
                first[j] = in.nextInt();
            }
            for (int j = 0; j < 6; j ++) {
                second[j] = in.nextInt();
            }
            int ans = countTurns(first, second);
            System.out.println(ans);
        }

        int N = in.nextInt();
        int M = in.nextInt();
        List<soldier> armyA = new ArrayList<>();
        List<soldier> armyB = new ArrayList<>();
        for (int i = 0; i < N; i ++) {
            soldier curr = new soldier();
            curr.attack = in.nextInt(); //attack data
            curr.defend = in.nextInt(); //defend data
            armyA.add(curr);
        }
        Collections.sort(armyA, (soldier a, soldier b) -> (b.attack - a.attack)); //attacking from largest to smallest
        for (int i = 0; i < M; i ++) {
            soldier curr = new soldier();
            curr.attack = in.nextInt(); //attack data
            curr.defend = in.nextInt(); //defend data
            armyB.add(curr);
        }
        Collections.sort(armyB, (soldier a, soldier b) -> (b.defend - a.defend)); //defense rom largest to smallest
        int ans = battle(armyA, armyB);
        System.out.println(ans);


        for (int i = 0; i < N; i ++) {
            int K = in.nextInt();
            int L = in.nextInt();
            String s = in.next();
            int ans = getNums(s, L, K);
            System.out.println(ans);
        }

        //String s = in.nextLine();
        //System.out.println(s);
        //String ans = compressWords(s);
        //System.out.println(ans);
    }
    static String compressWords(String input) {
        if (input == "") return "";
        String ans = "";
        char prev = input.charAt(0);
        int count = 0; //init used for input[0]

        for (int i = 0; i < input.length(); i ++) {
            if (input.charAt(i) != prev) {
                ans += count;
                ans += prev; //append
                count = 0; //re init
                prev = input.charAt(i);//re init
            }
            count ++;
        }
        ans += count;
        ans += prev;
        return ans;
    }

    static int getNums(String s, int L, int K) {
        if (K == 0) return 1;
        for (int i = 0; i < L / 2; i ++) {
            if (s.charAt(i) != s.charAt(L - i - 1)) return 2;
        }
        return 1;
    }

    static int battle(List<soldier> armyA, List<soldier> armyB) {
        int m = Math.min(armyA.size(), armyB.size());
        int count = 0;
        Set<Integer> memo = new HashSet<>();

        for (int i = 0; i < m; i ++) {
            if (armyA.get(i).attack < armyB.get(i).defend) return -1;
            else {
                int findBetterDefend = i;
                while (findBetterDefend < armyA.size()) {
                    if (armyA.get(findBetterDefend).defend > armyB.get(i).attack && !memo.contains(findBetterDefend)) {
                        count ++;
                        memo.add(findBetterDefend);
                        break;
                    }
                    findBetterDefend ++;
                }
            }
        }

        return count;
    }

    static class soldier {
        int attack;
        int defend;
    }

    static int countTurns(int[] first, int[] second) {
        int same = 0;
        for (int i = 0; i < 6; i ++) {
            if (first[i] == second[i]) same ++;
        }
        if (same == 6) return 0;

        for (int i = 0; i < 6; i += 2) {
            boolean flag = false;
            for (int j = 0; j < 6; j += 2) {
                if ( (first[i] == second[j] && first[i + 1] == second[j + 1]) ||
                        (first[i + 1] == second[j] && first[i] == second[j + 1]) ) {
                    flag = true;
                }
            }
            if (!flag) return -1;
        }
        return 1;
    }
}
*/
