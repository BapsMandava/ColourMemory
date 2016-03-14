# ColourMemory

1.Introduction.
  Aapplication for a Colour Memory Game in android native application development
The project caters to below user type:
·         Players – Users who play the game.
The project aims to deliver a gaming experience to the players and show its highscores stored in local DB.
 
3.ColourMemory Application
ColourMemory project comprises of single application developed in android.
Key Features
 
ü  ColourMemory is a gaming application where the palyer has to match the same pair of colours

ü  After every game the player enters his name stores the score and name in the local DB of the mobile.

ü  Player at any point can view his rank and scores in decending order.

 
4.Key Technical Choices
While designing ColourMemory, various Native and 3rd party lobabries  were analyzed and the most suitable ones were chosen for its implementation. Technologies used in the ColourMemory application development are illustrated below along with the reason for the decisions made.
 
Development:
Android is chosen for development as per the developer comfort and competency.
 
FlipView:
For the flip animation for colour images a 3rd party library davideas/FlipView is considered as the library serves flip animation required for  the game.
Local DataBase:
Realm DB for Android is used as the local DB instead of the traditional SQLLite  as Real DB is more efficient and in terms of reads and writes to local database when compared to SQLLite.
 
5.Recommendation for Future Work

Recommendations for enhancements and future work for the ColourMemory
Intergarte Server: Instead of the local DB storing player name and score , the name and score can be stored in a server and retrieve when needed.This way the player can see his high score ranked with other players as well.
UI Improvements : A smooth UI transistion if images and experience can be incorporated.
Include iOS version of the application: To target more player for the game iOS version can be developed
Linkage to Social media pages : Allow users to link to their social media pages like Facebook or twitter to share and challenge their game score with their friends.
 
