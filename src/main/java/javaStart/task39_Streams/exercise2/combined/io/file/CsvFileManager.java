package javaStart.task39_Streams.exercise2.combined.io.file;

import javaStart.task39_Streams.exercise2.combined.exception.DataReadException;
import javaStart.task39_Streams.exercise2.combined.exception.DataWriteException;
import javaStart.task39_Streams.exercise2.combined.io.ConsolePrinter;
import javaStart.task39_Streams.exercise2.combined.model.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CsvFileManager implements FileManager {
    private ConsolePrinter printer = new ConsolePrinter();
    private League league = new EuropeanLeague();
    private static final String FILE_NAME = "D:\\INNE\\Programowanie\\Projects\\learning\\matches\\matches.txt";

    public void readFile() {
        try (var reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    printer.printLine(line);
                }
        } catch (FileNotFoundException e) {
            throw new DataReadException("No file: " + FILE_NAME);
        } catch (IOException e) {
            throw new DataReadException("Error read file: " + FILE_NAME);
        }
    }

    public void writeFile(MatchManager matchManager) {
        try (var writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            chooseOnYourOwnTeamsTakingPartInCompetition(matchManager, writer);
            laLiga(matchManager, writer);
            premierLeague(matchManager, writer);
        } catch (IOException e) {
            throw new DataWriteException("Data write error into file: " + FILE_NAME);
        }
    }

    private void premierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        //zapis drużyn biorących udział w zawodach
        writeTeamsPremierLeague(writer);
        //zapis wyników pierwszej rundy
        writeResultsFirstRoundPremierLeague(matchManager, writer);
        //zapis tabeli pierwszej rundy
        writeTableFirstRoundPremierLeague(matchManager, writer);
        //zapis wyników meczy rewanżowych
        writeResultsRematchesPremierLeague(matchManager, writer);
        //zapis tabeli meczów rewanżowych
        writeTableRematchesPremierLeague(matchManager, writer);
        //zapis tabeli z wszystkich meczów
        writeTableAllMatchesPremierLeague(matchManager, writer);
        //liczenie bramek w pierwszej rundzie
        countByGoalsFirstRoundPremierLeague(matchManager, writer);
        //liczenie bramek rundy rewanżowej
        countByGoalsRematchesPremierLeague(matchManager, writer);
        //liczenie wszystkich bramek
        countByGoalsAllMatchesPremierLeague(matchManager, writer);
    }

    private void laLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        //zapis drużyn biorących udział w zawodach
        writeTeamsLaLiga(writer);
        //zapis wyników pierwszej rundy
        writeResultsFirstRoundLaLiga(matchManager, writer);
        //zapis tabeli pierwszej rundy
        writeTableFirstRoundLaLiga(matchManager, writer);
        //zapis wyników meczy rewanżowych
        writeResultsRematchesLaLiga(matchManager, writer);
        //zapis tabeli meczów rewanżowych
        writeTableRematchesLaLiga(matchManager, writer);
        //zapis tabeli z wszystkich meczów
        writeTableAllMatchesLaLiga(matchManager, writer);
        //liczenie bramek w pierwszej rundzie
        countByGoalsFirstRoundLaLiga(matchManager, writer);
        //liczenie bramek rundy rewanżowej
        countByGoalsRematchesLaLiga(matchManager, writer);
        //liczenie wszystkich bramek
        countByGoalsAllMatchesLaLiga(matchManager, writer);
    }

    private void chooseOnYourOwnTeamsTakingPartInCompetition(MatchManager matchManager, BufferedWriter writer) throws IOException {
        //zapis drużyn biorących udział w zawodach
        writeTeamsUserChoice(matchManager, writer);
        //zapis wyników pierwszej rundy
        writeResultsFirstRoundUserChoice(matchManager, writer);
        //zapis tabeli pierwszej rundy
        writeTableFirstRoundUserChoice(matchManager, writer);
        //zapis wyników meczy rewanżowych
        writeResultsRematchesUserChoice(matchManager, writer);
        //zapis tabeli meczów rewanżowych
        writeTableRematchesUserChoice(matchManager, writer);
        //zapis wyników wszystkich meczów
        writeResultsAllMatchesUserChoice(matchManager, writer);
        //zapis tabeli z wszystkich meczów
        writeTableAllMatchesUserChoice(matchManager, writer);
        writer.write("Statistics");
        writer.newLine();
        //STATYSTYKI
        //liczenie zespołów
        countByTeamsUserChoice(matchManager, writer);
        //liczenie bramek w pierwszej rundzie
        countByGoalsFirstRoundUserChoice(matchManager, writer);
        //liczenie bramek rundy rewanżowej
        countByGoalsRematchesUserChoice(matchManager, writer);
        //liczenie wszystkich bramek
        countByGoalsAllMatchesUserChoice(matchManager, writer);
        //sortowanie wszystkich meczów od wygranych do porażek
        sortFromWinsToLosesByAllMatchesUserChoice(matchManager, writer);
        //sortowanie pierwsza runda jednego zespołu
        sortByTeamFirstRoundUserChoice(matchManager, writer);
        //sortowanie mecze rewanżowe jednego zespołu
        sortByTeamRematchesUserChoice(matchManager, writer);
        //sortowanie wszystkie mecze jednego zespołu
        sortByTeamAllMatchesUserChoice(matchManager, writer);
    }

    private void sortByTeamFirstRoundUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.newLine();
        List<Match> allMatches = matchManager.getMatchFirstRoundUserChoice();
        String team = matchManager.getTeamFirstRoundUserChoice();
        List<Match> allMatchesOneTeam = matchManager.sortByTeam(allMatches, team);
        writer.write("First round of the following single team: " + team);
        writer.newLine();
        printCollectionLine(writer, allMatchesOneTeam);
        writeDoubleNewLine(writer);
    }

    private void sortByTeamRematchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.newLine();
        List<Match> allMatches = matchManager.getMatchRematchesUserChoice();
        String team = matchManager.getTeamRematchesUserChoice();
        List<Match> allMatchesOneTeam = matchManager.sortByTeam(allMatches, team);
        writer.write("Rematches of the following single team: " + team);
        writer.newLine();
        printCollectionLine(writer, allMatchesOneTeam);
        writeDoubleNewLine(writer);
    }

    private void sortByTeamAllMatchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.newLine();
        List<Match> allMatches = matchManager.getMatchAllMatchesUserChoice();
        String team = matchManager.getTeamUserChoiceAllMatches();
        List<Match> allMatchesOneTeam = matchManager.sortByTeam(allMatches, team);
        writer.write("All matches of the following single team: " + team);
        writer.newLine();
        printCollectionLine(writer, allMatchesOneTeam);
        writeDoubleNewLine(writer);
    }

    private void writeTeamsUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        Collection<String> teams = matchManager.getTeamsUserChoice();
        writer.write("Teams taking part in the competition:");
        writer.newLine();
        printCollectionNoLine(writer, teams);
        writeDoubleNewLine(writer);
    }

    private void writeTeamsLaLiga(BufferedWriter writer) throws IOException {
        Collection<String> teams = league.laLiga();
        writer.write("Teams taking part in the competition:");
        writer.newLine();
        printCollectionNoLine(writer, teams);
        writeDoubleNewLine(writer);
    }

    private void writeTeamsPremierLeague(BufferedWriter writer) throws IOException {
        Collection<String> teams = league.premierLeague();
        writer.write("Teams taking part in the competition:");
        writer.newLine();
        printCollectionNoLine(writer, teams);
        writeDoubleNewLine(writer);
    }

    private void writeResultsFirstRoundLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> firstRound = matchManager.getMatchFirstRoundLaLiga();
        writer.write("Results of the first round:");
        writer.newLine();
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);

    }

    private void writeResultsFirstRoundPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> firstRound = matchManager.getMatchFirstRoundPremierLeague();
        writer.write("Results of the first round:");
        writer.newLine();
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeResultsRematchesLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> firstRound = matchManager.getMatchRematchesLaLiga();
        writer.write("Results of the rematches:");
        writer.newLine();
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeResultsRematchesPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> firstRound = matchManager.getMatchRematchesPremierLeague();
        writer.write("Results of the rematches:");
        writer.newLine();
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeResultsFirstRoundUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> firstRound = matchManager.getMatchFirstRoundUserChoice();
        writer.write("Results of the first round:");
        writer.newLine();
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeTableFirstRoundUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the first round:");
        writer.newLine();
        List<Scoring> firstRound = matchManager.getScoringFirstRoundUserChoice();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(firstRound);
        firstRound = matchManager.getKey(scoringListMap);
        firstRound = matchManager.sortByPointsAndGoals(firstRound);
        writer.write(printer.printStandingsShortcutsTable());
        firstRound = matchManager.increasePosition(firstRound);
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeTableFirstRoundLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the first round:");
        writer.newLine();
        List<Scoring> firstRound = matchManager.getScoringFirstRoundLaLiga();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(firstRound);
        firstRound = matchManager.getKey(scoringListMap);
        firstRound = matchManager.sortByPointsAndGoals(firstRound);
        writer.write(printer.printStandingsShortcutsTable());
        firstRound = matchManager.increasePosition(firstRound);
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeTableFirstRoundPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the first round:");
        writer.newLine();
        List<Scoring> firstRound = matchManager.getScoringFirstRoundPremierLeague();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(firstRound);
        firstRound = matchManager.getKey(scoringListMap);
        firstRound = matchManager.sortByPointsAndGoals(firstRound);
        writer.write(printer.printStandingsShortcutsTable());
        firstRound = matchManager.increasePosition(firstRound);
        printCollectionLine(writer, firstRound);
        writeDoubleNewLine(writer);
    }

    private void writeTableRematchesLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the rematches:");
        writer.newLine();
        List<Scoring> rematches = matchManager.getScoringRematchesLaLiga();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(rematches);
        rematches = matchManager.getKey(scoringListMap);
        rematches = matchManager.sortByPointsAndGoals(rematches);
        writer.write(printer.printStandingsShortcutsTable());
        rematches = matchManager.increasePosition(rematches);
        printCollectionLine(writer, rematches);
        writeDoubleNewLine(writer);
    }

    private void writeTableRematchesPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the rematches:");
        writer.newLine();
        List<Scoring> rematches = matchManager.getScoringRematchesPremierLeague();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(rematches);
        rematches = matchManager.getKey(scoringListMap);
        rematches = matchManager.sortByPointsAndGoals(rematches);
        writer.write(printer.printStandingsShortcutsTable());
        rematches = matchManager.increasePosition(rematches);
        printCollectionLine(writer, rematches);
        writeDoubleNewLine(writer);
    }

    private void writeResultsRematchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> rematches = matchManager.getMatchRematchesUserChoice();
        writer.write("Results of the rematches:");
        writer.newLine();
        printCollectionLine(writer, rematches);
        writeDoubleNewLine(writer);
    }

    private void writeTableRematchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the rematches:");
        writer.newLine();
        List<Scoring> rematches = matchManager.getScoringRematchesUserChoice();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(rematches);
        rematches = matchManager.getKey(scoringListMap);
        rematches = matchManager.sortByPointsAndGoals(rematches);
        writer.write(printer.printStandingsShortcutsTable());
        rematches = matchManager.increasePosition(rematches);
        printCollectionLine(writer, rematches);
        writeDoubleNewLine(writer);
    }

    private void writeResultsAllMatchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> allMatches = matchManager.getMatchAllMatchesUserChoice();
        writer.write("Results of the all matches:");
        writer.newLine();
        printCollectionLine(writer, allMatches);
        writeDoubleNewLine(writer);
    }

    private void writeTableAllMatchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the all matches:");
        writer.newLine();
        List<Scoring> allScores = matchManager.getScoringAllMatchesUserChoice();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(allScores);
        allScores = matchManager.getKey(scoringListMap);
        allScores = matchManager.sortByPointsAndGoals(allScores);
        writer.write(printer.printStandingsShortcutsTable());
        allScores = matchManager.increasePosition(allScores);
        printCollectionLine(writer, allScores);
        writeDoubleNewLine(writer);
    }

    private void writeTableAllMatchesLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the all matches:");
        writer.newLine();
        List<Scoring> allScores = matchManager.getScoringAllMatchesLaLiga();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(allScores);
        allScores = matchManager.getKey(scoringListMap);
        allScores = matchManager.sortByPointsAndGoals(allScores);
        writer.write(printer.printStandingsShortcutsTable());
        allScores = matchManager.increasePosition(allScores);
        printCollectionLine(writer, allScores);
        writeDoubleNewLine(writer);
    }

    private void writeTableAllMatchesPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("The table of the all matches:");
        writer.newLine();
        List<Scoring> allScores = matchManager.getScoringAllMatchesPremierLeague();
        Map<Scoring, List<Scoring>> scoringListMap = matchManager.groupByName(allScores);
        allScores = matchManager.getKey(scoringListMap);
        allScores = matchManager.sortByPointsAndGoals(allScores);
        writer.write(printer.printStandingsShortcutsTable());
        allScores = matchManager.increasePosition(allScores);
        printCollectionLine(writer, allScores);
        writeDoubleNewLine(writer);
    }

    private void countByTeamsUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<String> teams = matchManager.getTeamsUserChoice();
        int teamsNumber = matchManager.countByTeams(teams);
        writer.write("Number of teams in the competition: " + teamsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsFirstRoundUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchFirstRoundUserChoice();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("Number of goals in the competition by the first round: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsFirstRoundLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchFirstRoundLaLiga();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("La Liga goals in first round: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsFirstRoundPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchFirstRoundPremierLeague();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("Premier League goals in first round: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsRematchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchRematchesUserChoice();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("Number of goals in the competition by the rematches: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsRematchesLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchRematchesLaLiga();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("La Liga goals in rematches: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsRematchesPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchRematchesPremierLeague();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("La Liga goals in rematches: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsAllMatchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchAllMatchesUserChoice();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("The competition goals in all rounds: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsAllMatchesLaLiga(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchAllMatchesLaLiga();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("La Liga goals in all matches: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void countByGoalsAllMatchesPremierLeague(MatchManager matchManager, BufferedWriter writer) throws IOException {
        List<Match> matches = matchManager.getMatchAllMatchesPremierLeague();
        long goalsNumber = matchManager.countByGoals(matches);
        writer.write("Premier League goals in all matches: " + goalsNumber);
        writeDoubleNewLine(writer);
    }

    private void sortFromWinsToLosesByAllMatchesUserChoice(MatchManager matchManager, BufferedWriter writer) throws IOException {
        writer.write("Sorted matches from win to loss by all matches");
        writer.newLine();
        List<Match> allMatches = matchManager.getMatchAllMatchesUserChoice();
        allMatches = sortByScores(matchManager, allMatches);
        printCollectionLine(writer, allMatches);
        writer.newLine();
    }

    private <T> void printCollectionNoLine(BufferedWriter writer, Collection<T> list) throws IOException {
        String collect = list.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
        writer.write(collect);
    }

    private <T> void printCollectionLine(BufferedWriter writer, Collection<T> list) throws IOException {
        String collect = list.stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
        writer.write(collect);
    }

    private List<Match> sortByScores(MatchManager matchManager, List<Match> matches) {
        List<Match> winnersHome = matchManager.sortByWinnersHomeTeam(matches);
        List<Match> ties = matchManager.sortByTies(matches);
        List<Match> winnersAway = matchManager.sortByWinnersAwayTeam(matches);
        return matchManager.joinWinTieLoss(winnersHome, ties, winnersAway);
    }

    private void writeDoubleNewLine(BufferedWriter writer) throws IOException {
        writer.newLine();
        writer.newLine();
    }
}
