# Restaurant_JC
This app is fetching the near by restaurants and display the restaurants in list and also display marker info of the restaurant.


# Overview

Restaurant Near Me app does the following:

This application basically uses the google place api for the fecth the near by restaurants
App having 3 pages(Splash screen, Dashboard, and Map Location for the marker) and 2 dialogs(filter dialog and marker short info) and other comman utilities.
# To achieve this, 
 - Designe Recycle view for the display list of restaurants
 - Designed three pages
 - Design the map for the add marker on the map.
 - Used the retrofit for network call.

# Once application launch it open the splah screen and ask for permissoion.
- capture location


# MainActivity 

- Responsible for the add two tabs 
  1) Restaurant list tab
  2) Restaurant location 


# Restaurant list fragment

- Responsible for open fetch data from network and set data into recycle view.

# Restaurant list location

- Responsible for the add marker on the map and once click of any marker it will display short info about that restaurant.

# ViewModel and Live data used.

# Filter 

- Filter Button on the dashboard select any of them(Km,Mile) and apply and scroll list you will see the data respetivly Km or Mile.  


# For Test App
Clone or Download from this repo.
Open downlaoded project in android studio and just run.
