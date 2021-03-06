/**
 * Created by Daylon on 9/29/2016.
 */
package teamHarambe;

public class Match {
    Team team1;
    Team team2;
    int time;
    double team1Score;
    double team2Score;
    Referee referee;

    public Match(Team team1, Team team2, double team1Score, double team2Score, Referee referee)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.referee = referee;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(double team1Score) {
        this.team1Score = team1Score;
    }

    public double getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(double team2Score) {
        this.team2Score = team2Score;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getTime() {
        return time;
    }

    public String toString() {
        return team1.getName() + " - " + team2.getName() + "; Refereed by ID # " + getReferee().getId();
    }
}
