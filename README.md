CurrencyDemo

Project Overview

    CurrencyDemo is an Android application designed to showcase a modern approach to currency listing, filtering,
    and management for both fiat and cryptocurrencies. The app leverages the latest Android development libraries
    and principles such as Jetpack Compose for UI, Dagger-Hilt for dependency injection, and Kotlin Coroutines for
    handling asynchronous tasks. The app provides features for loading, filtering, searching, and clearing currencies
    in a reactive and efficient way.

Features

	•	Display Currencies: View a list of both fiat and cryptocurrencies.
	•	Filter by Currency Type: Filter the list by fiat or crypto currencies.
	•	Search Functionality: Search for a currency by name or symbol.
	•	Insert Currencies: Add new fiat or cryptocurrency data to the database.
	•	Clear All Currencies: Remove all currencies from the database.
	•	Jetpack Compose UI: A clean and modern UI using Compose.
	•	Coroutines & StateFlow: Ensuring asynchronous tasks and reactive state management.

Technologies Used

	•	Kotlin: The primary programming language used in Android development.
	•	Jetpack Compose: A modern toolkit for building native Android UIs.
	•	Dagger-Hilt: Dependency injection framework for managing dependencies.
	•	Kotlin Coroutines: For handling concurrency and background tasks.
	•	StateFlow: A state-holder observable flow for managing state in Jetpack Compose.
	•	JUnit & Mockito: For unit testing ViewModel and use cases.
	•	Retrofit (Optional): Could be used for integrating real-time API data in the future.
	•	Room Database: Android’s official SQLite object-mapping library for local storage.

Project Structure

    CurrencyDemo/
    │
    ├── ui/
    │   ├── viewmodels/          # ViewModels for managing UI-related data in a lifecycle-conscious way
    │   ├── components/          # Jetpack Compose UI components
    │   └── activities/          # MainActivity and other activity-level UI logic
    │
    ├── domain/
    │   ├── model/               # Data models (Currency, etc.)
    │   ├── repository/          # Repository interfaces for managing data sources
    │   └── usecase/             # Use cases encapsulating application logic (GetCurrenciesUseCase, InsertCurrenciesUseCase, etc.)
    │
    ├── data/
    │   ├── local/               # Local data sources (Room database, DAOs)
    │   └── remote/              # Placeholder for remote data sources (if needed for future API integration)
    │
    ├── utils/                   # Utility classes, enums (CurrencyType, extensions, etc.)
    ├── di/                      # Dependency Injection (Dagger-Hilt modules)
    └── tests/                   # Unit tests for ViewModels and use cases

Setup Instructions

    1.	Clone the repository:
        •   git clone https://github.com/your-repo/currencydemo.git
    2.	Open the project in Android Studio.
    3.	Sync the Gradle project and ensure all dependencies are downloaded.
    4.	Run the app:
        •	Connect an Android emulator or a physical device.
        •	Click on the “Run” button in Android Studio.
    5.	Run Unit Tests:
        •	Open the CurrencyViewModelTest class.
        •	Right-click and select Run 'CurrencyViewModelTest'.

How It Works

ViewModel: CurrencyViewModel
    
    The CurrencyViewModel manages the state of the list of currencies. It provides various functions to:
	•	Load all currencies.
	•	Load only cryptocurrencies.
	•	Load only fiat currencies.
	•	Insert new currencies into the database.
	•	Clear the database of all currency data.
	•	Search for currencies by name or symbol.

Each function is executed within the viewModelScope and uses Kotlin Coroutines with the Dispatchers.IO context to ensure all long-running operations happen off the main thread.

Use Cases

Use cases encapsulate individual pieces of business logic:

	•	GetCurrenciesUseCase: Fetches currencies from the repository.
	•	InsertCurrenciesUseCase: Adds new currencies to the local database.
	•	ClearDatabaseUseCase: Removes all currency records from the database.

These are injected into the CurrencyViewModel via Dagger-Hilt.

Jetpack Compose UI

The UI is built using Jetpack Compose and includes:

	•	A search bar for filtering currencies.
	•	A list of currency items (using LazyColumn).
	•	Buttons for loading, filtering, and clearing currencies.

State Management
    
    The app uses MutableStateFlow and StateFlow to manage the state of the currency list reactively. When currencies are loaded, inserted, or filtered, the UI automatically reacts to the changes via Compose’s remember and collectAsState() functions.

Unit Testing

Unit tests are written using JUnit and Mockito for mocking dependencies. The tests are focused on the CurrencyViewModel to ensure:

	•	Correct data loading: Verifying that the currency list is correctly populated.
	•	Filtering functionality: Ensuring that the search functionality works as expected.
	•	Database interactions: Confirming that the clear, insert, and get operations are working correctly.

License
    This project is licensed under the MIT License.