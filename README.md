# PA165-SportClub

School project for PA165 subject at Masaryk University - Faculty of Informatics for managing sport teams and their rosters.

For more information, visit our [Wiki.](https://github.com/HonzaCech/PA165-SportClub/wiki)


## Instructions for using REST API

Rest api is available for player at /rest/player
Following requests are possible:
GET on /rest/player to get all players
GET on /rest/player/{id} to get player with ID {id}
GET on /rest/player/{email} to get player with email {email}
POST on /rest/player with player as JSON in body to create player
PUT on /rest/player/{id} with player as JSON in body to edit player with ID {id}
DELETE on /rest/player/{id} to delete player with ID {id}
