# 📱 Pascalina

**Pascalina** is a clean, minimal calculator app built with **Jetpack Compose**. Inspired by the first mechanical calculator in history, Pascalina is modern, lightweight, and ready to be extended.

## ✨ Features

- 🧮 Supports basic math expressions: `+`, `-`, `*`, `/`, `()`
- 🔄 Real-time result calculation
- ⌫ Backspace support
- 🧼 Clear button to reset input and result
- 📱 Responsive UI using **Jetpack Compose**
- 🌙 Dark mode support (optional)

## 📸 Screenshots

<img src="https://github.com/user-attachments/assets/2750bb14-baa6-45d1-8433-8ee02e1f606f" width="350" height="800" alt="mobile pascalina" />


## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **EvalEx** (I'm seriously thinking about creating my own parse)
- **StateFlow** for reactive state management
- **MVVM Architecture**

## 🚀 Getting Started

### Requirements
- Android Studio Hedgehog or newer
- Kotlin 2.0+
- Minimum SDK 26

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/HarukaYamamoto0/pascalina.git
   ```

2. Open in Android Studio
3. Run the app on an emulator or device

## 📦 Dependency

EvalEx is used to evaluate math expressions:

```kotlin
implementation("com.ezylang:EvalEx:3.5.0")
```

Check [EvalEx GitHub](https://github.com/ezylang/EvalEx) for updates.

## 🧠 Future Ideas

* Scientific mode (trigonometry, logarithms, etc.)
* History of calculations
* Memory storage (M+, MR, MC)
* Theming customization

## 🤝 Contributing

Feel free to fork this project and open PRs! Contributions are welcome ❤️

## 📄 License

MIT License © 2025 [Haruka Yamamoto](https://github.com/HarukaYamamoto0)

<a href="https://www.flaticon.com/free-icons/calculator" title="calculator icons">Calculator icons created by Pixel perfect - Flaticon</a>