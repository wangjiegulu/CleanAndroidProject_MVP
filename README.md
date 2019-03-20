# CleanAndroidProject_MVP

Clean Android Project Example (MVP).

## Libraries and tools used

- [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
- [Dagger2](https://github.com/google/dagger)
- [OkHttp](http://square.github.io/okhttp/)
- [dal_request](https://github.com/wangjiegulu/dal_request)
- [MVPArchitecture](https://github.com/wangjiegulu/MVPArchitecture)
- [Gson](https://github.com/google/gson)
- [RapidORM](https://github.com/wangjiegulu/RapidORM)
- [Mockito](http://site.mockito.org/)
- [Espresso](https://developer.android.com/training/testing/espresso/index.html)


## Packages

```
app:
/com/your/packages/
    |
    +--- application/ -> Application, initialize modules.
    |     |
    |     +--- configuration/
    |             |
    |             +--- AppSchedulers -> Schedulers of RxJava in viewer layer.
    |
    |
    +--- inject/ -> DI configuration, inject interactors from bll(business logic layer).
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
                   +--- vo/ -> View Objects for {Main}Activity.



provider(the `model` layer in `MVP`, provider logic and data for app):
/com/your/packages/provider/
    |
    +--- bll/ -> Business logic layer.
    |     |
    |     +--- application/ -> App application wrapper for provider.
    |     |         |
    |     |         +--- configuration/
    |     |                    |
    |     |                    +--- ProviderSchedulers -> Schedulers of RxJava in Model layer.
    |     |                    |
    |     |                    +--- network/
    |     |                          |
    |     |                          +--- interceptor/ -> Interceptors, such as add default common parameters for each request, parameters sign, encryption, login token status time out...
    |     |
    |     |
    |     +--- inject/ -> DI configuration, inject dependencies from dal(data access layer).
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
    |     +--- bo/ -> business objects.
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
          |     +--- FileRespository -> File storage respository.
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
          |     +--- PrefsRespository -> Shared preference access objects.
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



