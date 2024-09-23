# Android Book Management App

This project demonstrates a basic book management app, designed using Clean Architecture principles. The app allows users to browse, view details, and mark books as favorites. Data is stored locally using Room, and the UI is built entirely with Jetpack Compose.

## Architecture Overview
The app follows Clean Architecture, though it has been adapted pragmatically to suit the scope of this project. While it may seem like over-engineering for a project of this size, the focus was on maintainability and scalability. The architecture includes three essential layers:

- **Data Layer:** Responsible for data handling and persistence using Room.
- **Domain Layer:** Contains the core business logic and use cases. It is entirely independent of other layers.
- **Presentation Layer:** Manages the UI and user interaction, implemented using Jetpack Compose.

For the presentation layer, the MVVM pattern is used with some characteristics of MVI. The layer follows a unidirectional data flow approach, which is recommended and praised by Google for improving clarity and separation of responsibilities between components.

While additional components like data sources are commonly used in full-scale Clean Architecture, they were not included in this project for simplicity and pragmatism. The key architectural focus is on ensuring clean separation of concerns and managing dependencies effectively.

### Dependency Graph
The domain layer is kept independent of the data and presentation layers, which is crucial for a scalable architecture. Although this project does not implement modularization, packages were organized by layer, and this structure could be further improved by splitting them into separate modules.

Here is a high-level diagram representing the architecture:

![archi overview](https://github.com/user-attachments/assets/831bc022-4776-4574-947b-d94789ab6b33)


## Android vs. iOS Comparison
- **Use Cases:** Android includes use cases in the domain layer, but they are omitted in the iOS version, reflecting differences in how pragmatic approaches were applied.
- **Models:** In Android, the presentation layer has its own models, decoupled from the domain models. In contrast, the iOS version uses the same models across both layers.

## UI Implementation
The UI is implemented using Jetpack Compose, which provides a modern declarative way of building UI components. Even though XML-based UI is common, Compose was chosen to demonstrate modern Android practices. With proper architecture in place, switching between Compose and XML is a minor decision, requiring minimal changes. For example, replacing Compose with XML would mostly involve substituting the Compose views with fragments, without affecting the underlying business logic.

The app adheres to Material 3 design guidelines and supports dynamic color theming on Android 12 and above. Both light and dark modes are supported.

## Setup Instructions
Setting up the project is straightforward. Simply clone the repository and open the project in Android Studio. The project uses standard Gradle configuration, and no additional setup steps are required.

Build and run the app as you would with any standard Android project.

## Possible Improvements
- **CI/CD Setup:** Continuous integration and deployment (CI/CD) were not set up to keep the project within the time limits, but this could be easily implemented. For Android, Github Actions or a similar CI/CD platform could be used.
- **Modularization:** The current architecture could benefit from splitting the layers into separate modules to further improve scalability and maintainability.
- **XML Support:** While this project solely uses Compose, switching to XML is simple due to the robust architecture, which decouples the UI from business logic.
- **Tests:** Although writing unit tests is crucial, they are not present in this project due to time constraints. However, the decoupled layers and the use of a Dependency Injection framework greatly enhance the testability of the codebase. The best place to start writing tests would be from the ViewModels to ensure they behave as expected.

## Screenshots
![Android 1](https://github.com/user-attachments/assets/8f793907-9472-4638-8aca-98e3e38d8f08)
![Android 2](https://github.com/user-attachments/assets/5446e0af-b230-4cc1-99ce-6626bbc03b11)
![Android 3](https://github.com/user-attachments/assets/96330f7d-d461-4a16-9d57-bf7f71dad321)






