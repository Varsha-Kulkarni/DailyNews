## :scroll: Description
This is the sample News app, showing top headlines from different news sources.

- On app launch user needs to be authenticated using google signin.
- When user signs in, tabbed view opens with top headlines in first tab and
second tab showing different news sources (from https://newsapi.org)
- each News item can be viewed by clicking or can be saved into favorites.

This app demonstrates the following views and techniques:

* [Mavericks](https://github.com/airbnb/mavericks) to manage state.
* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service.
* [Picasso](https://square.github.io/picasso/) to load and cache images by URL.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for DI
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Room](https://developer.android.com/training/data-storage/room) for local database storage.
* [Flow](https://developer.android.com/kotlin/flow) to receive live updates from a database.
* [Coroutines](https://developer.android.com/kotlin/coroutines) for asynchronous programming.
* [Fragments](https://developer.android.com/guide/fragments) for UI.
* [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) to display list
of news data
* [TabLayout](https://developer.android.com/guide/navigation/navigation-swipe-view-2)
 to display tabs horizontally
* [ViewPager2](https://developer.android.com/training/animation/screen-slide-2) to swipe views
* [CardView](https://developer.android.com/guide/topics/ui/layout/cardview) to implement
the Material Design card pattern with round corners and drop shadows.
* [SwipeRefreshLayout](https://developer.android.com/training/swipe/add-swipe-interface) to implement
swipe-to-refresh UI pattern

## License
```
Copyright 2021 Varsha-Kulkarni

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

