# Newsly

Newsly uses [newsapi.org](https://newsapi.org) to fetch news from your country. 
It shows a list of headlines, and clicking on one takes you to the news website.

![Screenshot_1](media/Screenshot_1.png)
![Screenshot_2](media/Screenshot_2.png)
![Screenshot_3](media/Screenshot_3.png)

The app is built using **MVVM architecture** and Kotlin Coroutines.
Whole UI written in Jetpack Compose with day/night theming support.
Uses Room DAO for caching news items

Libraries used in this project -

- [Dagger 2](https://github.com/google/dagger)
- [Retrofit 2](https://square.github.io/retrofit/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room](https://developer.android.com/topic/libraries/architecture/room)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

#### Build Instructions
Create [your own API key](https://newsapi.org/register) . Place your API key at 
```java
co.k2.newsbits.common.NewsApi.API_KEY
```
Build and run as usual 
