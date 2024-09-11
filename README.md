# Sirma Academy Final Exam backend API
## Designed to handle incoming data from an external frontend React app
### The main task is to find the pair of football players who have played together in common matches and for the longest time and duration for each of those matches.

- Model classes that describe the tables in the database and their respective columns:
  - Player
  - Team
  - FootballMatch
  - MatchRecord

- Repository classes that handle the operations with the database:
  - PlayerRepository
  - TeamRepository
  - FootballMatchRepository
  - MatchRecordRepository

- Services:
  - CsvReaderService - Responsible for reading the information from the different csv files.
  - FootballMatchService - Responsible for CRUD logic for the Matches table.
  - MatchRecordService - extends CsvReaderService for reading information from the records.csv file.
  - PlayerPairService - Responsible for the logic that finds the pair of football players who have played together in common matches and for the longest time and duration for each of those matches.
  - PlayerService - Responsible for CRUD logic for the Players table.
  - TeamService - Responsible for CRUD logic for the Teams table.

- Controllers:
  - CsvReaderController
  - FootballMatchController
  - PlayerPairController
  - TeamController

### Type of database used for the project: PostgreSQL

### Explanation of PlayerPairService class that holds the main logic that solves the assigned task:
1. All match records are retrieved from the repository.
2. Each pair of match records (record1 and record2) are being compared. If both records belong to the same match (record1.getMatch().equals(record2.getMatch()) and it is ensured they are different players (!record1.getPlayer().equals(record2.getPlayer())) then the overlap time is being calculated.
3. For each valid pair of players in the same match, it is being calculated the time they were on the field together by finding the overlap between their playing time ranges. The start of the overlap is the later of their start times, and the end of the overlap is the earlier of their end times (or 90 minutes if no end time is specified).
4. If the players played together for a positive amount of time, the method updates a Map where the key is a list of players (sorted by player ID) and the value is their cumulative shared time.
5. The pair is being identified with the maximum shared time by sorting the Map. After that this pair is returned along with their total shared time.
