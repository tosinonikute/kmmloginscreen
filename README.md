## KMM Login Screen

Project Demonstrating Clean Code Architecture with Kotlin Multiplatform.

This repository contains a Kotlin Multiplatform application App with a single screen, that has two text inputs and a button to confirm. 

The shared module is connected with the Android project via the Gradle multi-project mechanism and contains the Kotlin Multiplatform library which validates text inputs for email and password text.

### Feature Implemented

-   Email validation check.
-   Password validation check, special character, uppercase, lowercase, a number and consecutive empty spaces.
-   A confirm button which is only enabled when both fields are valid.

### Architecture

Clean architecture, Model-View-ViewModel, this application demonstrates sharing the data and domain layers of the app and also the application state, from the data layer to Ui layer.

### The project is split into different layers:

Android App
-   Ui
-   Presentation

Shared Module
-   Domain
-   Data

This provides better abstractions between framework implementations and the underlying business logic. The layered shared module architectural approach is guided by clean architecture which provides a clear separation of concerns with its Abstraction Principle.

### Ui layer

`AndroidApp`  contains the MainActivity and Fragment which handles calling of the Ui components to interact with user inputs and provide feedback observed from the ViewState.

### Presentation layer

`AndroidApp`  contains the MainViewModel and handles calling of the usecase execution serving data from the ViewModel to the UI. The data being received are fields in the ViewState class that contains the relevant state.


### Domain layer

The  `shared: domain`  module contains domain model classes and Flow repository interface which represent the data we will be handling across presentation, domain and data layer.

-   `ValidateEmailUsecase`
-   `ValidatePasswordUsecase`

Use cases are provided in the domain layer and orchestrate the flow of data from the data layer onto the presentation layer and a split into modular pieces serving one particular purpose.

The UseCases use a  `BaseUseCase`  interface that defines the ContinuousUseCase with callback instead of a single result.

-   `ValidationStatusRepository`

Handles usecase interaction with the Data layer by providing Flow repository interfaces.

### Data layer

-   `ValidationStatusDataRepository`

A concrete data repository implementing a Flow repository interface defined in the Domain Layer. The repository exposes a single public functions, that’s defined by the interface. This function is responsible for constructing the domain model. This handles interaction with the TextValidator library and returns    `Flow<EmailValidationStatusDomainModel>` and   `Flow<PasswordValidationStatusDomainModel>` states which are served up to the presentation layer through domain objects.

### Library

-   `TextValidatorImpl`

A TextValidator class implementing an interface defined in the Library. The interface exposes functions for validating email and password, which returns an InputStatus Model.

### Testing

The `presentation`  layer contains `MainViewModelTest` , Unit Tests which are created to test the usecase response and update the expected ViewState.

The `Data`  layer contains `ValidationStatusRepositoryTest`  which is created under JvmTest module, Unit Tests which is created to test the Flow domain objects returned by each function.

The `TextValidator`  library contains `EmailTextValidatorTest`  and `PasswordTextValidatorTest`  which is a series of Parameterised Test to extensively test different valid emails and passwords.

### External Libraries

-   `Kotlin Coroutines and Kotlin Flow` - for asynchronous programming by using flow types to emit multiple values sequentially.
-   `Mockito` - mocking framework for Java/kotlin.
-   `JUnit` - Testing framework for Java/kotlin.
-   `Hilt` - Dependency Injection Framework.
-   `Timber` - logger which provides utility on top of Android's normal Log class.



