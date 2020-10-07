# Restaurant_JC
This application shows near by restaurant list accourding to your current location within 5 km distance.
And also you can see restaurants location on Map with the hepl of marker.


# Overview
Restaurant Near Me applicaion does the following:

This application basically uses the google place API to fetch the near by restaurants.
This application is having 3 pages(Splash screen, Dashboard, and Map Location for the marker) and 2 dialogs(filter dialog and marker short info) 
and other comman utilities.
# To achieve this, 
 - Designed three pages
 - Designed Recycle view for the display list of restaurants
 - Designed map to add the marker on the map.
 - Used retrofit for network call.

# Once application gets launch, it will open the splah screen and ask for location permission.
- capture location


# MainActivity 

- Responsible for the add two tabs 
  1) Restaurant list tab
  2) Restaurant location 


# Restaurant list fragment

- Responsible for fetch data from network and set data into recycle view.

# Restaurant list location

- Responsible for the add marker on the map and once you click on any marker it will display short info about that restaurant.

# ViewModel and Live data used.

# Filter 

- Filter Button used on the dashboard to get the restaurant list according to kilometer or Mile and it will apply on list only when you scroll the screen.  


# For Test App
Clone or Download this repo.
Open downlaoded project in android studio and just run.
