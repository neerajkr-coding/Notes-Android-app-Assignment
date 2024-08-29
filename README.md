## [Download APK File](https://drive.google.com/file/d/1ledMlM2wXofb7zgneNrkr1yIKQbkRN_J/view?usp=sharing)
## [Drive Link](https://drive.google.com/drive/folders/11xA7Tzaut-4x-YBcNFFylloc2ySeSe11?usp=sharing)

https://github.com/user-attachments/assets/b5b01cf8-9f81-4d7e-bdb9-31f6bd4e682a

# Notes Android App

## Overview
This assignment involves designing and developing a simple Notes Android app with the following features:

1. **Single Activity Design:** The app uses only **one activity**, screens implemented using **multiple fragments**.
2. **Login Screen:** Users are prompted to log in using **Gmail**. The app tracks logged-in users using **SharedPreferences**.
3. **Notes Display:** Once logged in, users see all their notes displayed in a **RecyclerView**.
4. **Note Management:** Users can **add, update, and delete** notes.
 
## Implementation Details
- **Architecture**: The app is designed using the **MVVM (Model-View-ViewModel) architectural pattern**, ensuring a clean separation between the UI and business logic.
- **Database:** Instead of using SQLite directly, the app uses **Room DB**, an abstraction layer over SQLite. Room simplifies database operations and provides compile-time verification of SQL queries.
- **Data Binding:** Data binding is used throughout the app to **bind UI components directly to data sources**, reducing boilerplate code and enhancing maintainability.
- **Login:** Gmail is used for authentication instead of Google+, following its discontinuation.
- **Multiple Fragments:** Different screens and functionalities are implemented using fragments within a single activity.
## Benefits of Using Room DB over SQLite
- **Simplified Database Access:** Room provides a higher-level abstraction over SQLite, making database operations easier and less error-prone.
- **Compile-Time Verification:** SQL queries in Room are checked at compile time, reducing runtime errors.
- **LiveData Integration:** Room supports LiveData, which helps in observing data changes and updating the UI automatically.
- **Data Conversion:** Room handles the conversion between database types and Java/Kotlin types, reducing the need for manual conversion.
- **Ease of Use:** Room simplifies complex database operations with annotations and provides a cleaner API for accessing data.
## Features
- Login Screen: User authentication via Gmail.
- Notes Management: Add, update, and delete notes.
- RecyclerView: Display all notes of the logged-in user in a RecyclerView.
- Data Binding: Utilized throughout the app to streamline UI updates and reduce boilerplate code.
