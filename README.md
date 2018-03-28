# CleanAndroidProject_MVP

Clean Android Project Example (MVP).

## packages

```
app:
/com/your/packages/
    |
    +--- application/ -> Application, initialize modules.
    |     |
    |     +--- configuration/
    |             |
    |             +--- network/
    |             |      |
    |             |      +--- interceptor/ -> Interceptors, such as add default common parameters for each request, parameters sign, encryption, login token status time out...
    |             |
    |             +--- ...
    |
    |
    +--- inject/ -> CI configuration, inject interactors from bll(business logic layer).
    |
    +--- control/ -> Custom views.
    |
    +--- ui/ -> All ui pages of app.
          |
          +--- /{main} -> A separate package for main pages.
                   |
                   +--- {Main}Activity.java -> {Main}Activity.
                   |
                   +--- {Main}Prsenter.java -> The corresponding Presenter for {Main}Activity.
                   |
                   +--- adapter/ -> Adapters for {Main}Activity.
                   |
                   +--- view/ -> View modules for {Main}Activity.
                   |
                   +--- vm/ -> ViewModels for {Main}Activity.



provider(the `model` layer in `MVP`, provider logic and data for app):
/com/your/packages/provider/
    |
    +--- bll/ -> Business logic layer.
    |     |
    |     +--- application/ -> App application wrapper for provider.
    |     |
    |     +--- inject/ -> CI configuration, inject dependencies from dal(data access layer).
    |     |
    |     +--- interactor/ -> Interactors exposed to the presenter layer.
    |     |        |
    |     |        +--- cache -> Caches, such as login status cache.
    |     |        |
    |     |        +--- comb/ -> Model wrapper for presenter layer.
    |     |        |
    |     |        +--- rxevents/ -> Events for rxbus.
    |     |
    |     |
    |     +--- vm/ -> Common vm class.
    |
    +--- dal/ -> Data access layer.
          |
          +--- db/ -> Database access data source.
          |     |
          |     +--- model/ -> Persistence layer objects, mapping to tables.
          |     |
          |     +--- helper/ -> DatabaseOpenHelper for android sqlite.
          |     |
          |     +--- dao/ -> Database access objects.
          |
          +--- file/ -> File access data source.
          |     |
          |     +--- FileStructure -> File storage structure.
          |
          +--- net/ -> Network access data source.
          |     |
          |     +--- http/ -> Http network.
          |           |
          |           +--- response/ -> Response class for all request.
          |           |
          |           +--- webapi/ -> All request urls.
          |
          |
          +--- prefs/ -> SharedPreference access data source.
          |     |
          |     +--- PrefsConstants -> Shared preference keys.
          |     |
          |     +--- PrefsHelper -> Shared preference access objects.
          |
          +--- ...



..../support/ -> Support module
       |
       +--- bridge/ -> Compatible with unit testing, thread scheduling bridge.
       |
       +--- func/ -> Function interface definition.
       |
       +--- rxbus/ -> Event bus implementation base on RxJava.
       |
       +--- usage/ -> Usage utils, such as `XLazy`.
       |
       +--- xlog/ -> Log utils, Compatible with unit testing base JVM on PC.

```



