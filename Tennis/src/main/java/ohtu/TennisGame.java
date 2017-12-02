package ohtu;

import static java.lang.Math.abs;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        if (m_score1 == m_score2) {
            return scoreWhenBothAreSame(m_score1);

        } else if (m_score1 >= 4 || m_score2 >= 4) {
            if (haveOtherPlayerWon()) {
                return winner();
            }

            return whoHasAdvantage();

        } else {
            String score = changeNumberToRightText(m_score1);
            score += "-";
            score += changeNumberToRightText(m_score2);

            return score;
        }
    }

    public String scoreWhenBothAreSame(int sameScore) {
        switch (sameScore) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";

        }
    }

    public boolean haveOtherPlayerWon() {
        if (m_score1 > 3 || m_score2 > 3) {
            int scoreDifference = abs(m_score1 - m_score2);

            return scoreDifference > 1;
        }

        return false;
    }

    public String winner() {
        if (m_score1 > m_score2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    public String whoHasAdvantage() {
        if (m_score1 > m_score2) {
            return "Advantage player1";
        } else {
            return "Advantage player2";
        }
    }

    public String changeNumberToRightText(int scoreNumber) {
        switch (scoreNumber) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }

        return "";
    }
}
