# ingswAM2021-DeNovellis-Donato-Bellini
## Software engineering project 20/21.
### Contributors 
Emanuele Bellini, Giovanni De Novellis, Roberto Donato.
### Implemented functionalities
- Complete rules
- Socket 
- CLI 
- GUI
- Local game
- Resilience to disconnections

### Setup
- In the [Deliverables](Deliverables) folder there is the jar file of the game. To run the jar with default IP and port please type:
```shell
java -jar .\AM43.jar 
```
- If you want to specify IP and port please type:
``` shell
java -jar .\AM43.jar "ip" "port"
```
- For example:
``` shell
java -jar .\AM43.jar "127.0.0.2" "1235"
```
- The console will ask you if you want to start in Server, CLI or GUI mode and if you want to play a local game or not.
- CLI tested on WSL. 
- GUI tested on PowerShell and CMD. 
- To play in GUI mode 1920x1080 resolution is needed.
- On CLI mode write help to see all available commands.
