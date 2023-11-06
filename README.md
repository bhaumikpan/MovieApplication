## Introduction
App displays list of Movies from api https://api.themoviedb.org/3/movie/
Here are a few key points to pay attention to in the project:
1. UI implementation using MVVM Architecture
    * A starting Activity is added and it displays a `ListFragment` contains list of movies
    * `MovieAdapter` is wired up movie Data class `movie_item_view` layout file
2. Architecture using Hilt
    * It uses Daggers for dependency injection (DI) within the app
    * Package `di` has `ServerModule` and `DispatchModule` for DI use
    * Package `domain` has `repository` and `usecase` layer
    * Package `data` has `Movie` data classes inside
3. Network
    * Network stack is covered by Retrofit2
4. additional libs, Picaso (for Images), mockk (for UNIT test)

- minsdk version 26
- on running the app, MainActivity is loaded, opens ListFragment which makes api GET call to get Movies list
- while api call is happening progress bar is displayed
- on Success List is displayed
- Success unit test is added for api
- On click on Movie any item `MovieDeatilsFragment` respective Movie is display which has Movie details

