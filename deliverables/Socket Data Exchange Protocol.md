# Socket Data Exchange Protocol

![alt text](https://i.imgur.com/V4ut7Pu.png)

</br></br></br>
_____________________________________________________________
</br></br></br>
#### Initial Setup

* ###### Client: Server_up --> Check if server is up and running

    | Timeout: Server down --> After the timeout, the client

    | calls a function check_server to check if the server is up

* ###### Server: Server_ready --> Server answer: it is ready to receive connections

#### Login

* ###### Client: Login(username) --> Client login to server, with username provided by user

    | Timeout: Server error/unreachable --> After the timeout, the client

    | goes back to the __* Initial Setup *__

* ###### Server: User_Logged --> Server accepts client Login

#### Player Setup and Lobby

* ###### Client: Setup_Player --> User sets up his/her Player. After a timeout, it sends default settings

    | Timeout: After __ User_Logged __, a timer starts. If it expires

    | before __ Players_Ready __, the client checks if the server is down,

    | and goes back to __ Initial Setup __, or if it is disconnected, and

    | retries the connection

* ###### Server: Players_Ready --> Server notify that all players are Server_ready
* ###### Server: Start_Game --> Notify the start of the game
#### Gameplay

* ###### Server: Receive_Players_State --> Save locally a copy of the players state, to show to the user. This will be called until it is not the player's turn.

    | Timeout: After __ Start_Game __ or __ Player_Turn __

    | , a timer starts. If it expires before __ Players_Ready __,

    | the client checks if the server is down, and goes back to

    | __ Initial Setup __, or if it is disconnected, and retries the connection

* ###### Server: Player_Turn --> Notify the client that it is its turn

    | Timeout: No Moves --> After the timeout, the player will end its turn

    | doing no moves

* ###### Client: Turn_Moves --> User performs its moves

Those actions (__Gameplay __) are repeated in a cycle (Receive_Players_State (*N= # of Players) --> Player_Turn --> Turn_Moves --> ...) until the server notifies __ Finished_Game __

#### Game Finished

* ###### Server: Finished_Game --> Notify client that the game is over
* ###### Server: Players_Points_&_Winner --> Notify client the players points and who is the winner

#### Logout

* ###### Client: Logout --> Notify logout
* ###### Server: Logged_Out --> Confirm logout
