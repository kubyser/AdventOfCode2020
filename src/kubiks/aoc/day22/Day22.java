package kubiks.aoc.day22;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;

public class Day22 {
    List<LinkedList<Integer>> startingDecks;

    public Day22(List<String> data) {
        startingDecks = new ArrayList<>();
        for (String s: data) {
            if (s.matches("Player [0-9]+:")) {
                startingDecks.add(new LinkedList<>());
            } else if (s.matches("[0-9]+")){
                startingDecks.get(startingDecks.size()-1).add(Integer.parseInt(s));
            }
        }
    }

    int getMaxIndex(List<Integer> list) {
        Iterator<Integer> iter = list.iterator();
        int pos = 0;
        int max = -1, maxIndex = -1;
        while (iter.hasNext()) {
            int value = iter.next();
            if (value > max || maxIndex == -1) {
                max = value;
                maxIndex = pos;
            }
            pos ++;
        }
        return maxIndex;
    }

    int winnerOfRound(List<Integer> cards, List<LinkedList<Integer>> decks, boolean recursive) {
        if (!recursive) {
            return getMaxIndex(cards);
        }
        int pos = 0;
        for (int numCards:cards) {
            if (numCards > decks.get(pos).size()) {
                return getMaxIndex(cards);
            }
            pos++;
        }
        return prepareAndPlayRecursiveGame(cards, decks);
    }

    public int playRound(List<LinkedList<Integer>> decks, boolean recursive) {
        LinkedList<Integer> cards = new LinkedList<>();
        for (int player=0; player<decks.size(); player++) {
            if (!decks.get(player).isEmpty()) {
                int card = decks.get(player).removeFirst();
                cards.add(card);
            }
        }
        int winner = winnerOfRound(cards, decks, recursive);
        decks.get(winner).add(cards.get(winner));
        cards.remove(winner);
        decks.get(winner).addAll(cards);
        if (decks.stream().filter(e -> e.isEmpty()).count() == decks.size() - 1) {
            return winner;
        }
        return -1;
    }

    public int countScore(int player) {
        int score = 0;
        Iterator iter = startingDecks.get(player).iterator();
        for (int pos = startingDecks.get(player).size()-1; pos >=0; pos--) {
            score += (int)iter.next() * (pos + 1);
        }
        return score;
    }

    public int playFullGame(boolean recursive) {
        int winner = playGame(startingDecks, recursive);
        return countScore(winner);
    }

    int prepareAndPlayRecursiveGame(List<Integer> cards, List<LinkedList<Integer>> previousDecks) {
        List<LinkedList<Integer>> newDecks = new ArrayList<>(previousDecks.size());
        Iterator playersCardsIter = cards.iterator();
        for (int player=0; player<previousDecks.size(); player++) {
            newDecks.add(new LinkedList<>());
            int numCards = (int)playersCardsIter.next();
            Iterator cardsIter = previousDecks.get(player).iterator();
            for (int pos=0; pos<numCards; pos++) {
                newDecks.get(newDecks.size()-1).add((int)cardsIter.next());
            }
        }
        return playGame(newDecks, true);
    }

    int playGame(List<LinkedList<Integer>> decks, boolean recursive) {
        int winner;
        Set<String> previousRounds = new HashSet<>();
        do {
            if (recursive) {
                if (previousRounds.contains(decks.toString())) {
                    return 0;
                } else {
                    previousRounds.add(decks.toString());
                }
            }
            winner = playRound(decks, recursive);
        } while (winner == -1);
        return winner;
    }

    public List<LinkedList<Integer>> getStartingDecks() {
        return startingDecks;
    }

    public static void main(String[] args) {
        Day22 day22 = new Day22(FileReaderUtils.readStringListFromFile("resources/day22_input.txt"));
        int answer = day22.playFullGame(false);
        System.out.format("Winning score: %d\n", answer);
        day22 = new Day22(FileReaderUtils.readStringListFromFile("resources/day22_input.txt"));
        int answerPart2 = day22.playFullGame(true);
        System.out.format("Winning score in recursive game: %d\n", answerPart2);
    }
}
