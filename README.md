# WaracleTest
Tasks:

1. When the app is started, load and display a list of cakes
    a. Remove duplicate entries - Done
    b. Order entries by name - Done
    c. Display image and title for each entry - Done
    d. Display a divider between each entry - Done
2. Display the cake description in some kind of popup when a cake entry is clicked - Done
3. Provide some kind of refresh option that reloads the list - Done (Swipe to refresh)
4. Display an error message if the list cannot be loaded (e.g. no network) - Done.

    Additions to task:
    1. Handle orientation changes, ideally without reloading the list - Done, no reloading from server
    2. Provide an option to retry when an error is presented - Done.
    3. Animate in list items (e.g. fade in or fall down animations)


Proposed Architecture:
I would advise using MVVM+Clean.
As previously recommended by Google, I have decided to use the MVVM achitecture.
I have also added some usecases, and advised by Uncle Bob.

Some additional notes:
Mappers - in order to create a strict separation, between data that's returned by the server to the
entities that are being used in the app, the mapper comes to serve this purpose.

Hilt (DI)- a library supported by google,that wraps dagger, to give an easy way to inject dependencies.

Coroutines (Async) - the suggested and recommended library,for handling async tasks. This supports
the api requests

Tha above decisions are coming to support the ability to maintain, fix, read, and scale the project, in really easy manner.1

What would I add/change:
-I would add some scenarios for testing,
-I would name the tests with the GIVEN, WHEN, THEN
-I would have a better ui


